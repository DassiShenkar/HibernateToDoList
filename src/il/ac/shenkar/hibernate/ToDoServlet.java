package il.ac.shenkar.hibernate;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ToDoServlet")
public class ToDoServlet extends HttpServlet {
    private final Logger logger;
    private HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
    private String responseTime = null;

    public ToDoServlet() {
        logger = Logger.getLogger(ToDoServlet.class);
        BasicConfigurator.configure();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Time time = new Time(0, 0);
        time.startCount();  // start count time for response
        Cookie seconds_cookie;
        RequestDispatcher view;
        switch (request.getParameter("action")) {

            case "index":
                view = request.getRequestDispatcher("index.jsp");
                responseTime = Double.toString(time.computeTime());
                seconds_cookie = new Cookie("time_elapsed", responseTime);  // response time
                seconds_cookie.setMaxAge(60 * 60 * 24);   // 24 hours
                response.addCookie(seconds_cookie);
                logger.info("response time for index page: " + responseTime);
                view.forward(request, response);
                request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);  // move to index page
                break;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view;
        Time time = new Time(0, 0);
        time.startCount();  // start measure time
        Cookie seconds_cookie;
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

                    request.setAttribute("userID", Integer.toString(dao.getUserIdByEmail(user)));
                    request.setAttribute("userName", user.getUsername());
                    request.setAttribute("tasksSize", dao.getAllTasks().size());
                    request.setAttribute("time", responseTime);



                    seconds_cookie = new Cookie("time_elapsed", responseTime);
                    seconds_cookie.setMaxAge(60 * 60 * 24);   // 24 hours
                    logger.info("response time for login: " + responseTime);
                    response.addCookie(seconds_cookie);
                    request.getRequestDispatcher("/tasks.jsp").forward(request, response);  // move to tasks page
                } else {
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
                Cookie cookie = new Cookie("id", Integer.toString(usr.getId()));
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
                responseTime = Double.toString(time.computeTime());
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
                new Item(user_id, title, status);
                if (dao.getNameById(user_id).equals("administrator")) {  // if admin is true, get all tasks
                    request.setAttribute("tasksList", dao.getAllTasks());
                } else {
                    request.setAttribute("tasksList", dao.getAllItemsByUserId(user_id));  // get tasks by user id
                }
                responseTime = Double.toString(time.computeTime());
                seconds_cookie = new Cookie("time_elapsed", responseTime);
                seconds_cookie.setMaxAge(60 * 60 * 24);   // 24 hours
                logger.info("Response time for adding a task: " + responseTime);
                response.addCookie(seconds_cookie);
                request.getRequestDispatcher("/tasks.jsp").forward(request, response);  // move to tasks page
                break;
        }
    }
}
