package com.jwd46.Estate.Estate.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.jwd46.Estate.Estate.Service.UserService;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class UserController {


    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;

    @GetMapping("/signup")
    public String showSignGet(){
        return "signup";
    }

    @PostMapping("/signup")
    public String showSignPost(Model model, @RequestParam String name, String email, String phone, String NRC, String password, String confirmPassword, HttpServletRequest request) {
        if (name.isEmpty() && email.isEmpty() && phone.isEmpty() && NRC.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()){
            model.addAttribute("msg1", true);

            return "signup";
        }
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || NRC.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            model.addAttribute("msg1", true);

            return "signup";
        }
        if (password.length() < 8 || password.length() > 20){
            model.addAttribute("passwordLength", true);
            return "signup";
        }
        if (!password.equals(confirmPassword)) {
            model.addAttribute("showAlert", true);

            return "signup";
        } else {
            // Save the user or perform additional validation/logic
            User user = new User();
            user.setUserName(name);
            user.setUserEmail(email);
            user.setUserPhone(phone);
            user.setUserNrc(NRC);
            user.setUserPassword(BCrypt.withDefaults().hashToString(12, password.toCharArray()));
            user.setConfirmPassword(confirmPassword);
            request.getSession().setAttribute("userName", user.getUserName());
            userDao.save(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/user")
    public String User (Model model){
        List<User> users=userDao.findAll();
        List<User> listUser = new ArrayList<>();
        for (User user : users){
            if(user.isActive()==true){
                listUser.add(user);
            }
        }
        model.addAttribute("users",  listUser);
        return "user";
    }


    @PostMapping("/user")
    public String User(Model model, @RequestParam String userName){
        User user=new User();
        user.setUserName(userName);
        userDao.save(user);
        return "redirect:/user";
    }

    @GetMapping("/userEdit/user/{userId}")
    public ModelAndView editPage(@PathVariable("userId") int userId) {
        User user = userDao.findById(userId).orElseThrow();
        return new ModelAndView("userdetail", "userBean", user);
    }

    //@PostMapping("/delete")
    //public String delete(Model model, @RequestParam String userId){
    //  User user=new User();
    //dao.delete(user);
    //return "redirect:/user";





    @PostMapping("/delete/user")
    public String deleteUser(@RequestParam int userId) {
        User user = userDao.findById(userId).orElseThrow();
        user.setActive(false);
        userDao.save(user);
        return "redirect:/user";
    }
    @PostMapping("/user/update")
    public String updateUser(@Validated User user, @RequestParam("photo") MultipartFile photo, HttpSession session) throws IOException {
        int id = (Integer) session.getAttribute("userId");
        user.setUserId(id);
        userDao.save(user);
        return "redirect:/index";
    }

}