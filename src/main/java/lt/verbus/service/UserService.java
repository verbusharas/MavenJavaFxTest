package lt.verbus.service;

import lt.verbus.dao.UserDao;
import lt.verbus.domain.entity.User;

import java.util.List;

public class UserService {

    UserDao userDao;

    public UserService(){
        userDao = new UserDao();
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

}
