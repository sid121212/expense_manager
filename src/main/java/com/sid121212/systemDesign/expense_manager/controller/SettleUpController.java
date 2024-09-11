package com.sid121212.systemDesign.expense_manager.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sid121212.systemDesign.expense_manager.service.SettleUpService;

@RestController
@RequestMapping("/settleUp")
public class SettleUpController {
	
	private final SettleUpService settleUpService;

    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }
	
	@PostMapping
	public ResponseEntity<String> addSplit(@RequestParam Long expenseId, 
            @RequestBody Map<Long, BigDecimal> payerAmountMap){
		try {
            settleUpService.addSplit(expenseId, payerAmountMap);
            return new ResponseEntity<>("Split added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding split: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
	}
	
	@GetMapping
    public ResponseEntity<Map<String, Object>> getOwesAndOwed(@RequestParam Long groupId, @RequestParam Long userId) {
        try {
            Map<String, Object> result = settleUpService.getOwesAndOwedByUser(groupId, userId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
