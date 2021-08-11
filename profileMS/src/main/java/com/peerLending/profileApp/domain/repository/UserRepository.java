package com.peerLending.profileApp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peerLending.profileApp.domain.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
