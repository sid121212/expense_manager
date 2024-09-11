package com.sid121212.systemDesign.expense_manager.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sid121212.systemDesign.expense_manager.entities.Expense;
import com.sid121212.systemDesign.expense_manager.entities.Group;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{

	List<Expense> findByGroup(Group group);

	@Query("SELECT e FROM Expense e WHERE e.paidBy.id = :userId AND e.group.id = :groupId")
    List<Expense> findByPaidByAndGroup(@Param("userId") Long userId, @Param("groupId") Long groupId);

	List<Expense> findByGroupId(Long groupId);


}
