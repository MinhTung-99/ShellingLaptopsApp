package com.shelling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shelling.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{}
