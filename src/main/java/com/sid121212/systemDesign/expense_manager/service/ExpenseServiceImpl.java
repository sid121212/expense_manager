package com.sid121212.systemDesign.expense_manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sid121212.systemDesign.expense_manager.entities.Expense;
import com.sid121212.systemDesign.expense_manager.entities.Group;
import com.sid121212.systemDesign.expense_manager.entities.User;
import com.sid121212.systemDesign.expense_manager.respository.ExpenseRepository;
import com.sid121212.systemDesign.expense_manager.respository.GroupRepository;
import com.sid121212.systemDesign.expense_manager.respository.UserRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService{

	private ExpenseRepository expenseRepo;
	private GroupRepository groupRepo;
	private UserRepository userRepo;
	
	public ExpenseServiceImpl(ExpenseRepository expenseRepo, GroupRepository groupRepo, UserRepository userRepo) {
		super();
		this.expenseRepo = expenseRepo;
		this.groupRepo = groupRepo;
		this.userRepo = userRepo;
	}

	@Override
	public void addExpense(Long groupId,Long userId, Expense expense) throws Exception {
		Group group = groupRepo.findById(groupId).orElseThrow(() -> new Exception("Group not found"));
	    User user = userRepo.findById(userId).orElseThrow(() -> new Exception("User not found"));

		Expense newExpense = new Expense();
		newExpense.setAmount(expense.getAmount());
		newExpense.setGroup(group);
		newExpense.setPaidBy(user);
		newExpense.setReason(expense.getReason());
		expenseRepo.save(newExpense);
	}

	@Override
	public List<Expense> getExpensesByGroup(Long groupId) throws Exception {
		Group group = groupRepo.findById(groupId).orElseThrow(() -> new Exception("Group not found"));
	    return expenseRepo.findByGroup(group);
	}
	
	
	
}
