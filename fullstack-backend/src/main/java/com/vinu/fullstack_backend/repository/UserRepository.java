package com.vinu.fullstack_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinu.fullstack_backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
