package com.peerLender.landingEngine.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peerLender.landingEngine.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
