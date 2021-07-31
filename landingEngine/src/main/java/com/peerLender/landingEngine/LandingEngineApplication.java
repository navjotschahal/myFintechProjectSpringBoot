package com.peerLender.landingEngine;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.peerLender.landingEngine.application.enums.Occupation;
import com.peerLender.landingEngine.domain.model.User;
import com.peerLender.landingEngine.domain.repository.UserRepository;

@SpringBootApplication
public class LandingEngineApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LandingEngineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Random rand = new Random();
		Occupation[] occupations = Occupation.values();
		int numOfOccupations = occupations.length;
		for (int i = 0; i < 100; i++) {
			String borrowerOccupation = occupations[rand.nextInt(numOfOccupations)].toString();
			int age = rand.nextInt(100-18) + 18;
			userRepository.save(new User("UserName" + i, "fName" + i, "lName" + i, age, borrowerOccupation));
		}
	}

}
