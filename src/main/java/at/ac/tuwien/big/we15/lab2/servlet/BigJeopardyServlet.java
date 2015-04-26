package at.ac.tuwien.big.we15.lab2.servlet;


import at.ac.tuwien.big.we15.lab2.api.*;
import at.ac.tuwien.big.we15.lab2.api.impl.QuestionPool;
import at.ac.tuwien.big.we15.lab2.api.impl.ServletJeopardyFactory;
import at.ac.tuwien.big.we15.lab2.api.impl.model.impl.*;

import java.util.ArrayList;
import java.util.Random;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Marc on 24.04.15.
 */
@WebServlet(name = "BigJeopardyServlet", urlPatterns = {"/jeopardy", "/question"})
public class BigJeopardyServlet extends HttpServlet {
    private QuestionPool questionPool;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BigJeopardyServlet service called");

        HttpSession session = req.getSession(true);

        if(session.getAttribute("stats") == null) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            CategoryListBean categories = (CategoryListBean)session.getAttribute("categories");
            questionPool = new QuestionPool(categories.getCategories());
        }

        super.service(req, resp);
    }

    // Jeopardy - Post
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("BigJeopardyServlet doPost called");

        HttpSession session = request.getSession(false);
        String selectedQuestion = request.getParameter("question_selection");

        if(selectedQuestion != null) {
            int questionId = Integer.parseInt(request.getParameter("question_selection"));
            SelectableQuestion question = questionPool.getQuestion(questionId);
            question.setDisabled(true);

            session.setAttribute("currentQuestion", question);

            getServletContext().getRequestDispatcher("/question.jsp").forward(request, response);
        }
    }

    // Question - get
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("jeopardy: doGet called");
        HttpSession session = request.getSession(false);
        PlayerStats stats = (PlayerStats)session.getAttribute("stats");
        Player enemy = stats.getEnemy();
        Player human = stats.getHuman();

        if(enemy.getMoney() >= human.getMoney())
        {
            //human hat bereits gewählt, enemy wählt jetzt

        }

        String[] answers = request.getParameterValues("answers");

        if(answers != null) {
            SelectableQuestion currentQuestion = (SelectableQuestion)session.getAttribute("currentQuestion");
            PlayerInfo info = (PlayerInfo) session.getAttribute("info");

            if(checkCorrectness(currentQuestion.getQuestion().getCorrectAnswers(),KI(currentQuestion.getQuestion().getAllAnswers(),currentQuestion.getQuestion().getCorrectAnswers().size())))
            {
                enemy.addMoney(currentQuestion.getQuestion().getValue());
                info.setEnemyInfo(true,currentQuestion.getQuestion().getValue());
            }
            else {
                info.setEnemyInfo(false,currentQuestion.getQuestion().getValue());
                enemy.addMoney(currentQuestion.getQuestion().getValue()*-1);
            }
            if (checkCorrectness(currentQuestion.getQuestion().getCorrectAnswers(), answers)) {
                human.addMoney(currentQuestion.getQuestion().getValue());
                info.setHumanInfo(true, currentQuestion.getQuestion().getValue());
            } else {
                info.setHumanInfo(false, currentQuestion.getQuestion().getValue());
                human.addMoney(currentQuestion.getQuestion().getValue()*-1);
            }

            stats.setAskedQuestions(stats.getAskedQuestions() + 1);

            //hier checken wir ob PC - money kleiner ist als human money
            if(enemy.getMoney() < human.getMoney())
            {
                //enemy wählt random frage, erst dann wählt human

            }

            getServletContext().getRequestDispatcher("/jeopardy.jsp").forward(request, response);
        }
    }

    //Methode zum �berpr�fen der gew�hlten antworten
    private boolean checkCorrectness(List<Answer> aw, String[] ch) {
        boolean b = true;
        if (aw.size() == ch.length) {
            for (String s : ch) {
                for (Answer a : aw) {
                    if (a.getId() != Integer.parseInt(s))
                        b = false;
                }
            }
        } else
            b = false;

        return b;
    }

    // methode erzeugt computergenerierte Antworten
    private String[] KI (List<Answer> allPossible,int anzrichtigeantworten) {

        List<String> gewaehlt = new ArrayList<String>();
        Random randy = new Random();
        while(gewaehlt.size() != anzrichtigeantworten)
        {
            int zufall = randy.nextInt(allPossible.size());
            if(zufall == 0)
            {
                zufall ++;
            }
            String addit = (zufall+"");
            if(!gewaehlt.contains(addit))
            {
                gewaehlt.add(addit);
            }
        }
        String[] forreturn = new String[gewaehlt.size()];
        gewaehlt.toArray(forreturn);
        return forreturn;

    }
}
