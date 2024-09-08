package com.sid121212.systemDesign.expense_manager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sid121212.systemDesign.expense_manager.entities.Group;
import com.sid121212.systemDesign.expense_manager.entities.User;
import com.sid121212.systemDesign.expense_manager.service.UserGroupService;

@RestController
@RequestMapping("/usersToGroup")
public class UserGroupController {
	
	private UserGroupService userGroupService;
	
	
	public UserGroupController(UserGroupService userGroupService) {
		super();
		this.userGroupService = userGroupService;
	}


	@PostMapping
	public ResponseEntity<String> addUsersToGroup(@RequestParam Long groupId, @RequestBody List<Long> userIds) {
	    try {
	        userGroupService.addUsersToGroup(groupId, userIds);
	        return new ResponseEntity<>("Users added to group successfully", HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error adding users to group: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping
	public ResponseEntity<?> getUserGroupData(
	        @RequestParam(required = false) Long groupId,
	        @RequestParam(required = false) Long userId) {
	    
	    try {
	        if (groupId != null) {
	            // Fetch users in a group when groupId is provided
	            List<User> users = userGroupService.getUsersInGroup(groupId);
	            return new ResponseEntity<>(users, HttpStatus.OK);
	        } else if (userId != null) {
	            // Fetch groups a user is in when userId is provided
	            List<Group> groups = userGroupService.getGroupsForUser(userId);
	            return new ResponseEntity<>(groups, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Either groupId or userId must be provided", HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	
}
