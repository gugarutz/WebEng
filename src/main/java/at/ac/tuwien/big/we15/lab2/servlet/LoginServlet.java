package at.ac.tuwien.big.we15.lab2.servlet;

import at.ac.tuwien.big.we15.lab2.api.Avatar;
import at.ac.tuwien.big.we15.lab2.api.impl.DAO.IUserDao;
import at.ac.tuwien.big.we15.lab2.api.impl.DAO.impl.UserDAO;
import at.ac.tuwien.big.we15.lab2.api.impl.model.impl.Player;
import at.ac.tuwien.big.we15.lab2.api.impl.model.impl.PlayerStats;

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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession(true).getAttribute("stats") != null)
            getServletContext().getRequestDispatcher("/jeopardy.jsp").forward(req, resp);

        super.service(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login: doPost called");

        HttpSession session = request.getSession(true);

        if (request.getServletPath().equals("/login")) {
            Player human = userDAO.getNewPlayer();
            Avatar humanAvatar = Avatar.getRandomAvatar();

            human.setName(request.getParameter("username"));
            human.setAvatar(humanAvatar);
            human.setMoney(0);

            Player enemy = userDAO.getNewPlayer();
            Avatar enemyAvatar = Avatar.getOpponent(humanAvatar);

            enemy.setName(enemyAvatar.getName());
            enemy.setAvatar(enemyAvatar);
            enemy.setMoney(0);

            PlayerStats stats = new PlayerStats();
            stats.setHuman(human);
            stats.setEnemy(enemy);
            stats.setAskedQuestions(0);

            session.setAttribute("stats", stats);

            getServletContext().getRequestDispatcher("/jeopardy.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }
}