package com.peerLending.securityApp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peerLending.securityApp.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
