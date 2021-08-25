package com.peerLender.landingEngine.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peerLender.landingEngine.application.enums.Status;
import com.peerLender.landingEngine.domain.model.LoanRequest;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {
	
	List<LoanRequest> findAllByStatusEquals(Status status);

}
