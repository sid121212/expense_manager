package com.sid121212.systemDesign.expense_manager.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sid121212.systemDesign.expense_manager.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}
