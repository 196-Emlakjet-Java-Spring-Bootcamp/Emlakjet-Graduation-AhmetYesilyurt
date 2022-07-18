package com.ysl.emlakjet;

import com.ysl.commonservice.dao.EmlakUserRepository;
import com.ysl.commonservice.dto.request.EmlakUserSignupDTO;
import com.ysl.commonservice.enums.Role;
import com.ysl.commonservice.service.EmlakUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.ysl.commonservice.*")
@EnableJpaRepositories(basePackages = "com.ysl.commonservice.dao")
@ComponentScan(basePackages = "com.ysl.*")
@EntityScan("com.ysl.*")

public class EmlakjetApplication {

	@Autowired
	EmlakUserRepository emlakUserRepository;


	public static void main(String[] args) {
		SpringApplication.run(EmlakjetApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EmlakUserService userService) {
		return args -> {

			if (!emlakUserRepository.existsByEmail("admin@gmail.com")) {
				EmlakUserSignupDTO admin = new EmlakUserSignupDTO();
				admin.setFirstName("admin");
				admin.setLastName("admin");
				admin.setPasswordHash("123");
				admin.setEmail("admin@gmail.com");
				admin.setMobileNo("05380858698");
				admin.setRoleType(Role.CORPORATE.toString());;
				userService.signUpEmlakUser(admin);
			}
		};

	}






}
