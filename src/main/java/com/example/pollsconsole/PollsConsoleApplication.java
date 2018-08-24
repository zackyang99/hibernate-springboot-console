package com.example.pollsconsole;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.pollsconsole.exception.AppException;
import com.example.pollsconsole.model.Role;
import com.example.pollsconsole.model.RoleName;
import com.example.pollsconsole.model.User;
import com.example.pollsconsole.repository.RoleRepository;
import com.example.pollsconsole.repository.UserRepository;
import com.example.pollsconsole.service.PollService;

@SpringBootApplication
public class PollsConsoleApplication implements CommandLineRunner {
	private static Logger logger = LoggerFactory.getLogger(PollsConsoleApplication.class);
	PollService pollService;
	RoleRepository roleRepository;
	UserRepository userRepository;
	
	
	@Autowired
	public void setPollService(PollService pollService) {
		this.pollService = pollService;
	}
	
	@Autowired
	public void setRoleRepository( RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(PollsConsoleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setName("yang ziqiang");
		user.setUsername("yang2");
		user.setEmail("y@gmail.com");
		user.setPassword("test");
		
		Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
				.orElseThrow(() -> new AppException("Role not found"));
		
		

		
		user.setRoles(Collections.singleton(userRole));
		
		User result = userRepository.save(user);
		
		
	}
}
