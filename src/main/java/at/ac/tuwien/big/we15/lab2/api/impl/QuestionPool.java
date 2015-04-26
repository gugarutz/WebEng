package at.ac.tuwien.big.we15.lab2.api.impl;

import at.ac.tuwien.big.we15.lab2.api.Category;
import at.ac.tuwien.big.we15.lab2.api.Question;
import at.ac.tuwien.big.we15.lab2.api.SelectableQuestion;
import at.ac.tuwien.big.we15.lab2.api.impl.model.impl.CategoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marc on 24.04.15.
 */
public class QuestionPool {

    private List<SelectableQuestion> qlist;

    public QuestionPool(List<CategoryBean> categories) {

        qlist = new ArrayList<>();
        for (CategoryBean c : categories) {
            qlist.addAll(c.getQuestions());
        }
    }

    public List<SelectableQuestion> getQlist() {
        return qlist;
    }

    public void setQlist(List<SelectableQuestion> qlist) {
        this.qlist = qlist;
    }

    public SelectableQuestion getQuestion(int ID) {
        SelectableQuestion question = null;
        
        for (SelectableQuestion q : qlist) {
            if (q.getQuestion().getId() == ID) {
                question = q;
            }
        }
        return question;
    }
}
