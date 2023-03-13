package com.example.Active.Learning.project;

import com.example.Active.Learning.project.account.models.enums.ERole;
import com.example.Active.Learning.project.account.models.role.Role;
import com.example.Active.Learning.project.account.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActiveLearningProjectApplication implements CommandLineRunner {

	@Autowired
	private final RoleRepository roleRepository;
	public ActiveLearningProjectApplication(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
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
	}
}
