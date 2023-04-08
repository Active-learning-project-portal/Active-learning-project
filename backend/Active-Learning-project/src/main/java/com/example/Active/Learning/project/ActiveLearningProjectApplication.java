package com.example.Active.Learning.project;

import com.example.Active.Learning.project.account.models.enums.ERole;
import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.payload.request.UserRequest;
import com.example.Active.Learning.project.account.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.Optional;

@SpringBootApplication
public class ActiveLearningProjectApplication implements CommandLineRunner {



	@Autowired
	private final UserServiceImpl userRepository;
	public ActiveLearningProjectApplication(UserServiceImpl userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ActiveLearningProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



		//Admin manual insert

		UserRequest  userRequest = new UserRequest();
		userRequest.setFirstname("super");
		userRequest.setLastname("admin");
		userRequest.setPassword("P@ssword1");
		userRequest.setUsername("super.admin@alp.com");
		userRequest.setRoles(Collections.singleton(ERole.ROLE_SUPER_ADMIN));
		userRepository.saveUser(userRequest);

		//Admin manual insert
		UserRequest  adminRequest = new UserRequest();
		adminRequest.setFirstname("admin");
		adminRequest.setLastname("admin");
		adminRequest.setPassword("P@ssword1");
		adminRequest.setUsername("admin@alp.com");;
		adminRequest.setRoles(Collections.singleton(ERole.ROLE_ADMIN));
		userRepository.saveUser(adminRequest);

		//TRAINEE
		UserRequest  traineeRequest = new UserRequest();
		traineeRequest.setFirstname("trainee");
		traineeRequest.setLastname("trainee");
		traineeRequest.setPassword("P@ssword1");
		traineeRequest.setUsername("trainee@alp.com");
		traineeRequest.setRoles(Collections.singleton(ERole.ROLE_TRAINEE));
		userRepository.saveUser(traineeRequest);

		//TRAINEER

		UserRequest  traineerRequest = new UserRequest();
		traineerRequest.setFirstname("traineer");
		traineerRequest.setLastname("traineer");
		traineerRequest.setPassword("P@ssword1");
		traineerRequest.setUsername("traineer@alp.com");
		traineerRequest.setRoles(Collections.singleton(ERole.ROLE_TRAINER));
		userRepository.saveUser(traineerRequest);


		User user  =userRepository.findByUsername("traineer@alp.com");
		System.out.println(user);
	}
}
