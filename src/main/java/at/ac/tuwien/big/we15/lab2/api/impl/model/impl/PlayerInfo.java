package at.ac.tuwien.big.we15.lab2.api.impl.model.impl;

import at.ac.tuwien.big.we15.lab2.api.Question;

/**
 * Created by Raimund on 26.04.2015.
 */
public class PlayerInfo {
    private Info humanInfo;
    private Info enemyInfo;
    private EnemyQuestionChosen enemyChosenQuestion;

    public PlayerInfo() {

    }

    public PlayerInfo(String humanName, String enemyName) {
        this.humanInfo = new Info(false, true, 0, humanName);
        this.enemyInfo = new Info(false, false, 0, enemyName);
        this.enemyChosenQuestion = new EnemyQuestionChosen(null, enemyName);
    }

    public Info getHumanInfo() {
        return humanInfo;
    }

    public void setHumanInfo(boolean correct, int money) {
        this.humanInfo.setCorrect(correct);
        this.humanInfo.setMoney(money);
    }

    public Info getEnemyInfo() {
        return enemyInfo;
    }

    public void setEnemyInfo(boolean correct, int money) {
        this.enemyInfo.setCorrect(correct);
        this.enemyInfo.setMoney(money);
    }

    public EnemyQuestionChosen getEnemyChosenQuestion() {
        return enemyChosenQuestion;
    }

    public void setEnemyChosenQuestion(Question question) {
        this.enemyChosenQuestion.setQuestion(question);
    }
}
