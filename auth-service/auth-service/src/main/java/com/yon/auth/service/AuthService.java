package com.yon.auth.service;

import org.springframework.stereotype.Service;

import com.yon.auth.config.exception.AuthException;
import com.yon.auth.dto.LoginRequest;
import com.yon.auth.dto.RegisterRequest;
import com.yon.auth.entity.User;
import com.yon.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final UserRepository userRepo;
	private final JwtService jwtService;
	
	public String register(RegisterRequest request) {
		System.out.println("enter Register:::::::::");
		if(userRepo.findByUserName(request.getUserName()).isPresent()) {
			throw new AuthException("User Already Exists !");
		}

		User user = new User();
		user.setUserName(request.getUserName());
		user.setPassword(request.getPassword());
		user.setRole("ROLE_USER");
		userRepo.save(user);
		return "User Register Successfully";
	}
	
	public String login(LoginRequest request) {
		
		User user = userRepo.findByUserName(request.getUserName())
				.orElseThrow(()-> new AuthException("User Not found"));
		if(!user.getPassword().equals(request.getPassword())) {
			throw new AuthException("invalid Password");
		}
		return jwtService.generateToken(user.getUserName());
	}
	
}
