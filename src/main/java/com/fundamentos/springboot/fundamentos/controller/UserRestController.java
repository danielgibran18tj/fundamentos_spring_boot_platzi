package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.caseUse.GetUser;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    //create, get, delete, update
    //crear , obtener, eliminar, actualizar
    private GetUser getUser;

    public UserRestController(GetUser getUser) {
        this.getUser = getUser;
    }
    @GetMapping("/")
    List<User> get(){
        return getUser.getAll();
    }
    //localhost:8081/app/api/users/
}
