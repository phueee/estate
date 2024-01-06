//package com.jwd46.Estate.Estate.filterconfig;
//
//import com.jwd46.Estate.Estate.filters.AdminFilter;
//import com.jwd46.Estate.Estate.filters.UserFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfiguration {
//
//    @Bean
//    public FilterRegistrationBean<UserFilter> userFilterFilterRegistrationBean(){
//        FilterRegistrationBean<UserFilter> registrationBean=new FilterRegistrationBean<UserFilter>();
//        registrationBean.setFilter(new UserFilter());
//        registrationBean.addUrlPatterns("/user/*");
//        return registrationBean;
//    }
//    @Bean
//    public FilterRegistrationBean<AdminFilter> adminFilterFilterRegistrationBean(){
//        FilterRegistrationBean<AdminFilter> registrationBean=new FilterRegistrationBean<AdminFilter>();
//        registrationBean.setFilter(new AdminFilter());
//        registrationBean.addUrlPatterns("/admin/*");
//        return registrationBean;
//    }
//
//}
