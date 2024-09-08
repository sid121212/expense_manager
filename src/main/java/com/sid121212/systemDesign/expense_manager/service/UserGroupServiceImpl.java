package com.sid121212.systemDesign.expense_manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sid121212.systemDesign.expense_manager.entities.Group;
import com.sid121212.systemDesign.expense_manager.entities.User;
import com.sid121212.systemDesign.expense_manager.entities.UserGroup;
import com.sid121212.systemDesign.expense_manager.respository.GroupRepository;
import com.sid121212.systemDesign.expense_manager.respository.UserGroupRepository;
import com.sid121212.systemDesign.expense_manager.respository.UserRepository;

@Service
public class UserGroupServiceImpl implements UserGroupService{
	
	private UserGroupRepository userGroupRepo;
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    
	public UserGroupServiceImpl(UserGroupRepository userGroupRepo, UserRepository userRepository,
			GroupRepository groupRepository) {
		super();
		this.userGroupRepo = userGroupRepo;
		this.userRepository = userRepository;
		this.groupRepository = groupRepository;
	}

	@Override
	public void addUsersToGroup(Long groupId, List<Long> userIds) {
		Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
		for (Long id : userIds) {
			User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));
			UserGroup userGroup = new UserGroup();
			userGroup.setUser(user);
			userGroup.setGroup(group);
			userGroupRepo.save(userGroup);
		}
	}

	@Override
	public List<Group> getGroupsForUser(Long userId) {
		return userGroupRepo.findGroupsByUserId(userId);
	}

	@Override
	public List<User> getUsersInGroup(Long groupId) {
		return userGroupRepo.findUsersByGroupId(groupId);
	}



	

}
