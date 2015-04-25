package at.ac.tuwien.big.we15.lab2.api.impl.controller;

import at.ac.tuwien.big.we15.lab2.api.impl.DAO.IUserDAO;
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

    IUserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        System.out.println("asdf");

        if (request.getServletPath().equals("/login")) {
            HttpSession session = request.getSession();
            User user = userDAO.getNewUser();

            user.setName(request.getParameter("username"));
            session.setAttribute("user", user);

            RequestDispatcher dispatcher =
                    getServletContext()
                            .getRequestDispatcher("/jeopardy.jsp");

            dispatcher.forward(request, response);
        }
    }
}