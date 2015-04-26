package at.ac.tuwien.big.we15.lab2.api.impl.DAO.impl;


import at.ac.tuwien.big.we15.lab2.api.impl.DAO.IUserDao;
import at.ac.tuwien.big.we15.lab2.api.impl.model.impl.Player;

public class UserDAO implements IUserDao {
    public static int registeredUserCount = 0;

    @Override
    public Player getNewPlayer() {

        Player newUser = new Player();

        newUser.setId(++registeredUserCount);

        return newUser;
    }
}
