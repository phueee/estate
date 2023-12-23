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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    HomeDao homeDao;
    @Autowired
    HomeService service;

    @Autowired
    HomeDao dao;
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

    @PostMapping("/delete/home")
    public String deleteHome(@RequestParam int homeId) {
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
    public ModelAndView editPage(@PathVariable("homeId") int homeId) {
        Home home = homeDao.findById(homeId).orElseThrow();
        return new ModelAndView("adminEdit", "homeBean", home);
    }


//    @PostMapping("/home/update")
//    public String updateHome(Model model,@RequestParam ("photo")MultipartFile  photo,
//                             @ModelAttribute("homeBean") Home home) throws IOException {
//        home.setStatus(1);
//        byte[] bytes=photo.getBytes();
//        String enodedString= Base64.getEncoder().encodeToString(bytes);
//        home.setPhoto(enodedString);
//        homeDao.save(home);
//        model.addAttribute("fileName",enodedString);
//        return "redirect:/homes";
////        return "redirect:/homes/view";
//    }

    @GetMapping("/search")
    public String searchHomes() {
        return "view";
    }

    @PostMapping("/search")
    public String searchHomes(@RequestParam String property, String location, Model model) {
        List<Home> homeList;
        if (!property.trim().equals("Property Type") && !location.trim().equals("Location")) {
            homeList = service.search(property, location);
        } else if (!property.trim().equals("Property Type")) {
            homeList = service.searchByProperty(property);
//            System.out.println(property);
        } else {
            homeList = service.searchByLocation(location);
//            System.out.println(location);
        }
        model.addAttribute("homes", homeList);
        return "view";
    }

    @PostMapping("/home/update")
    public String updatePage(@RequestParam("homeId") int homeId,@RequestParam("homeNo")String homeNo,@RequestParam("bedRoom")String bedRoom,@RequestParam("bathRoom")String bathRoom,@RequestParam("area")String area,@RequestParam("location")String location,@RequestParam("price")String price,@RequestParam("property")String property,@RequestParam("service")String service,@RequestParam("photo")MultipartFile photo) throws IOException {
        Home home=dao.getHomeById(homeId);
        home.setHomeNo(homeNo);
        home.setBathRoom(bathRoom);
        home.setBedRoom(bedRoom);
        home.setPrice(price);
        home.setArea(area);
        home.setLocation(location);
        home.setService(service);
        home.setProperty(property);
        home.setLocation(location);
        byte[] bytes=photo.getBytes();
        String encodedString=Base64.getEncoder().encodeToString(bytes);
        home.setPhoto(encodedString);
        dao.save(home);
        return "redirect:/homes";
    }
}
