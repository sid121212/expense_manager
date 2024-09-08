package com.sid121212.systemDesign.expense_manager.service;

import java.util.List;

import com.sid121212.systemDesign.expense_manager.entities.Group;
import com.sid121212.systemDesign.expense_manager.entities.User;

public interface UserGroupService {
//	public List<Group> getUserGroups();
//	public List<User> getGroupUsers();
//	public void deleteUserFromGroup();

	public void addUsersToGroup(Long groupId, List<Long> userIds);

	public List<Group> getGroupsForUser(Long userId);

	public List<User> getUsersInGroup(Long groupId);
}
