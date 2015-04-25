package at.ac.tuwien.big.we15.lab2.api.impl.DAO.impl;


import at.ac.tuwien.big.we15.lab2.api.impl.model.impl.User;

public class UserDAO  {
    public static int registeredUserCount = 0;


    public User getNewUser() {

        User newUser = new User();
        newUser.setID(++registeredUserCount);

        return newUser;
    }
}
