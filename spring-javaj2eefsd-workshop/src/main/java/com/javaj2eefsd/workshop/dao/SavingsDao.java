package com.javaj2eefsd.workshop.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.javaj2eefsd.workshop.model.Investments;
import com.javaj2eefsd.workshop.model.Savings;

@Component
public interface SavingsDao {
	
	Savings createSavings(Savings savingsObj) throws Exception;
	Savings updateSavings(Savings savingsObj, String savingsId) throws Exception;
	 void deleteSavings(String savingsId, String userId) throws Exception;
	 Savings getSavings(String savingsId, String userId) throws Exception;
	  List<Savings> getSavingsAll(String userId) throws Exception;
}
