package com.sid121212.systemDesign.expense_manager.service;

import org.springframework.stereotype.Service;

import com.sid121212.systemDesign.expense_manager.entities.User;
import com.sid121212.systemDesign.expense_manager.respository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepo;
	
	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public void createUser(User user) {
		userRepo.save(user);
		
	}

	@Override
	public User getUser(Long id) {
		return userRepo.findById(id).orElse(null);
		
	}

	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}

}
