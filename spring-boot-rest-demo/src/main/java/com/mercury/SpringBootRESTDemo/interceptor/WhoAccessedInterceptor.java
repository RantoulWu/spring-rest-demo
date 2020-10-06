package com.mercury.SpringBootRESTDemo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@Component
public class WhoAccessedInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //spring security check permission
        System.out.println("WhoAccessedInterceptor : " +
                LocalDate.now() + " : " + request.getRemoteAddr());
        return super.preHandle(request, response, handler);
    }
}
