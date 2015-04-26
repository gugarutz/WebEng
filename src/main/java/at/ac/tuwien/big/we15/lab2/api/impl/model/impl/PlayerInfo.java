package at.ac.tuwien.big.we15.lab2.api.impl.model.impl;

/**
 * Created by Raimund on 26.04.2015.
 */
public class PlayerInfo {
    private Info humanInfo;
    private Info enemyInfo;
    private String enemyChosenQuestion;

    public PlayerInfo() {
    }

    public Info getHumanInfo() {
        return humanInfo;
    }

    public void setHumanInfo(Info humanInfo) {
        this.humanInfo = humanInfo;
    }

    public Info getEnemyInfo() {
        return enemyInfo;
    }

    public void setEnemyInfo(Info enemyInfo) {
        this.enemyInfo = enemyInfo;
    }

    public String getEnemyChosenQuestion() {
        return enemyChosenQuestion;
    }

    public void setEnemyChosenQuestion(String enemyChosenQuestion) {
        this.enemyChosenQuestion = enemyChosenQuestion;
    }
}
