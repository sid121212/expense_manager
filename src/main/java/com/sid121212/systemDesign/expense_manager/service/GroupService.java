package com.sid121212.systemDesign.expense_manager.service;

import com.sid121212.systemDesign.expense_manager.entities.Group;

public interface GroupService {
	public void createGroup(Group group);
	public Group getGroup(Long id);
	public void deleteGroup(Long id);
}
