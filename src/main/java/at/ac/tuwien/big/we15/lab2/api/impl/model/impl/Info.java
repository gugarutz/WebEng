package at.ac.tuwien.big.we15.lab2.api.impl.model.impl;

/**
 * Created by Raimund on 26.04.2015.
 */
public class Info {
    private String text;
    private boolean correct;

    public Info() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
