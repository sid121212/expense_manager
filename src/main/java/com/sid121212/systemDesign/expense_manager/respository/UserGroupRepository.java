package com.sid121212.systemDesign.expense_manager.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sid121212.systemDesign.expense_manager.entities.Group;
import com.sid121212.systemDesign.expense_manager.entities.User;
import com.sid121212.systemDesign.expense_manager.entities.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long>{
	
	@Query("SELECT ug.group from UserGroup ug where ug.user.id = :userId")
	List<Group> findGroupsByUserId(Long userId);
	
	@Query("SELECT ug.user FROM UserGroup ug WHERE ug.group.id = :groupId")	
	List<User> findUsersByGroupId(Long groupId);

}
