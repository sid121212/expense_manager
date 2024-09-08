package com.sid121212.systemDesign.expense_manager.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sid121212.systemDesign.expense_manager.entities.Expense;
import com.sid121212.systemDesign.expense_manager.entities.Group;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{

	List<Expense> findByGroup(Group group);

}
