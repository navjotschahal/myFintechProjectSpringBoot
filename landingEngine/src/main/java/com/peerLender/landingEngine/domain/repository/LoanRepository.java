package com.peerLender.landingEngine.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peerLender.landingEngine.domain.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
