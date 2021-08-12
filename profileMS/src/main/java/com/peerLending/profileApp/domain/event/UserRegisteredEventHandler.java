/**
 * 
 */
package com.peerLending.profileApp.domain.event;

import java.time.LocalDate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.peerLending.profileApp.application.gsonconv.LocalDateSerializer;
import com.peerLending.profileApp.domain.model.User;
import com.peerLending.profileApp.domain.repository.UserRepository;

/**
 * @author nsc
 *
 */
@Component
public class UserRegisteredEventHandler {
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private static final Gson GSON = new Gson();
	private final UserRepository userRepository;
	
	/**
	 * @param userRepository
	 */
	@Autowired
	public UserRegisteredEventHandler(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public void handleUserRegistration(String userDetails) {
		Gson gsonc = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).setPrettyPrinting().create();
		User user = gsonc.fromJson(userDetails, User.class);  // GSON.fromJson(userDetails, User.class);
		LOGGER.info("user {} registered ", user.getUserName());
		userRepository.save(user);
	}

}
