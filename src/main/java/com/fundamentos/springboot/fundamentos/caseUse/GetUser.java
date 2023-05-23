package com.fundamentos.springboot.fundamentos.caseUse;

import com.fundamentos.springboot.fundamentos.entity.User;

import java.util.List;

public interface GetUser {  //retribuye la lista de los usuarios
    List<User> getAll();
}
