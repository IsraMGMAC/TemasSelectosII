package com.tienda.online.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DemoController {
    @GetMapping("test")
    public String testApp() {
        return "Hola";
    }
}