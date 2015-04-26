package at.ac.tuwien.big.we15.lab2.servlet;

import at.ac.tuwien.big.we15.lab2.api.impl.DAO.IUserDao;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("logout: doGet called");

        //param false damit nur dann eine session zurueckgeliefert wird wenn es schon eine gibt
        HttpSession session = req.getSession(false);

        //check ob es eine aktuelle session gibt
        if (session != null) {
            session.setAttribute("stats", null);
        }

        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}