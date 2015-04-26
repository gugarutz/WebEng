package at.ac.tuwien.big.we15.lab2.api.impl.model.impl;

import at.ac.tuwien.big.we15.lab2.api.Question;
import at.ac.tuwien.big.we15.lab2.api.SelectableQuestion;

/**
 * Created by Raimund on 26.04.2015.
 */
public class SimpleSelectableQuestion implements SelectableQuestion {
    private Question question;
    private boolean disabled;

    public SimpleSelectableQuestion() {
    }

    public SimpleSelectableQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean isDisabled() {
        return disabled;
    }

    @Override
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public Question getQuestion() {
        return question;
    }
}
