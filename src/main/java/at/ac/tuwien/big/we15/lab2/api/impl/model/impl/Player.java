package at.ac.tuwien.big.we15.lab2.api.impl.model.impl;

import at.ac.tuwien.big.we15.lab2.api.Avatar;

/**
 * Created by Raimund on 26.04.2015.
 */
public class Player {
    private int id;
    private Avatar avatar;
    private String name;
    private int money;

    public Player() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
