package at.ac.tuwien.big.we15.lab2.servlet;


import at.ac.tuwien.big.we15.lab2.api.Answer;
import at.ac.tuwien.big.we15.lab2.api.Question;
import at.ac.tuwien.big.we15.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we15.lab2.api.impl.QuestionPool;
import at.ac.tuwien.big.we15.lab2.api.impl.ServletJeopardyFactory;
import at.ac.tuwien.big.we15.lab2.api.impl.model.impl.Player;
import at.ac.tuwien.big.we15.lab2.api.impl.model.impl.PlayerStats;

import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
    private ServletJeopardyFactory factory;
    private QuestionDataProvider dataProvider;
    private QuestionPool questionPool;

    @Override
    public void init() throws ServletException {
        factory = new ServletJeopardyFactory(this.getServletContext());
        dataProvider = factory.createQuestionDataProvider();
        questionPool = new QuestionPool(dataProvider.getCategoryData());

        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession(true).getAttribute("stats") == null)
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

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
            Question question = questionPool.getQuestion(questionId);

            session.setAttribute("currentQuestion", question);

            getServletContext().getRequestDispatcher("/question.jsp").forward(request, response);
        }
    }

    // Question - get
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("jeopardy: doGet called");

        HttpSession session = request.getSession(false);
        String[] answers = request.getParameterValues("answers");
        Question currentQuestion = (Question)session.getAttribute("currentQuestion");
        PlayerStats player = (PlayerStats)session.getAttribute("stats");
        Player enemy = player.getEnemy();
        Player human = player.getHuman();

        if (checkCorrectness(currentQuestion.getCorrectAnswers(), answers)) {
           human.setMoney(currentQuestion.getValue());
        }

        getServletContext().getRequestDispatcher("/jeopardy.jsp").forward(request, response);
    }


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

    private String[] KI (List<Answer> allPossible,int anzrichtigeantworten) {

        String[] gewaehlt = new String[anzrichtigeantworten];
        Random randy = new Random();
        for(int rand = 0 ; rand < anzrichtigeantworten; rand ++)
        {
            gewaehlt[rand] = (allPossible.get(randy.nextInt(allPossible.size())).getId() + "");
        }
        return gewaehlt;
    }


}
