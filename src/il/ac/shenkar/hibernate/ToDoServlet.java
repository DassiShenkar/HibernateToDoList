package il.ac.shenkar.hibernate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Arel on 05/01/2017.
 */
@WebServlet("/ToDoServlet")
public class ToDoServlet extends HttpServlet {
    HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch(request.getParameter("action")){
            case "login":
                boolean exist = dao.checkIfUserExists(new User(request.getParameter("email"),request.getParameter("password")));
                if(exist){
                    request.setAttribute("tasksList", dao.getAllItemsByUserId(1));//TODO: fix getUserByUserName and add a call to dao
                    request.getRequestDispatcher("/tasks.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("status", "don't exist");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            case "create":
                dao.createUser(new User(request.getParameter("email"),request.getParameter("password")));
                request.getRequestDispatcher("/tasks.jsp").forward(request, response);
        }
    }
}
