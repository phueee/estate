package com.jwd46.Estate.Estate.Service;

import com.jwd46.Estate.Estate.daos.HomeDao;
import com.jwd46.Estate.Estate.models.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    HomeDao homeDao;

    public void saveHomeToDB(MultipartFile file,String homeNo,String bedroom,String bathroom,String area,String location,String price,String property,String service,String photo){

       Home home=new Home();
       home.setHomeNo(homeNo);
       home.setBedRoom(bedroom);
       home.setBathRoom(bathroom);
       home.setArea(area);
       home.setLocation(location);
       home.setPrice(price);
       home.setProperty(property);
       home.setService(service);
       home.setStatus(1);
       String fileName=StringUtils.cleanPath(file.getOriginalFilename());
       if (fileName.contains("..")){
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

    public List<Home> search(String property,String location){
        return homeDao.findByPropertyAndLocation(property,location);
    }
    public List<Home> searchByProperty(String property){
        return homeDao.findByProperty(property);
    }
    public List<Home> searchByLocation(String location){
        return homeDao.findByLocation(location);
    }

}
