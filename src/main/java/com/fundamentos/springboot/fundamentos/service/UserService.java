package com.fundamentos.springboot.fundamentos.service;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;      //inyectando dependencia

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional      //si existe algun error. nos permite hacer un rollback de todas las transacciones registradas en la base de datos
                        //, es decir q se debe registrar ttodo, o no permite q nada se registre
    public void saveTransaccional(List<User> users){
        users.stream()
                .peek(user -> LOG.info("Usuario insertado: "+user))        //mostrar en pantalla lo que se viene registrando
                .forEach(userRepository::save); // == forEach(user-> userRepository.save(user))
                    //aniadiendo a los usuarios de la lista users a userRepository
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();    //retornara todos los usuarios en una sola lista
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(User newUser, Long id) {
        return userRepository.findById(id)
                .map(
                        user -> {
                            user.setName(newUser.getName());
                            user.setEmail(newUser.getEmail());
                            user.setBirthDate(newUser.getBirthDate());
                            return userRepository.save(user);
                        }
                ).get();
    }
}
