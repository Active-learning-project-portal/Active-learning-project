package com.example.Active.Learning.project;

import com.example.Active.Learning.project.account.models.enums.ERole;
import com.example.Active.Learning.project.account.models.role.Role;
import com.example.Active.Learning.project.account.payload.request.UserRequest;
import com.example.Active.Learning.project.account.repositories.RoleRepository;
import com.example.Active.Learning.project.account.repositories.UserRepository;
import com.example.Active.Learning.project.account.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ActiveLearningProjectApplication implements CommandLineRunner {

	@Autowired
	private final RoleRepository roleRepository;

	@Autowired
	private final UserServiceImpl userRepository;
	public ActiveLearningProjectApplication(RoleRepository roleRepository, UserServiceImpl userRepository) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ActiveLearningProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role role = new Role();
		role.setName(ERole.ROLE_SUPER_ADMIN);
		roleRepository.save(role);

		Role role1 = new Role();
		role1.setName(ERole.ROLE_ADMIN);
		roleRepository.save(role1);

		Role role2 = new Role();
		role2.setName(ERole.ROLE_TRAINER);
		roleRepository.save(role2);

		Role role3 = new Role();
		role3.setName(ERole.ROLE_TRAINEE);
		roleRepository.save(role3);


		//Admin manual insert
		Set<Role> superAdmin = new HashSet<>();
       superAdmin.add(role);

		UserRequest  userRequest = new UserRequest();
		userRequest.setFirstname("super");
		userRequest.setLastname("admin");
		userRequest.setPassword("P@ssword1");
		userRequest.setUsername("super.admin@alp.com");
		userRequest.setJoined(new Date());
		userRequest.setRoles(superAdmin);
		userRepository.saveUser(userRequest);

		//Admin manual insert
		Set<Role> admin = new HashSet<>();
		admin.add(role1);

		UserRequest  adminRequest = new UserRequest();
		adminRequest.setFirstname("admin");
		adminRequest.setLastname("admin");
		adminRequest.setPassword("P@ssword1");
		adminRequest.setUsername("admin@alp.com");
		adminRequest.setJoined(new Date());
		adminRequest.setRoles(admin);
		userRepository.saveUser(adminRequest);

		//TRAINEE
		Set<Role> trainee = new HashSet<>();
		trainee.add(role3);

		UserRequest  traineeRequest = new UserRequest();
		traineeRequest.setFirstname("trainee");
		traineeRequest.setLastname("trainee");
		traineeRequest.setPassword("P@ssword1");
		traineeRequest.setUsername("trainee@alp.com");
		traineeRequest.setJoined(new Date());
		traineeRequest.setRoles(trainee);
		userRepository.saveUser(traineeRequest);

		//TRAINEER
		Set<Role> traineer = new HashSet<>();
		traineer.add(role2);

		UserRequest  traineerRequest = new UserRequest();
		traineerRequest.setFirstname("traineer");
		traineerRequest.setLastname("traineer");
		traineerRequest.setPassword("P@ssword1");
		traineerRequest.setUsername("traineer@alp.com");
		traineerRequest.setJoined(new Date());
		traineerRequest.setRoles(traineer);
		userRepository.saveUser(traineerRequest);
	}
}
