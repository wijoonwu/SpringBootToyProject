package com.fastcampus.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastcampus.domain.User;
import com.fastcampus.persistence.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void insertUser(User user) {
		user.setRole("USER");
		userRepository.save(user);
	}
	
	@Transactional
	public User getUser(String username) {	
		Optional<User> findUser = userRepository.findUserByUsername(username);
		if(findUser.isPresent()) {
			return findUser.get();
		}
		return new User();		
	}


}
