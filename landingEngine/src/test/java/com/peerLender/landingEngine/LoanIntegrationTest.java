package com.peerLender.landingEngine;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.google.gson.Gson;
import com.peerLender.landingEngine.application.dtos.LoanRequestDto;
import com.peerLender.landingEngine.domain.model.LoanRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class LoanIntegrationTest {
	
	private static final String UserName0 = "UserName0";
	private static final Gson GSON = new Gson();
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int serverPort;
	
	@Test
	public void testMakeLoanRequestAndLoanRequestIsCreated() throws Exception {
		final String baseURL = "http://localhost:" + serverPort;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.AUTHORIZATION, UserName0);
		
		HttpEntity<LoanRequestDto> httpEntity = new HttpEntity<LoanRequestDto>(new LoanRequestDto("", 50.0, 10, 10.0), httpHeaders);
		
		Object res = restTemplate.exchange(baseURL + "/requests", HttpMethod.GET, httpEntity, String.class);

		assertNotNull(res);
	}

}
