package com.fundamentos.springboot.fundamentos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller     //estereotipo
//@RestController
public class TestController {
    @RequestMapping("/")    //anotacion para aceptar todas solicitudad http
    //@GetMapping
    @ResponseBody    //responder un cuerpo a nivel de servicio
    public ResponseEntity<String> function(){
        return new ResponseEntity<>("Hello from controller, probando actualizacion", HttpStatus.OK);
    }

}
