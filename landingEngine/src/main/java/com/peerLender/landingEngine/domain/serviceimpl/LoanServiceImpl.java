package com.peerLender.landingEngine.domain.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peerLender.landingEngine.application.enums.Currency;
import com.peerLender.landingEngine.domain.exception.LoanNotFoundException;
import com.peerLender.landingEngine.domain.exception.UserNotFoundException;
import com.peerLender.landingEngine.domain.model.Loan;
import com.peerLender.landingEngine.domain.model.LoanRequest;
import com.peerLender.landingEngine.domain.model.Money;
import com.peerLender.landingEngine.domain.model.User;
import com.peerLender.landingEngine.domain.repository.LoanRepository;
import com.peerLender.landingEngine.domain.repository.LoanRequestRepository;
import com.peerLender.landingEngine.domain.repository.UserRepository;
import com.peerLender.landingEngine.domain.service.LoanService;

@Component
public class LoanServiceImpl implements LoanService {
	private final LoanRepository loanRepository;
	private final LoanRequestRepository loanRequestRepository;
	private final UserRepository userRepository;

	/**
	 * @param loanRepository
	 * @param loanRequestRepository
	 * @param userRepository
	 */
	@Autowired
	public LoanServiceImpl(LoanRepository loanRepository, LoanRequestRepository loanRequestRepository,
			UserRepository userRepository) {
		super();
		this.loanRepository = loanRepository;
		this.loanRequestRepository = loanRequestRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void acceptLoan(final long loanRequestId, final String lenderUserName) {
		User loanLenderUser = findUser(lenderUserName);
		LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
				.orElseThrow(() -> new LoanNotFoundException());
		User borrower = loanRequest.getBorrower();
		Money money = loanRequest.getLoanAmount();
		loanLenderUser.withDrawl(money);
		borrower.topUp(money);
		loanRepository.save(new Loan(loanLenderUser, loanRequest));
	}

	/**
	 * @param lenderUserName
	 * @return
	 * @throws UserNotFoundException
	 */
	private User findUser(final String lenderUserName) throws UserNotFoundException {
		return userRepository.findById(lenderUserName).orElseThrow(() -> new UserNotFoundException());
	}

	public List<Loan> findAllBrorrowedLoans(User borrower) {
		return loanRepository.findAllByBorrower(borrower);
	}

	public List<Loan> findAllLentLoans(User lender) {
		return loanRepository.findAllByLender(lender);
	}

	@Transactional
	public void repayLoan(final Money amountToRepay, final long loanId, final User borrower) {
		Loan borrowedLoan = loanRepository.findOneByIdAndBorrower(loanId, borrower)
				.orElseThrow(() -> new LoanNotFoundException());

		Money actualAmountPaid = amountToRepay.getAmount() > borrowedLoan.getAmountOwed().getAmount()
				? borrowedLoan.getAmountOwed()
				: amountToRepay;

		borrowedLoan.repay(actualAmountPaid);
	}

}
