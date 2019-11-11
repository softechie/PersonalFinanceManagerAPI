package com.javaj2eefsd.workshop.service;

import java.util.List;

import com.javaj2eefsd.workshop.model.Investments;
import com.javaj2eefsd.workshop.model.Savings;

public interface SavingsService {

	
	 Savings createSavings(Savings savingsObj) throws Exception;
	 Savings updateSavings(Savings savingsObj, String userId) throws Exception;
	 void deleteSavings(String savingsId, String userId) throws Exception;
	 Savings getSavings(String savingsId, String userId) throws Exception;
	 List<Savings> getSavingsAll(String userId) throws Exception;
}
