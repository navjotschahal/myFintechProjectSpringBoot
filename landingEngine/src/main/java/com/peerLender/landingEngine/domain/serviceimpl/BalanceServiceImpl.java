package com.peerLender.landingEngine.domain.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peerLender.landingEngine.domain.exception.UserNotFoundException;
import com.peerLender.landingEngine.domain.model.Money;
import com.peerLender.landingEngine.domain.model.User;
import com.peerLender.landingEngine.domain.repository.UserRepository;
import com.peerLender.landingEngine.domain.service.BalanceService;

@Component
public class BalanceServiceImpl implements BalanceService {
	
	private final UserRepository userRepository;

	/**
	 * @param userRepository
	 */
	@Autowired
	public BalanceServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void topUpBalance(final Money money, String authToken) {
		User user = findUser(authToken);
		user.topUp(money);
	}

	@Transactional
	public void withDrawlBalance(final Money money, String authToken) {
		User user = findUser(authToken);
		user.withDrawl(money);
	}
	
	private User findUser(String authToken) {
		return userRepository.findById(authToken).orElseThrow(() -> new UserNotFoundException(authToken));
	}

}
