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

        if(request.getServletPath().equals("/jeopardy")) {
            String selectedQuestion = request.getParameter("question_selection");

            if(selectedQuestion != null) {
                int questionId = Integer.parseInt(request.getParameter("question_selection"));
                SelectableQuestion question = questionPool.getQuestion(questionId);
                question.setDisabled(true);

                session.setAttribute("currentQuestion", question);

                getServletContext().getRequestDispatcher("/question.jsp").forward(request, response);
            }
        }
    }

    // Question - get
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("jeopardy: doGet called");

        HttpSession session = request.getSession(false);

        PlayerStats stats = (PlayerStats)session.getAttribute("stats");
        PlayerInfo info = (PlayerInfo) session.getAttribute("info");
        Player enemy = stats.getEnemy();
        Player human = stats.getHuman();
        SelectableQuestion enemyQuestion = null;

        if(enemy.getMoney() >= human.getMoney())
        {
            //human hat bereits gewaehlt, enemy waehlt jetzt
            enemyQuestion = chooseRandomQuestion();

            if (checkCorrectness(enemyQuestion.getQuestion().getCorrectAnswers(), KI(enemyQuestion)))
            {
                enemy.addMoney(enemyQuestion.getQuestion().getValue());
                info.setEnemyInfo(true, enemyQuestion.getQuestion().getValue());
            }
            else {
                info.setEnemyInfo(false, enemyQuestion.getQuestion().getValue());
                enemy.addMoney(enemyQuestion.getQuestion().getValue() * -1);
            }

            // frage disable setzen
            enemyQuestion.setDisabled(true);
        }

        String[] answers = request.getParameterValues("answers");

        SelectableQuestion currentQuestion = (SelectableQuestion)session.getAttribute("currentQuestion");
        enemyQuestion = chooseRandomQuestion();

        if(!currentQuestion.isDisabled()) {
            if (answers != null && checkCorrectness(currentQuestion.getQuestion().getCorrectAnswers(), answers)) {
                human.addMoney(currentQuestion.getQuestion().getValue());
                info.setHumanInfo(true, currentQuestion.getQuestion().getValue());
            } else {
                info.setHumanInfo(false, currentQuestion.getQuestion().getValue());
                human.addMoney(currentQuestion.getQuestion().getValue() * -1);
            }
        }

        enemyQuestion = (SelectableQuestion) session.getAttribute("currentEnemyQuestion");

        if(enemyQuestion != null) {
            if (checkCorrectness(enemyQuestion.getQuestion().getCorrectAnswers(), KI(enemyQuestion))) {
                enemy.addMoney(enemyQuestion.getQuestion().getValue());
                info.setEnemyInfo(true, enemyQuestion.getQuestion().getValue());
            } else {
                info.setEnemyInfo(false, enemyQuestion.getQuestion().getValue());
                enemy.addMoney(enemyQuestion.getQuestion().getValue() * -1);
            }
        }

        // zuruecksetzen
        session.setAttribute("currentEnemyQuestion", null);

        //hier checken wir ob PC - money kleiner ist als human money
        if(enemy.getMoney() < human.getMoney())
        {
            //enemy waehlt random frage, erst dann waehlt human
            enemyQuestion = chooseRandomQuestion();

            enemyQuestion.setDisabled(true);

            session.setAttribute("currentEnemyQuestion", enemyQuestion);
        }

        info.setEnemyChosenQuestion(enemyQuestion);
        stats.setAskedQuestions(stats.getAskedQuestions() + 1);

        getServletContext().getRequestDispatcher("/jeopardy.jsp").forward(request, response);
    }

    //Methode zum ueberpruefen der gewaehlten antworten, aw => correct, ch => answered
    private boolean checkCorrectness(List<Answer> aw, String[] ch) {
        boolean b = false;

        if (aw.size() == ch.length) {

            for (String s : ch) {

                for (Answer a : aw) {
                    if (a.getId() == Integer.parseInt(s))
                        b = true;
                }
            }
        }

        return b;
    }

    private SelectableQuestion chooseRandomQuestion() {
        SelectableQuestion question = null;
        Random rnd = new Random();

        do {
            int id = rnd.nextInt(questionPool.getQlist().size());
            question = questionPool.getQuestion(id == 0 ? 1 : id);
        } while (question.isDisabled());


        return question;
    }

    // methode erzeugt computergenerierte Antworten
    private String[] KI (SelectableQuestion question) {

        Random randy = new Random();
        List<String> gewaehlt = new ArrayList<String>();
        int zuf = randy.nextInt(10);
        if((zuf%2) == 0) {
            for(Answer a : question.getQuestion().getCorrectAnswers())
            {
               gewaehlt.add(a.getId()+"");
            }
            String[] forreturn = new String[gewaehlt.size()];
            gewaehlt.toArray(forreturn);
            return forreturn;
        }
        else {
            while (gewaehlt.size() != question.getQuestion().getCorrectAnswers().size()) {
                int zufall = randy.nextInt(question.getQuestion().getAllAnswers().size());
                if (zufall == 0) {
                    zufall++;
                }
                String addit = (zufall + "");
                if (!gewaehlt.contains(addit)) {
                    gewaehlt.add(addit);
                }
            }
            String[] forreturn = new String[gewaehlt.size()];
            gewaehlt.toArray(forreturn);
            return forreturn;
        }
    }
}
