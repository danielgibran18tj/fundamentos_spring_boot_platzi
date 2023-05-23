package com.fundamentos.springboot.fundamentos.caseUse;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser {
    private UserService userService;        //repositorio donde estan todos los usuarios

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();   //retribuye tooodos los usuarios
    }
}
