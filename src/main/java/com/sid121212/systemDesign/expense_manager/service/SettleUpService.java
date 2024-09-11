package com.sid121212.systemDesign.expense_manager.service;

import java.math.BigDecimal;
import java.util.Map;

public interface SettleUpService {

	void addSplit(Long expenseId, Map<Long, BigDecimal> payerAmountMap) throws Exception;

	Map<String, Object> getOwesAndOwedByUser(Long userId, Long groupId);



}
