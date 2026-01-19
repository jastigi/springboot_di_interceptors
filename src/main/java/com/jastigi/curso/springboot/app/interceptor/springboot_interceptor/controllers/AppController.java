package com.jastigi.curso.springboot.app.interceptor.springboot_interceptor.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping("/foo")
    public Map<String, Object> foo() {
        return Collections.singletonMap("message", "Handler foo del controlador");
    }

    @GetMapping("/bar")
    public Map<String, Object> bar() {
        return Collections.singletonMap("message", "Handler bar del controlador");
    }

    @GetMapping("/baz")
    public Map<String, Object> baz() {
        return Collections.singletonMap("message", "Handler baz del controlador");
    }

}
