package com.peerLending.securityApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.peerLending.securityApp.user.entity.User;
import com.peerLending.securityApp.user.repository.UserRepository;

@SpringBootApplication
public class SecurityAppApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SecurityAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			userRepository.save(new User("UserName" + i, "password"));
		}
	}

}
