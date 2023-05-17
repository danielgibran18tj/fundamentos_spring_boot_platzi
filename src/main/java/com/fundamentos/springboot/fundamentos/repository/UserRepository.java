package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository     //estereotipo usado para usar "UserRepository" como dependencia
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select u from User u WHERE u.email=?1 ")  //consulta jpql
    Optional<User> findByUserEmail(String email);   // encontrar usuario por email
}
