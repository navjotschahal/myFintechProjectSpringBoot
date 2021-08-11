package com.peerLending.securityApp.user.serviceImpl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.peerLending.securityApp.user.entity.User;

@Component
public class NotificationServiceImpl {

	private final RabbitTemplate rabbitTemplate;
	private static final Gson GSON = new Gson();

	/**
	 * @param rabbitTemplate
	 */
	@Autowired
	public NotificationServiceImpl(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(User user) {
		rabbitTemplate.convertAndSend("userRegisteredTopic", "user.registered", GSON.toJson(user));
	}

}
