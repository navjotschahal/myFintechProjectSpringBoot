package com.peerLender.landingEngine.domain.service;

import com.peerLender.landingEngine.domain.model.User;

public interface TokenValidationService {
	
	public User validateTokenAndGetUser(final String token);

}
