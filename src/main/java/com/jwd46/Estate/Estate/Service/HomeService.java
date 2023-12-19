package com.jwd46.Estate.Estate.Service;

import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.models.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    HomeDao homeDao;

    public void saveHomeToDB(MultipartFile file, String homeNo, String bedroom, String bathroom, String area, String location, String price, String property, String service, String photo) {

        Home home = new Home();
        home.setHomeNo(homeNo);
        home.setBedRoom(bedroom);
        home.setBathRoom(bathroom);
        home.setArea(area);
        home.setLocation(location);
        home.setPrice(price);
        home.setProperty(property);
        home.setService(service);
        home.setStatus(1);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a valid file");
        }
        try {
            home.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        homeDao.save(home);
    }


    public List<Home> getActiveHome() {
        return homeDao.findByActiveTrue();
    }

    public List<Home> search(String property, String location) {
        return homeDao.findByPropertyAndLocation(property, location);
    }

    public List<Home> searchByProperty(String property) {
        return homeDao.findByProperty(property);
    }

    public List<Home> searchByLocation(String location) {
        return homeDao.findByLocation(location);
    }

    public void updateHome(Home home, MultipartFile photoFile) {
        Home existingHome = homeDao.findById(home.getHomeId())
                .orElseThrow(() -> new RuntimeException("Home not found"));

        // Update other fields
        home.setBedRoom(existingHome.getBedRoom());
        home.setBathRoom(existingHome.getBathRoom());
        home.setArea(existingHome.getArea());
        home.setLocation(existingHome.getLocation());
        home.setPrice(existingHome.getPrice());
        home.setProperty(existingHome.getProperty());
        home.setService(existingHome.getService());

        // Update photo only if a new file is provided
        if (photoFile != null && !photoFile.isEmpty()) {
            try {
                String photoBase64 = Base64.getEncoder().encodeToString(photoFile.getBytes());
                home.setPhoto(photoBase64);
            } catch (IOException e) {
                // Log the exception or handle it more gracefully
                throw new RuntimeException("Error processing photo file", e);
            }
        }

        homeDao.save(home);
    }
}



