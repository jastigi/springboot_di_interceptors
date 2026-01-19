package com.jastigi.curso.springboot.app.interceptor.springboot_interceptor.interceptors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        logger.info("LoadingTimeInterceptor entrando Pre Handle: " + ((HandlerMethod) handler).getMethod().getName());

        long start = System.currentTimeMillis();
        request.setAttribute("startTime", start);

        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);

        Map<String, String> json = new HashMap<>();
        json.put("Error", "No tienes acceso a esta página.");
        json.put("Date", new Date().toString());

        ObjectMapper mapper = new ObjectMapper();
        String jsonError = mapper.writeValueAsString(json);
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(jsonError);

        return false;

        // return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        logger.info("Tiempo transcurrido: " + executionTime + "ms");

        logger.info("LoadingTimeInterceptor saliendo Post Handle: " + ((HandlerMethod) handler).getMethod().getName());

    }

}
