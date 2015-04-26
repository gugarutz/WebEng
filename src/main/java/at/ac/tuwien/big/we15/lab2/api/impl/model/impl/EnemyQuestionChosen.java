package at.ac.tuwien.big.we15.lab2.api.impl.model.impl;

import at.ac.tuwien.big.we15.lab2.api.Question;

/**
 * Created by Raimund on 26.04.2015.
 */
public class EnemyQuestionChosen {
    private Question question;
    private String name;

    public EnemyQuestionChosen() {
    }

    public EnemyQuestionChosen(Question question, String name) {
        this.question = question;
        this.name = name;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return question != null ?
                        String.format("%s hat %s f&uuml;r %s gew&auml;hlt.", name, question.getCategory().getName(), question.getValue()) :
                        String.format("%s hat noch nichts gew&auml;hlt.", name);
    }
}
