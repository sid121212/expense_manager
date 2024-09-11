package com.sid121212.systemDesign.expense_manager.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sid121212.systemDesign.expense_manager.entities.SettleUp;

public interface SettleUpRepository extends JpaRepository<SettleUp, Long>{

	@Query("SELECT s FROM SettleUp s WHERE s.expense.group.id = :groupId")
    List<SettleUp> findByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT s FROM SettleUp s WHERE s.payer.id = :userId AND s.expense.group.id = :groupId")
    List<SettleUp> findByPayerAndGroup(@Param("userId") Long userId, @Param("groupId") Long groupId);

    @Query("SELECT s FROM SettleUp s WHERE s.expense.paidBy.id = :userId AND s.expense.group.id = :groupId")
    List<SettleUp> findByPaidByAndGroup(@Param("userId") Long userId, @Param("groupId") Long groupId);



}
