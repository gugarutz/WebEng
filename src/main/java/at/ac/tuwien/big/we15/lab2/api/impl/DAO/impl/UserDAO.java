package at.ac.tuwien.big.we15.lab2.api.impl.DAO.impl;

import at.ac.tuwien.big.we15.lab2.api.impl.DAO.IUserDAO;
import at.ac.tuwien.big.we15.lab2.api.impl.model.impl.User;

public class UserDAO implements IUserDAO {

    public static int registeredUserCount = 0;

    @Override
    public User getNewUser() {

        User newUser = new User();
        newUser.setID(++registeredUserCount);

        return newUser;
    }
}
