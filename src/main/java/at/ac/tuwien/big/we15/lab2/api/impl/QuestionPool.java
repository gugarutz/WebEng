package at.ac.tuwien.big.we15.lab2.api.impl;

import at.ac.tuwien.big.we15.lab2.api.Category;
import at.ac.tuwien.big.we15.lab2.api.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marc on 24.04.15.
 */
public class QuestionPool {

    private List<Question> qlist;

    public QuestionPool(List<Category> categories) {

        qlist = new ArrayList<>();
        for (Category c : categories) {
            qlist.addAll(c.getQuestions());
        }
    }

    public List<Question> getQlist() {
        return qlist;
    }

    public void setQlist(List<Question> qlist) {
        this.qlist = qlist;
    }

    public Question getQuestion(int ID) {
        // List<Question> question = qlist.stream().filter(f -> f.getId() == ID).collect(Collectors.toList());
        Question question = null;
        for (Question q : qlist) {
            if (q.getId() == ID) {
                question = q;
            }
        }
        return question;
    }
}
