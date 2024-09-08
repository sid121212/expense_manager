package com.sid121212.systemDesign.expense_manager.service;

import java.util.List;

import com.sid121212.systemDesign.expense_manager.entities.Expense;

public interface ExpenseService {
	public void addExpense(Long groupId,Long userId, Expense expense) throws Exception;

	public List<Expense> getExpensesByGroup(Long groupId) throws Exception;
}
