package com.jwd46.Estate.Estate.Service;

import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.models.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class HomeService {

    @Autowired
    HomeDao homeDao;

    public void saveHomeToDB(MultipartFile file,String homeNo,String bedRoom,String bathRoom,String area,String location,String price,String property,String service,String photo){
        Home home=new Home();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println("not a a valid file");
        }
        home.setHomeNo(homeNo);
        home.setBedRoom(bedRoom);
        home.setBathRoom(bathRoom);
        home.setArea(area);
        home.setLocation(location);
        home.setPrice(price);
        home.setProperty(property);
        home.setService(service);
        try {
            home.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        homeDao.save(home);
    }
}