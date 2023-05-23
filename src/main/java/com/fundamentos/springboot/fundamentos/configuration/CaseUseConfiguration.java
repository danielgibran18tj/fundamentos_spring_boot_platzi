package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.caseUse.GetUser;
import com.fundamentos.springboot.fundamentos.caseUse.GetUserImplement;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {
    @Bean   //se encargará de invocar ese método durante la inicialización de la aplicación
        // y registrará el objeto devuelto por el método como un bean administrado por Spring
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
