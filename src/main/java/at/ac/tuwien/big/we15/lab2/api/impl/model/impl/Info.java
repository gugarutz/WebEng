package at.ac.tuwien.big.we15.lab2.api.impl.model.impl;

/**
 * Created by Raimund on 26.04.2015.
 */
public class Info {
    private boolean correct;
    private boolean human;
    private boolean winner;
    private int money;
    private String name;

    public Info() {
    }

    public Info(boolean correct, boolean human, int money, String name) {
        this.correct = correct;
        this.human = human;
        this.money = money;
        this.name = name;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner() {
        this.winner = true;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isHuman() {
        return human;
    }

    public void setHuman(boolean human) {
        this.human = human;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String text = money > 0 ?
                String.format("%s %s %s geantwortet: %s %s &euro;", name, human ? "hast" : "hat", correct ? "richtig" : "falsch", correct ? "+" : "-", money) :
                String.format("%s %s noch nichts beantwortet.", name, human ? "hast" : "hat");

        text = winner ? String.format("%s %s gewonnen.", name, human ? "hast" : "hat") : text;

        return text;
    }
}
