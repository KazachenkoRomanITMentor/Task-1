package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;


public class UserServiceImpl implements UserService {

    UserDao userDao;


    public void createUsersTable() {
        if (userDao == null){
        userDao = new UserDaoHibernateImpl();
        }
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        if (userDao == null){
            userDao = new UserDaoHibernateImpl();
        }
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        if (userDao == null){
            userDao = new UserDaoHibernateImpl();
        }
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        if (userDao == null){
            userDao = new UserDaoHibernateImpl();
        }
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        if (userDao == null){
            userDao = new UserDaoHibernateImpl();
        }
        return userDao.getAllUsers();

    }

    public void cleanUsersTable() {
        if (userDao == null){
            userDao = new UserDaoJDBCImpl();
        }
        userDao.cleanUsersTable();

    }
}
