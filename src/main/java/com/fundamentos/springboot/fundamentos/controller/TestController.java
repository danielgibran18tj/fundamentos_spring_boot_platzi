package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;

@Controller     //estereotipo
@RestController
public class TestController {
    @RequestMapping("/")    //anotacion para aceptar todas solicitudad http
    //@RequestMapping("/test")

    @ResponseBody    //responder un cuerpo a nivel de servicio
    public ResponseEntity<String> function() {
        return new ResponseEntity<>("Hello from controller, probando actualizacion" , HttpStatus.OK);
    }
}
