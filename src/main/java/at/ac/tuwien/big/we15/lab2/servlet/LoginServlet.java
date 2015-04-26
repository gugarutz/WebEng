package at.ac.tuwien.big.we15.lab2.servlet;

import at.ac.tuwien.big.we15.lab2.api.impl.DAO.IUserDao;
import at.ac.tuwien.big.we15.lab2.api.impl.DAO.impl.UserDAO;
import at.ac.tuwien.big.we15.lab2.api.impl.model.impl.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    IUserDao userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        System.out.println("login: doPost called");

        if (request.getServletPath().equals("/login")) {

            response.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();
            User user = userDAO.getNewUser();

            user.setName(request.getParameter("username"));
            session.setAttribute("user", user);

            RequestDispatcher dispatcher =
                    getServletContext()
                            .getRequestDispatcher("/BigJeopardyServlet");

            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        System.out.println("login: doGet called");

        String redirectResource = "/login.jsp";

        if (request.getServletPath().equals("/login")) {

            if (request.getSession(false) != null) {
                redirectResource = "/BigJeopardyServlet";
            }

            response.setCharacterEncoding("UTF-8");

            RequestDispatcher dispatcher =
                    getServletContext()
                            .getRequestDispatcher(redirectResource);

            dispatcher.forward(request, response);
        }
    }
}