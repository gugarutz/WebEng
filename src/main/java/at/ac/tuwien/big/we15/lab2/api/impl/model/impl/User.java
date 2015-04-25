package at.ac.tuwien.big.we15.lab2.api.impl.model.impl;

public class User {

    private String name;
    private int ID;
    private int points = 0;

    public User() {
    }

    public User(int points) {
        this.points = points;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}