package com.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springdemo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByNameAndPassword(String name, String password);
}
