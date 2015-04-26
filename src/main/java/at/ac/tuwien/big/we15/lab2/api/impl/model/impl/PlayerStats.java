package at.ac.tuwien.big.we15.lab2.api.impl.model.impl;

/**
 * Created by Raimund on 26.04.2015.
 */
public class PlayerStats {
    private Player human;
    private Player enemy;
    private int askedQuestions;

    public PlayerStats() {
    }

    public PlayerStats(Player human, Player enemy, int askedQuestions) {
        this.human = human;
        this.enemy = enemy;
        this.askedQuestions = askedQuestions;
    }

    public Player getHuman() {
        return human;
    }

    public void setHuman(Player human) {
        this.human = human;
    }

    public Player getEnemy() {
        return enemy;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public int getAskedQuestions() {
        return askedQuestions;
    }

    public void setAskedQuestions(int askedQuestions) {
        this.askedQuestions = askedQuestions;
    }
}
