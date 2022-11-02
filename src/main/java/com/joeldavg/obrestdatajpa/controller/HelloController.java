package com.joeldavg.obrestdatajpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String holaMundo() {
        return "Hola Mundo que tal vamos!! Hasta luego!";
    }

}
