package il.ac.shenkar.hibernate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email;
        String password;
        HibernateToDoListDAO hibernateToDoListDAO = HibernateToDoListDAO.getInstance();
        System.out.println(request.getParameter("action"));
        switch(request.getParameter("action")){
            case "login":
                System.out.println("i am innnnnnnnn login");
                email = request.getParameter("email");
                password = request.getParameter("password");
                User user = new User(email,password);
                boolean exist = true;
                if(exist){
                    List<Item> list = new ArrayList<>();
                    list.add(new Item(1,"shopping","waiting"));
                    list.add(new Item(2,"shopping2","waiting"));
                    list.add(new Item(3,"shopping3","waiting"));
                    request.setAttribute("tasksList", list);
                    request.getRequestDispatcher("/tasks.jsp").forward(request, response);
                }
                else{
                    System.out.println("not exist");
                    request.setAttribute("statuss", "dont exist");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            case "create":
                System.out.println("create");
                email = request.getParameter("email");
                password = request.getParameter("password");
                hibernateToDoListDAO.createUser(new User(email,password));
                PrintWriter out5 = response.getWriter();
                out5.println("<p>Success</p>");
        }
    }
}
