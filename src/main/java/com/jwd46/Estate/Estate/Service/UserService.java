package com.jwd46.Estate.Estate.Service;

import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User login(String email, String password) {

        return userDao.findByEmailAndPassword(email, password);
    }

    public void deleteUser(int userId){
        userDao.deleteById(userId);
    }

}
