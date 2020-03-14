package com.epam.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.repository.UserRepository;
import com.epam.model.User;
import com.epam.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(users::add);
		return users;
	}

	@Override
	public void saveUsers(List<User> users){
		users.forEach(userRepository::save);
	}

}
