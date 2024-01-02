package com.jwd46.Estate.Estate.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        if(request.getSession().getAttribute("userId")!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            response.sendRedirect("/login");
        }
    }
}
