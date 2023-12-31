package com.jwd46.Estate.Estate.controllers;

import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.daos.UserDao;
import com.jwd46.Estate.Estate.models.Home;
import com.jwd46.Estate.Estate.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {
    @Autowired
    UserDao userDao;
@Autowired
     HomeDao homeDao;

    @GetMapping("/")
    public  String homePage(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String viewPage(Model model) {


        List<Home> homes= homeDao.findAll();
        List<Home> homePreviews = new ArrayList<Home>();
        if (homes.size() > 6) {
            for (int i = 0;i < 6;i++){
                homePreviews.add(homes.get(i));
            }
        } else {
            homePreviews = homes;
        }
        model.addAttribute("homesPreview", homePreviews);
        model.addAttribute("homesCount", homes.size());


        List<Home> appartment= homeDao.findAllByProperty("Appartment");
        model.addAttribute("homesList", appartment);
        model.addAttribute("appartmentCount", appartment.size());

        model.addAttribute("title", "Home");

        List<Home> villa= homeDao.findAllByProperty("Villa");
        model.addAttribute("homesList", villa);
        model.addAttribute("villaCount", villa.size());

        List<Home> house= homeDao.findAllByProperty("House");
        model.addAttribute("homesList", house);
        model.addAttribute("houseCount", house.size());

        List<Home> office= homeDao.findAllByProperty("Office");
        model.addAttribute("homesList", office);
        model.addAttribute("officeCount", office.size());

        return "index";
    }
    @GetMapping("view")
    public String viewAbout(Model model) {
        List<Home> homes=homeDao.findAll();
        model.addAttribute("homes", homes);
        model.addAttribute("title", "About");
        return "view";
    }
    @GetMapping("appartment")
    public String viewAppartment(Model model) {
        List<Home> homes=homeDao.findAllByProperty("Appartment");
        model.addAttribute("homes", homes);
        model.addAttribute("title", "Appartment");
        return "property/appartment.html";
    }
    @GetMapping("villa")
    public String viewVilla(Model model){
        List<Home> homes=homeDao.findAllByProperty("Villa");
        model.addAttribute("homes", homes);
        model.addAttribute("villaCount", homes.size());
        model.addAttribute("title","Villa");
        return "property/villa.html";
    }
    @GetMapping("home")
    public String viewHome(Model model){
        List<Home> homes=homeDao.findAllByProperty("House");
        model.addAttribute("homes", homes);
        model.addAttribute("houseCount", homes.size());
        model.addAttribute("title","Home");
        return "property/home.html";
    }

    @GetMapping("office")
    public String viewOffice(Model model){
        List<Home> homes=homeDao.findAllByProperty("Office");
        model.addAttribute("homes", homes);
        model.addAttribute("officeCount", homes.size());
        model.addAttribute("title","Office");
        return "property/office.html";
    }

    @GetMapping("contact")
    public  String viewContact(Model model){
        model.addAttribute("title","Contact");
        return "contact.html";
    }

    @GetMapping("villadetail")
    public String viewVilladetail(Model model){
        return "property/villadetail";
    }


//    @GetMapping("userdetail")
//    public ModelAndView viewUserdetail(Model model, HttpSession session) {
//        User user = (User) session.getAttribute("userId");
//
//        if (user == null) {
//            // Handle the case when the user is not in the session
//            // Redirect to login or show an error message
//            return new ModelAndView("redirect:/login");
//        }
//
//        User userFromDao = userDao.findByUserEmail(user.getUserEmail());
//
//        if (userFromDao == null) {
//            // Handle the case when the user is not found in the database
//            // Redirect to an error page or show an error message
//            return new ModelAndView("error");
//        }
//
//        model.addAttribute("user", userFromDao);
//        model.addAttribute("title", "userdetail");
//        return new ModelAndView("userdetail", "useBean", userFromDao);
//    }
//


    @GetMapping("/usedetail/view")
    public  String viewUserDetail(Model model,HttpSession session){
//        model.addAttribute("title","userdetail");
        User user=(User) session.getAttribute("user");
        System.out.println("username"+user.getUserName());
        System.out.println("username"+user.getUserPhone());
        System.out.println("username"+user.getUserEmail());
        System.out.println("username"+user.getUserName());
        model.addAttribute("user",user);
        return "userdetail.html";
    }
    @GetMapping("Buy")
    public String viewBuy(Model model) {
        List<Home> homes = homeDao.findAllByService("Sale");
        model.addAttribute("homes", homes);
        model.addAttribute("villaCount", homes.size());
        model.addAttribute("title", "Buy");
        return "service/Buy.html";
    }

    @GetMapping("Rent")
    public String viewRent(Model model) {
        List<Home> homes = homeDao.findAllByService("Rent");
        model.addAttribute("homes", homes);
        model.addAttribute("RentCount", homes.size());
        model.addAttribute("title", "Rent");
        return "service/Rent.html";
    }


    @GetMapping("homedetails")
    public String viewHomeDetails(Model model){
        return "homedetails.html";
    }

    @GetMapping("qrcode")
    public String viewQrCode(Model model){
        return "qrcode.html";
    }

}
