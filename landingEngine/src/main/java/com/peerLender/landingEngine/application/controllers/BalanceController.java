package com.peerLender.landingEngine.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peerLender.landingEngine.domain.model.Money;
import com.peerLender.landingEngine.domain.serviceimpl.BalanceServiceImpl;

@RestController
@RequestMapping("/balance")
public class BalanceController {
	
	private final BalanceServiceImpl balanceServiceImpl;

	/**
	 * @param balanceServiceImpl
	 */
	@Autowired
	public BalanceController(BalanceServiceImpl balanceServiceImpl) {
		super();
		this.balanceServiceImpl = balanceServiceImpl;
	}
	
	@PostMapping("/topup")
	private void topUp(final @RequestBody Money money, @RequestHeader String authorization) {
		balanceServiceImpl.topUpBalance(money, authorization);
	}
	
	@PostMapping("/withdrawal")
	private void withdrawal(final @RequestBody Money money, @RequestHeader String authorization) {
		balanceServiceImpl.withDrawlBalance(money, authorization);
	}

}
