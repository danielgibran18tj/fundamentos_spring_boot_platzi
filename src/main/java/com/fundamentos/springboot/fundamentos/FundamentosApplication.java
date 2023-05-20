package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;	//dependencia inyectada
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;
	public FundamentosApplication(@Qualifier("componentTwoImplement")ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args){	// ejecuta todo lo que le indiquemos de nuestro proyecto
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional(){
		User test1 = new User("test1Transactional1", "testTransactional1@domain.com", LocalDate.now()/*fecha actual*/);
		User test2 = new User("test2Transactional1", "test2Transactional1@domain.com", LocalDate.now()/*fecha actual*/);
		User test3 = new User("Test3Transactional1", "testTransactional1@domain.com", LocalDate.now()/*fecha actual*/);
		User test4 = new User("Test4Transactional1", "test4Transactional1@domain.com", LocalDate.now()/*fecha actual*/);

		List<User> users = Arrays.asList(test1, test2, test3, test4);
		try {
			userService.saveTransaccional(users);
		}catch (Exception e){
			LOGGER.error("Esta es una excepcion dentro del metodo transaccional" + e);
		}

		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transaccional" + user));
	}

	private void getInformationJpqlFromUser(){
		/*LOGGER.info("Usuario con el metodo findByUserEmail"
				+ userRepository.findByUserEmail("marisol@domain.com")
				.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("use", Sort.by("id").ascending())
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort "+ user));

		userRepository.findByName("userJohn")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method " + user));

		LOGGER.info("Usuario con query method findByEmailAndName "
				+ userRepository.findByEmailAndName("marisol@domain.com", "userMarisol")
				.orElseThrow(()-> new RuntimeException("Usuario no encontrado por email ni por name")));

		userRepository.findByNameLike("%ser%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLike" + user));

		userRepository.findByNameOrEmail("Julie","karen@domain.com")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail" + user));*/

		userRepository.findByBirthDateBetween(LocalDate.of(2021,3,1),LocalDate.of(2021,7,30))
				.stream()
				.forEach(user -> LOGGER.info("Usuario con intervalo de fechas: " + user));

		/*userRepository.findByNameLikeOrderByIdDesc("user%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado desc "+user));

		userRepository.findByNameContainingOrderByIdAsc("ser")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con contenedor y ordenado asc "+user));*/

		//Reto cumplido
		userRepository.findByBirthDateBetweenAndNameContainingOrderByIdDesc(LocalDate.of(2021,3,1),LocalDate.of(2021,7,30),"ser")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con intervalo de fechas y conteniendo SER: " + user));

		LOGGER.info("El usuario a partir del named parameter es:  "
				+ userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 9, 8),"daniela@domain.com")
				.orElseThrow(()-> new RuntimeException("No se encontro el usuario a partir del named parameter")));
	}

	private void saveUsersInDataBase(){

		User user1 = new User("userJohn", "john@domain.com", LocalDate.of(2021, 3, 13));
		User user2 = new User("Julie", "julie@domain.com", LocalDate.of(2021, 12, 8));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
		User user4 = new User("userMarisol", "marisol@domain.com", LocalDate.of(2021, 6, 18));
		User user5 = new User("userKaren", "karen@domain.com", LocalDate.of(2021, 1, 1));
		User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
		User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
		User user8 = new User("userLuis", "luis@domain.com", LocalDate.of(2021, 2, 27));
		User user9 = new User("Paola", "paola@domain.com", LocalDate.of(2021, 4, 10));
		User user10 = new User("userDamita", "damita@domain.com", LocalDate.of(2021, 6, 28));

		List<User> list = Arrays.asList(user1, user2,user3,user4,user5,user6,user7,user8,user9,user10);
		list.stream().forEach(userRepository::save);
		//userRepository.saveAll(list);
	}
	private void ejemplosAnteriores(){
		componentDependency.saludar();  	//utilizando dependencia como implementacion
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + " - " + userPojo.getPassword() + " - " + userPojo.getAge());
		try {
			//ERROR
			int value = 10/0;
			LOGGER.debug("Mi valor: " + value);
		}catch (Exception e){
			LOGGER.error("Esto es un error al dividir por cero " + e.getMessage());
		}
	}
}
