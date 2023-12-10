package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.models.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    HomeDao homeDao;


    @GetMapping("/homes")
    public String viewHomes (Model model){
        List<Home> homes=homeDao.findAll();
        model.addAttribute("homes", homes);
        return "homes";
    }


    @GetMapping("/delete/home/{homeId}")
    public String deleteHome(@PathVariable("homeId") int homeId){
        homeDao.deleteById(homeId);
        return "redirect:/homes";

    }



     @GetMapping("/adminEdit/home/{homeId}")
    public ModelAndView editPage(@PathVariable("homeId") int homeId){
        Home home=homeDao.findById(homeId).orElseThrow();
        return new ModelAndView("adminEdit","homeBean",home);
  }


//
//    @GetMapping("/adminEdit/{homeId}")
//    public ModelAndView editForm(@PathVariable("homeId") int homeId) {
//        Home home = homeDao.findById(homeId).orElse(null);
//        ModelAndView modelAndView = new ModelAndView("/adminEdit");
//        modelAndView.addObject("home", home);
//        return modelAndView;
//    }
//
//    @PostMapping("/adminEdit")
//    public String home(@ModelAttribute Home updatedHome) {
//        homeDao.save(updatedHome);
//        return "redirect:/homes";
//    }

    @PostMapping("/home/update")
    public String updateHome(@ModelAttribute("homeBean") Home home){
        homeDao.save(home);
        return "redirect:/homes";
    }


}
