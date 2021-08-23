package com.peerLender.landingEngine.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peerLender.landingEngine.domain.model.Loan;
import com.peerLender.landingEngine.domain.model.User;

public interface LoanRepository extends JpaRepository<Loan, Long> {
	
	List<Loan> findAllByBorrower(User borrower);
	List<Loan> findAllByLender(User lender);
	
	Optional<Loan> findOneByIdAndBorrower(Long id, User borrower);

}
