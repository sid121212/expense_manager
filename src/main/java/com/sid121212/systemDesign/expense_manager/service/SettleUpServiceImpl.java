package com.sid121212.systemDesign.expense_manager.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sid121212.systemDesign.expense_manager.entities.Expense;
import com.sid121212.systemDesign.expense_manager.entities.SettleUp;
import com.sid121212.systemDesign.expense_manager.entities.User;
import com.sid121212.systemDesign.expense_manager.respository.ExpenseRepository;
import com.sid121212.systemDesign.expense_manager.respository.SettleUpRepository;
import com.sid121212.systemDesign.expense_manager.respository.UserRepository;

@Service
public class SettleUpServiceImpl implements SettleUpService{
	
	private SettleUpRepository settleUpRepo;
	private ExpenseRepository expenseRepo;
	private UserRepository userRepo;

	public SettleUpServiceImpl(SettleUpRepository settleUpRepo, ExpenseRepository expenseRepo,
			UserRepository userRepo) {
		super();
		this.settleUpRepo = settleUpRepo;
		this.expenseRepo = expenseRepo;
		this.userRepo = userRepo;
	}

	@Override
	public void addSplit(Long expenseId, Map<Long, BigDecimal> payerAmountMap) throws Exception {
		Expense expense = expenseRepo.findById(expenseId).orElseThrow(() -> new Exception("Expense not found"));
        
        for (Map.Entry<Long, BigDecimal> entry : payerAmountMap.entrySet()) {
            Long payerId = entry.getKey();
            BigDecimal amount = entry.getValue();
            
            User payer = userRepo.findById(payerId).orElseThrow(() -> new Exception("Payer not found"));
            
            SettleUp settleUp = new SettleUp();
            settleUp.setExpense(expense);
            settleUp.setPayer(payer);
            settleUp.setAmount(amount);
            
            settleUpRepo.save(settleUp);
        }
    }

	@Override
	public Map<String, Object> getOwesAndOwedByUser(Long userId, Long groupId) {
        
		List<SettleUp> settleUps = settleUpRepo.findByPayerAndGroup(userId, groupId);
		List<SettleUp> owedBy = settleUpRepo.findByPaidByAndGroup(userId, groupId);

		Map<String,BigDecimal> owedMap = new HashMap<>();
		for (SettleUp split:settleUps) {
			System.out.println(split.toString());
			User userWhopaid = split.getExpense().getPaidBy();
			if (owedMap.containsKey(userWhopaid.getName())) {
				owedMap.put(userWhopaid.getName(), owedMap.get(userWhopaid.getName()).add(split.getAmount()));
			}else {
				owedMap.put(userWhopaid.getName(),split.getAmount());
			}
		}
//		owedMap.forEach((key,value) -> {
//			System.out.println("Key="+key+" Value="+value);
//		});
        Map<String, Object> result = new HashMap<>();
//        result.put("owed", oweMap);
        
        Map<String,BigDecimal> owesMap = new HashMap<>();
        for (SettleUp split:owedBy) {
        	if (owesMap.containsKey(split.getPayer().getName())) {
        		owesMap.put(split.getPayer().getName(), owesMap.get(split.getPayer().getName()).add(split.getAmount()));
        	}else {
        		owesMap.put(split.getPayer().getName(),split.getAmount());
			}
//        	System.out.println(split.toString());
        }
        result.put("owedBy", owesMap);  // What the user owes to others
        result.put("owedTo", owedMap);
        
        Map<String, BigDecimal> toPay = new HashMap<>();

        owedMap.forEach((key, value) -> {
            BigDecimal temp = value;
            if (owesMap.containsKey(key)) {
                // Perform subtraction and assign the result to 'temp'
                temp = temp.subtract(owesMap.get(key));
            }
            // Add to toPay only if temp is greater than zero
            if (temp.compareTo(BigDecimal.ZERO) > 0) {
                toPay.put(key, temp);
            }
        });

        result.put("toPay", toPay);
        return result;
    }
		
	
	
}
