package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.caseUse.CreateUser;
import com.fundamentos.springboot.fundamentos.caseUse.DeleteUser;
import com.fundamentos.springboot.fundamentos.caseUse.GetUser;
import com.fundamentos.springboot.fundamentos.caseUse.UpdateUser;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     //permite que todos los metodos de aqui se formateen con formato JSON
@RequestMapping("/api/users")
public class UserRestController {
    //create, get, delete, update
    //crear , obtener, eliminar, actualizar
    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;

    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
    }

    @GetMapping("/")    //obtener usuarios
    List<User> get(){
        return getUser.getAll();
    }

    @PostMapping("/")   //agregar usuario
    ResponseEntity<User> newUser(@RequestBody User newUser){
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")     //eliminar usuario
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);   //no responderemos practicamente nada
    }

    @PutMapping("/{id}")        //modificar usuario
    ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id){
        return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK);
    }

}    //localhost:8081/app/api/users/

