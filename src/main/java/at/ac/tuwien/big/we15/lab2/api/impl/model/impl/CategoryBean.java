package at.ac.tuwien.big.we15.lab2.api.impl.model.impl;

import at.ac.tuwien.big.we15.lab2.api.SelectableQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raimund on 26.04.2015.
 */
public class CategoryBean {
    private String name;
    private List<SelectableQuestion> questions;

    public CategoryBean() {
    }

    public CategoryBean(String name) {
        this.questions = new ArrayList<SelectableQuestion>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SelectableQuestion> getQuestions() {
        return questions;
    }

    public void addQuestion(SelectableQuestion question) {
        this.questions.add(question);
    }
}
