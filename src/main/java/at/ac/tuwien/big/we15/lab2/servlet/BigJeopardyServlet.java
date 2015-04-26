package at.ac.tuwien.big.we15.lab2.servlet;


import at.ac.tuwien.big.we15.lab2.api.Answer;
import at.ac.tuwien.big.we15.lab2.api.Question;
import at.ac.tuwien.big.we15.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we15.lab2.api.impl.QuestionPool;
import at.ac.tuwien.big.we15.lab2.api.impl.ServletJeopardyFactory;
import at.ac.tuwien.big.we15.lab2.api.impl.model.impl.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
@WebServlet(name = "BigJeopardyServlet", urlPatterns = {"/BigJeopardyServlet", "/question"})
public class BigJeopardyServlet extends HttpServlet {
    private ServletJeopardyFactory factory;
    private QuestionDataProvider dataProvider;
    private QuestionPool questionPool;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Jeopardy service called");

        factory = new ServletJeopardyFactory(this.getServletContext());
        dataProvider = factory.createQuestionDataProvider();
        questionPool = new QuestionPool(dataProvider.getCategoryData());
        super.service(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Jeopardy doPost called");

        HttpSession session = request.getSession(true);
        RequestDispatcher dispatcher = null;

        String target = "/jeopardy.jsp";

        if (request.getServletPath().equals("/question")) {

            int questionId = Integer.parseInt(request.getParameter("question_selection"));
            Question question = questionPool.getQuestion(questionId);

            target = "/question.jsp";

            session.setAttribute("question", question);
        }

        dispatcher = getServletContext().getRequestDispatcher(target);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Jeopardy doGet called");

        HttpSession session = request.getSession(true);
        RequestDispatcher dispatcher = null;

        String target = "/login";

        if (session != null)
            target = "/jeopardy.jsp";

        dispatcher = getServletContext().getRequestDispatcher(target);

        dispatcher.forward(request, response);
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


}
