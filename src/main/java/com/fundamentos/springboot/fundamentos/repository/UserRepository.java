package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository     //estereotipo usado para usar "UserRepository" como dependencia
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u WHERE u.email=?1 ")  //consulta jpql
    Optional<User> findByUserEmail(String email);   // encontrar usuario por email

    @Query("Select u from User u where u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    //USO DE QUERY METHODS
    List<User> findByName(String name);
    Optional<User> findByEmailAndName(String email, String name);
    List<User> findByNameLike(String name);
    List<User> findByNameOrEmail(String name, String email);
    List<User> findByBirthDateBetween (LocalDate begin, LocalDate end);
    List<User> findByNameLikeOrderByIdDesc(String name);
    List<User> findByNameContainingOrderByIdAsc(String name);
    //Reto cumplido
    List<User> findByBirthDateBetweenAndNameContainingOrderByIdDesc (LocalDate begin, LocalDate end, String name);

    @Query("SELECT new com.fundamentos.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthDate)"
        + " FROM User u " + " where u.birthDate=:parametroFecha "
        + " and u.email=:parametroEmail ")      //cada espacio y letra es importante porque es la direccion que se le esta dando al JPQL
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date,
                                                @Param("parametroEmail") String email);
}
