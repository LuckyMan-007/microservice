package com.yon.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yon.auth.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
Optional<User> findByUserName(String userName);
}
