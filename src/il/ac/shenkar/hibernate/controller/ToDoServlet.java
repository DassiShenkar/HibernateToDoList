package il.ac.shenkar.hibernate.controller;

import il.ac.shenkar.hibernate.controller.utils.Time;
import il.ac.shenkar.hibernate.model.dao.HibernateToDoListDAO;
import il.ac.shenkar.hibernate.model.Item;
import il.ac.shenkar.hibernate.model.dao.ToiListException;
import il.ac.shenkar.hibernate.model.User;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * This class is the main servlet that run this application and get all GET and POST requests
 * @author Arel Gindos
 * @author Dassi Rosen
 * @author Lior Lerner
 * @see HttpServlet#HttpServlet()
 */
@WebServlet("/ToDoServlet")
public class ToDoServlet extends HttpServlet {
    private final Logger logger;
    private final HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
    private String responseTime = null;

    /**
     * Default Constructor
     */
    public ToDoServlet() {
        logger = Logger.getLogger(ToDoServlet.class);
        BasicConfigurator.configure();
    }

    /**
     * This method get all the get request from client
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     * @throws ServletException Handling Servlet Exception
     * @throws IOException Handling IO Exception
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Time time = new Time(0, 0);
        time.startCount();  // start count time for response
        Cookie seconds_cookie;
        RequestDispatcher view;
        try {
            switch (request.getParameter("action")) {

                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    int userID = Integer.parseInt(request.getParameter("userID"));
                    String admin = request.getParameter("admin");
                    responseTime = Double.toString(time.computeTime());
                    Item item = dao.getItemByID(id);
                    dao.deleteItem(item);  // delete the item from the database
                    if (admin.equals("true")) {  // check if admin logged in
                        request.setAttribute("tasksList", dao.getAllTasks());  // if admin, return all tasks
                    } else {
                        request.setAttribute("tasksList", dao.getAllItemsByUserId(userID));  // if not admin return user tasks
                    }
                    seconds_cookie = new Cookie("time_elapsed", responseTime);
                    seconds_cookie.setMaxAge(60 * 60 * 24);  // 24 hours
                    logger.info("response time for deletion: " + responseTime);
                    request.setAttribute("userName",dao.getNameById(userID));
                    request.setAttribute("userID",userID);
                    response.addCookie(seconds_cookie);  // add cookie for response time
                    request.getRequestDispatcher("/tasks.jsp").forward(request, response);  // move to tasks page
                    break;

                case "tasks":
                    int user_id = Integer.parseInt(request.getParameter("id"));
                    User user = dao.getUserById(Integer.parseInt(request.getParameter("id")));
                    if (user.getUsername().equals("administrator") && user.getPassword().equals("admin")) {  // check if admin logged in
                        request.setAttribute("tasksList", dao.getAllTasks());  // get all tasks
                    } else {
                        request.setAttribute("tasksList", dao.getAllItemsByUserId(user_id));  // get tasks by user id
                    }
                    responseTime = Double.toString(time.computeTime());
                    seconds_cookie = new Cookie("time_elapsed", responseTime);
                    seconds_cookie.setMaxAge(60 * 60 * 24);  // 24 hours
                    logger.info("response time for tasks page: " + responseTime);
                    response.addCookie(seconds_cookie);  // add cookie for response time
                    request.getRequestDispatcher("/tasks.jsp").forward(request, response);  // move to tasks page

                case "log_out":
                    Cookie[] cookies = request.getCookies();  // delete all cookies used by the servlet because of the log out
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("logged_in")) {
                                cookie.setMaxAge(0);
                                cookie.setValue("");
                                response.addCookie(cookie);
                            } else if (cookie.getName().equals("user_id")) {
                                cookie.setMaxAge(0);
                                cookie.setValue("");
                                response.addCookie(cookie);
                            } else if (cookie.getName().equals("name")) {
                                cookie.setMaxAge(0);
                                cookie.setValue("");
                                response.addCookie(cookie);
                            } else if (cookie.getName().equals("time_elapsed")) {
                                cookie.setMaxAge(0);
                                cookie.setValue("");
                                response.addCookie(cookie);
                            }
                        }
                        seconds_cookie = new Cookie("time_elapsed", Double.toString(time.computeTime()));
                        seconds_cookie.setMaxAge(60 * 60 * 24);   // 24 hours
                        response.addCookie(seconds_cookie);   // add cookie for response time
                        view = request.getRequestDispatcher("index.jsp");
                        view.forward(request, response);
                        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response); // move to index page
                        break;
                    }
            }
        }
        catch (ToiListException e) {
            logger.error("Error in doGet", e);
            request.setAttribute("exception", e.getMessage());
            view = request.getRequestDispatcher("error.jsp");
            view.forward(request, response);
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response); // move to index page
        }
    }

    /**
     * This method get all the post request from client
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     * @throws ServletException Handling Servlet Exception
     * @throws IOException Handling IO Exception
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view;
        Time time = new Time(0, 0);
        time.startCount();  // start counter
        Cookie seconds_cookie;
        try {
            switch (request.getParameter("action")) {
                case "login":
                    User user = new User(request.getParameter("email"), request.getParameter("password"));
                    boolean exist = dao.checkIfUserExists(user);
                    if (exist) {  // user is already in the database
                        Cookie cookie = new Cookie("logged_in", "true");
                        Cookie cookie1 = new Cookie("user_id", Integer.toString(dao.getUserIdByEmail(user)));
                        Cookie cookie2 = new Cookie("name", user.getUsername());
                        cookie.setMaxAge(60 * 60 * 24);   // 24 hours
                        cookie1.setMaxAge(60 * 60 * 24);  // 24 hours
                        cookie2.setMaxAge(60 * 60 * 24);  // 24 hours
                        response.addCookie(cookie);
                        response.addCookie(cookie1);
                        response.addCookie(cookie2);
                        if (request.getParameter("email").equals("administrator") && request.getParameter("password").equals("admin")) {
                            request.setAttribute("tasksList", dao.getAllTasks());
                        } else {
                            request.setAttribute("tasksList", dao.getAllItemsByUserId(Integer.parseInt(cookie1.getValue())));
                        }
                        responseTime = Double.toString(time.computeTime());
                        request.setAttribute("userID", dao.getUserIdByEmail(user));
                        request.setAttribute("userName", user.getUsername());
                        request.setAttribute("tasksSize", dao.getAllTasks().size());
                        request.setAttribute("time", responseTime);
                        seconds_cookie = new Cookie("time_elapsed", responseTime);
                        seconds_cookie.setMaxAge(60 * 60 * 24);   // 24 hours
                        logger.info("response time for login: " + responseTime);
                        response.addCookie(seconds_cookie);
                        request.getRequestDispatcher("/tasks.jsp").forward(request, response);  // move to tasks page
                    }
                    else {
                        request.setAttribute("status", "This user don't exist");  // return to client that user not exist
                        seconds_cookie = new Cookie("time_elapsed", Double.toString(time.computeTime()));
                        seconds_cookie.setMaxAge(60 * 60 * 24);   // 24 hours
                        response.addCookie(seconds_cookie);
                        view = request.getRequestDispatcher("index.jsp");
                        view.forward(request, response);
                        request.getRequestDispatcher("index.jsp").forward(request, response);  // move to index page
                    }
                    break;

                case "create":
                    User usr = new User(request.getParameter("email"), request.getParameter("password"));
                    dao.createUser(usr);
                    Cookie cookie = new Cookie("user_id", Integer.toString(usr.getId()));
                    cookie.setMaxAge(60 * 60 * 24);
                    response.addCookie(cookie);
                    request.setAttribute("userID", (usr.getId()));
                    request.setAttribute("userName", usr.getUsername());
                    responseTime = Double.toString(time.computeTime());
                    if(usr.getUsername().toLowerCase().equals("administrator") && usr.getPassword().toLowerCase().equals("admin")){
                        request.setAttribute("tasksList", dao.getAllTasks());
                    }
                    seconds_cookie = new Cookie("time_elapsed", responseTime);
                    seconds_cookie.setMaxAge(60 * 60 * 24);   // 24 hours
                    logger.info("response time for creating a new user: " + responseTime);
                    response.addCookie(seconds_cookie);
                    request.getRequestDispatcher("/tasks.jsp").forward(request, response);  // move to tasks page
                    break;

                case "editTask":
                    int userID = Integer.parseInt(request.getParameter("userID"));
                    String admin = request.getParameter("admin");
                    Item item = dao.getItemByID(Integer.parseInt(request.getParameter("id")));
                    item.setStatus(request.getParameter("status"));
                    item.setTitle(request.getParameter("title"));
                    dao.updateItem(item);  // update the task in the database
                    if (admin.equals("true")) {
                        request.setAttribute("tasksList", dao.getAllTasks());  // if admin is logged in return all tasks
                    } else {
                        request.setAttribute("tasksList", dao.getAllItemsByUserId(userID));// return tasks by user
                    }
                    responseTime = Double.toString(time.computeTime());
                    request.setAttribute("userID", userID);
                    request.setAttribute("userName",dao.getNameById(userID));
                    seconds_cookie = new Cookie("time_elapsed", responseTime);
                    seconds_cookie.setMaxAge(60 * 60 * 24);   // 24 hours
                    logger.info("Response time for updating task: " + responseTime);
                    response.addCookie(seconds_cookie);
                    request.getRequestDispatcher("/tasks.jsp").forward(request, response);  // move to tasks page
                    break;

                case "addItem":
                    String status = request.getParameter("status");
                    String title = request.getParameter("title");
                    int user_id = Integer.parseInt(request.getParameter("userID"));
                    dao.addItem(new Item(user_id, title, status));
                    if (dao.getNameById(user_id).equals("administrator")) {  // if admin is true, get all tasks
                        request.setAttribute("tasksList", dao.getAllTasks());
                    } else {
                        request.setAttribute("tasksList", dao.getAllItemsByUserId(user_id));  // get tasks by user id
                    }
                    request.setAttribute("userID", user_id);
                    request.setAttribute("userName", dao.getUserById(user_id).getUsername());
                    responseTime = Double.toString(time.computeTime());
                    seconds_cookie = new Cookie("time_elapsed", responseTime);
                    seconds_cookie.setMaxAge(60 * 60 * 24);   // 24 hours
                    logger.info("Response time for adding a task: " + responseTime);
                    response.addCookie(seconds_cookie);
                    request.getRequestDispatcher("/tasks.jsp").forward(request, response);  // move to tasks page
                    break;
            }
        } catch (ToiListException e) {
            logger.error("Error in doPost", e);
            request.setAttribute("exception", e.getMessage());
            view = request.getRequestDispatcher("error.jsp");
            view.forward(request, response);
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response); // move to index page
        }
    }
}

