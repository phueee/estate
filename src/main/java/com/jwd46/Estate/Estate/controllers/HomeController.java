package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.Service.HomeService;
import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.models.Home;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    HomeDao homeDao;
    @Autowired
    HomeService service;
//
//    @GetMapping("/homes")
//    public String viewHomes (Model model){
//        List<Home> homes=homeDao.findAll();
//        List<Home> listHome = new ArrayList<>();
//        for (Home home : homes){
//            if(home.isActive()==true){
//                listHome.add(home);
//            }
//        }
//        model.addAttribute("homes", listHome);
//        return "homes";
//    }




    @GetMapping("/delete/home/{homeId}")
    public String deleteHome(@PathVariable("homeId") int homeId){
        homeDao.deleteById(homeId);
        return "redirect:/homes";

    }

//    @GetMapping("/delete/home/{homeId}")
//    public String deleteHome(@PathVariable("homeId") int homeId) {
//        Home home = homeDao.findById(homeId).orElseThrow();
//        home.setActive(false);
//        homeDao.save(home);
//        return "redirect:/homes";
//    }



     @GetMapping("/adminEdit/home/{homeId}")
    public ModelAndView editPage(@PathVariable("homeId") int homeId){
        Home home=homeDao.findById(homeId).orElseThrow();
        return new ModelAndView("adminEdit","homeBean",home);
  }


    @PostMapping("/home/update")
    public String updateHome(@ModelAttribute("homeBean") Home home){
        homeDao.save(home);
        return "redirect:/homes";
//        return "redirect:/homes/view";
    }

    @GetMapping("/search")
    public String searchHomes(){
        return "view";
    }

    @PostMapping("/search")
    public String searchHomes(@RequestParam String property,String location,Model model){
        List<Home> homeList=service.search(property, location);
        model.addAttribute("homes", homeList);
      return "view";
    }
}
