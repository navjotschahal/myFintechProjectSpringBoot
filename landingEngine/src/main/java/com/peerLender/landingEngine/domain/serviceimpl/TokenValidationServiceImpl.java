package com.peerLender.landingEngine.domain.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.peerLender.landingEngine.domain.exception.UserNotFoundException;
import com.peerLender.landingEngine.domain.model.User;
import com.peerLender.landingEngine.domain.repository.UserRepository;
import com.peerLender.landingEngine.domain.service.TokenValidationService;

@Component
public class TokenValidationServiceImpl implements TokenValidationService {

	private final UserRepository userRepsitory;
	private final RestTemplate restTemplate = new RestTemplate();
	private final String securityServiceBaseURL;

	/**
	 * @param userRepsitory
	 */
	@Autowired
	public TokenValidationServiceImpl(UserRepository userRepsitory,
			@Value("${service.securityservice.baseUrl}") String securityServiceBaseURL) {
		super();
		this.userRepsitory = userRepsitory;
		this.securityServiceBaseURL = securityServiceBaseURL;
	}

	public User validateTokenAndGetUser(final String token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
		HttpEntity httpEntity = new HttpEntity(httpHeaders);

		ResponseEntity<String> responseEntity = restTemplate.exchange(securityServiceBaseURL + "/user/validate",
				HttpMethod.GET, httpEntity, String.class);
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			return userRepsitory.findById(responseEntity.getBody())
					.orElseThrow(() -> new UserNotFoundException(responseEntity.getBody()));
		}

		throw new RuntimeException("Token " + token + " is INVALID.");
	}

}
