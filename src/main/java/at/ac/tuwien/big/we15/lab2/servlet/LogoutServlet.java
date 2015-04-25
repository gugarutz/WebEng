package at.ac.tuwien.big.we15.lab2.servlet;

import at.ac.tuwien.big.we15.lab2.api.impl.DAO.impl.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    UserDAO userDAO = new UserDAO();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        //false damit nur dann eine session zurueckgeliefert wird wenn es schon eine gibt
        HttpSession session = request.getSession(false);

        //null wenn es keine aktuelle session gibt
        if (session != null) {

            response.setCharacterEncoding("UTF-8");

            RequestDispatcher dispatcher =
                    getServletContext()
                            .getRequestDispatcher("/login");

            dispatcher.forward(request, response);
        }
    }
}