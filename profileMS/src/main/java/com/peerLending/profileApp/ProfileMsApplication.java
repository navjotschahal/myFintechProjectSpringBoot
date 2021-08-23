package com.peerLending.profileApp;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.peerLending.profileApp.domain.model.User;
import com.peerLending.profileApp.domain.repository.UserRepository;

@SpringBootApplication
public class ProfileMsApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProfileMsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			userRepository.save(new User("UserName" + i, "fName" + i, "lName" + i, 45, "occupation"));
		}
		
	}

}
