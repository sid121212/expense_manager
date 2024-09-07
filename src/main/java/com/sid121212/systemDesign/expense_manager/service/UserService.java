package com.sid121212.systemDesign.expense_manager.service;

import com.sid121212.systemDesign.expense_manager.entities.User;

public interface UserService {
	
	public void createUser(User user);
	public User getUser(Long id);
	public void deleteUser(Long id);
}
