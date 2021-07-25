package com.peerLender.landingEngine.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peerLender.landingEngine.domain.model.LoanRequest;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {

}
