package at.ac.tuwien.big.we15.lab2.api.impl.model.impl;

import at.ac.tuwien.big.we15.lab2.api.Question;
import at.ac.tuwien.big.we15.lab2.api.SelectableQuestion;

/**
 * Created by Raimund on 26.04.2015.
 */
public class EnemyQuestionChosen {
    private SelectableQuestion question;
    private String name;

    public EnemyQuestionChosen() {
    }

    public EnemyQuestionChosen(SelectableQuestion question, String name) {
        this.question = question;
        this.name = name;
    }

    public SelectableQuestion getQuestion() {
        return question;
    }

    public void setQuestion(SelectableQuestion question) {
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
                        String.format("%s hat %s f&uuml;r %s gew&auml;hlt.", name, question.getQuestion().getCategory().getName(), question.getQuestion().getValue()) :
                        String.format("%s hat noch nichts gew&auml;hlt.", name);
    }
}
