package il.ac.shenkar.hibernate;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ToDoServlet")
public class ToDoServlet extends HttpServlet {

    HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Time time = new Time(0,0);
        time.startCount();
        Cookie seconds_cookie;
        RequestDispatcher view = null;
        switch (request.getParameter("action")) {
            case "index":
                view = request.getRequestDispatcher("index.jsp");
                seconds_cookie = new Cookie("time_elapsed",Double.toString(time.computeTime()));
                seconds_cookie.setMaxAge(60*60*24);   // 24 hours
                response.addCookie(seconds_cookie);
                view.forward(request, response);
                request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
                break;

            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                int userID = Integer.parseInt(request.getParameter("userID"));
                String admin = request.getParameter("admin");
                Item item = dao.getItemByID(id);
                dao.deleteItem(item);
                if(admin.equals("true")){
                    request.setAttribute("tasksList", dao.getAllTasks());
                }
                else{
                    request.setAttribute("tasksList", dao.getAllItemsByUserId(userID));
                }
                seconds_cookie = new Cookie("time_elapsed",Double.toString(time.computeTime()));
                seconds_cookie.setMaxAge(60*60*24);   // 24 hours
                response.addCookie(seconds_cookie);
                request.getRequestDispatcher("/tasks.jsp").forward(request, response);
                break;

            case "tasks":
                int user_id = Integer.parseInt(request.getParameter("id"));
                User user = dao.getUserById(Integer.parseInt(request.getParameter("id")));
                if(user.getUsername().equals("administrator") && user.getPassword().equals("admin")){
                    request.setAttribute("tasksList", dao.getAllTasks());
                }
                else{
                    request.setAttribute("tasksList", dao.getAllItemsByUserId(user_id));
                }
                seconds_cookie = new Cookie("time_elapsed",Double.toString(time.computeTime()));
                seconds_cookie.setMaxAge(60*60*24);   // 24 hours
                response.addCookie(seconds_cookie);
                request.getRequestDispatcher("/tasks.jsp").forward(request, response);

                case "log_out":
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for(Cookie cookie:cookies){
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
                        }
                    }
                    seconds_cookie = new Cookie("time_elapsed",Double.toString(time.computeTime()));
                    seconds_cookie.setMaxAge(60*60*24);   // 24 hours
                    response.addCookie(seconds_cookie);
                    view = request.getRequestDispatcher("index.jsp");
                    view.forward(request, response);
                    request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
                    break;
                }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = null;
        Time time = new Time(0,0);
        time.startCount();
        Cookie seconds_cookie;
        switch(request.getParameter("action")){
            case "login":
                User user = new User(request.getParameter("email"),request.getParameter("password"));
                boolean exist = dao.checkIfUserExists(user);
                if(exist){
                    Cookie cookie = new Cookie("logged_in","true");
                    Cookie cookie1 = new Cookie("user_id",Integer.toString(dao.getUserIdByEmail(user)));
                    Cookie cookie2 = new Cookie("name",user.getUsername());
                    cookie.setMaxAge(60*60*24);   // 24 hours
                    cookie1.setMaxAge(60*60*24);  // 24 hours
                    cookie2.setMaxAge(60*60*24);  // 24 hours
                    response.addCookie(cookie);
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
                    if(request.getParameter("email").equals("administrator") && request.getParameter("password").equals("admin")){
                        request.setAttribute("tasksList", dao.getAllTasks());
                    }
                    else{
                        request.setAttribute("tasksList", dao.getAllItemsByUserId(Integer.parseInt(cookie1.getValue())));
                    }
                    seconds_cookie = new Cookie("time_elapsed",Double.toString(time.computeTime()));
                    seconds_cookie.setMaxAge(60*60*24);   // 24 hours
                    response.addCookie(seconds_cookie);
                    view = request.getRequestDispatcher("tasks.jsp");
                    view.forward(request, response);
                    request.getRequestDispatcher("/tasks.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("status", "This user don't exist");
                    seconds_cookie = new Cookie("time_elapsed",Double.toString(time.computeTime()));
                    seconds_cookie.setMaxAge(60*60*24);   // 24 hours
                    response.addCookie(seconds_cookie);
                    view = request.getRequestDispatcher("index.jsp");
                    view.forward(request, response);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;

            case "create":
                User usr = new User(request.getParameter("email"),request.getParameter("password"));
                dao.createUser(usr);
                Cookie cookie = new Cookie("id",Integer.toString(usr.getId()));
                cookie.setMaxAge(60*60*24);
                response.addCookie(cookie);
                //TODO: add administrator to cookies
                // add username in tasks.jsp
                seconds_cookie = new Cookie("time_elapsed",Double.toString(time.computeTime()));
                seconds_cookie.setMaxAge(60*60*24);   // 24 hours
                response.addCookie(seconds_cookie);
                request.getRequestDispatcher("/tasks.jsp").forward(request, response);
                break;

            case "editTask":
                int userID = Integer.parseInt(request.getParameter("userID"));
                String admin = request.getParameter("admin");
                Item item = dao.getItemByID(Integer.parseInt(request.getParameter("id")));
                item.setStatus(request.getParameter("status"));
                item.setTitle(request.getParameter("title"));
                dao.updateItem(item);
                if(admin.equals("true")){
                    request.setAttribute("tasksList", dao.getAllTasks());
                }
                else{
                    request.setAttribute("tasksList", dao.getAllItemsByUserId(userID));
                }
                seconds_cookie = new Cookie("time_elapsed",Double.toString(time.computeTime()));
                seconds_cookie.setMaxAge(60*60*24);   // 24 hours
                response.addCookie(seconds_cookie);
                request.getRequestDispatcher("/tasks.jsp").forward(request, response);
                break;

            case "addItem":
                String status = request.getParameter("status");
                String title = request.getParameter("title");
                int user_id = Integer.parseInt(request.getParameter("userID"));
                new Item(user_id,title,status);
                //TODO: if admin delete other's tasks, need to navigate to his all tasks page
                if(dao.getNameById(user_id).equals("administrator")){
                    request.setAttribute("tasksList", dao.getAllTasks());
                }
                else{
                    request.setAttribute("tasksList", dao.getAllItemsByUserId(user_id));
                }
                seconds_cookie = new Cookie("time_elapsed",Double.toString(time.computeTime()));
                seconds_cookie.setMaxAge(60*60*24);   // 24 hours
                response.addCookie(seconds_cookie);
                request.getRequestDispatcher("/tasks.jsp").forward(request, response);
                break;
        }
    }
}
