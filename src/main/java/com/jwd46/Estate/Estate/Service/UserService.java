package com.jwd46.Estate.Estate.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.jwd46.Estate.Estate.daos.AppoinmentDao;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.Appoinment;
import com.jwd46.Estate.Estate.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    AppoinmentDao appoinmentDao;

    public User login(String email, String password) {
        User user=userDao.findByUserEmail(email);
        if (user!=null){
            if (user.getUserEmail().equals(email) && BCrypt.verifyer().verify(password.toCharArray(),user.getUserPassword()).verified){
                return user;
            }else {
                return null;
            }
        }else {
            return null;
        }

    }

    public void deleteUser(int userId){
        userDao.deleteById(userId);
    }

    public List<User> getActiveUser() {
        return userDao.findByActiveTrue();
    }


    public void findByEmail(String email) {
        // Implement logic to retrieve user by email from your data source
        // ...
    }


    public User getByUserId(int userId) {
        return userDao.findByUserId(userId);
    }

    public User updatePage(User user) {
        return userDao.save(user);
    }

    public User Login(String userEmail, String userPassword) {

        return userDao.findByEmailAndPassword(userEmail, userPassword);
    }



    public void saveAppoinment(String name, String email, String phone, String reason, String comment, LocalDateTime dateTime) {

        Appoinment appoinment = new Appoinment();
        appoinment.setName(name);
        appoinment.setEmail(email);
        appoinment.setPhone(phone);
        appoinment.setReason(reason);
        appoinment.setComment(comment);
        appoinment.setDateTime(dateTime);
        appoinmentDao.save(appoinment);
    }


    public void saveAppointment(Appoinment appointment) {
        appoinmentDao.save(appointment);
    }



}