package com.sid121212.systemDesign.expense_manager.service;

import org.springframework.stereotype.Service;

import com.sid121212.systemDesign.expense_manager.entities.Group;
import com.sid121212.systemDesign.expense_manager.respository.GroupRepository;

@Service
public class GroupServiceImpl implements GroupService{
	
	private GroupRepository groupRepo;
	

	public GroupServiceImpl(GroupRepository groupRepo) {
		super();
		this.groupRepo = groupRepo;
	}

	@Override
	public void createGroup(Group group) {
		groupRepo.save(group);
		
	}

	@Override
	public Group getGroup(Long id) {
		return groupRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteGroup(Long id) {
		groupRepo.deleteById(id);
		
	}

}
