package at.ac.tuwien.big.we15.lab2.api;

/**
 * Created by Raimund on 26.04.2015.
 */
public interface SelectableQuestion {
    public boolean isDisabled();
    public void setDisabled(boolean disabled);
    public Question getQuestion();
}
