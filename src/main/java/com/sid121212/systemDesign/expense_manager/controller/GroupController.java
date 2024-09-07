package com.sid121212.systemDesign.expense_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sid121212.systemDesign.expense_manager.entities.Group;
import com.sid121212.systemDesign.expense_manager.service.GroupService;

@RestController
@RequestMapping("/group")
public class GroupController {
	
	private GroupService groupService;

	public GroupController(GroupService groupService) {
		super();
		this.groupService = groupService;
	}
	
	@GetMapping("/get_group/{id}")
	public ResponseEntity<?> getGroup(@PathVariable Long id){
		try {
			Group group = groupService.getGroup(id);
			if (group != null) {
                return new ResponseEntity<>(group, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Group not found", HttpStatus.NOT_FOUND);
            }
		} catch (Exception e) {
			return new ResponseEntity<>("Error retrieving group: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/create_group")
	public ResponseEntity<String> createGroup(@RequestBody Group group){
		try {
			groupService.createGroup(group);
			return new ResponseEntity<>("Group created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error creating group: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete_group/{id}")
	public ResponseEntity<String> deleteGroup(@PathVariable Long id){
		try {
			groupService.deleteGroup(id);
			return new ResponseEntity<>("Group deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error deleting group: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
