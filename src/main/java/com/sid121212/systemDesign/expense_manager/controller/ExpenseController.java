package com.sid121212.systemDesign.expense_manager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sid121212.systemDesign.expense_manager.entities.Expense;
import com.sid121212.systemDesign.expense_manager.service.ExpenseService;

@RestController()
@RequestMapping("/expense")
public class ExpenseController {
	
	private final ExpenseService expenseService;

	public ExpenseController(ExpenseService expenseService) {
		super();
		this.expenseService = expenseService;
	}
	
	@PostMapping
	public ResponseEntity<String> addExpense(@RequestParam Long groupId,@RequestParam Long userId,@RequestBody Expense expense){
		try {
			expenseService.addExpense(groupId, userId, expense);
			return new ResponseEntity<>("Expense added successfully",HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error adding expenses: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{groupId}")
    public ResponseEntity<List<Expense>> getExpensesByGroup(@PathVariable Long groupId) {
        try {
            List<Expense> expenses = expenseService.getExpensesByGroup(groupId);
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
	
}
