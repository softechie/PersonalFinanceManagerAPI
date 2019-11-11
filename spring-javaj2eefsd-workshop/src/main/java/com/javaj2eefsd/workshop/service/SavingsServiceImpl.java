package com.javaj2eefsd.workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.bp.OffsetDateTime;

import com.javaj2eefsd.workshop.dao.SavingsDao;
import com.javaj2eefsd.workshop.dao.SavingsDaoImpl;
import com.javaj2eefsd.workshop.model.Investments;
import com.javaj2eefsd.workshop.model.Savings;


@Service
public class SavingsServiceImpl implements SavingsService {

	@Autowired
	SavingsDao savingsdaoImpl;
	
	@Override
	public Savings createSavings(Savings savingsObj) throws Exception {
		// TODO Auto-generated method stub
		savingsObj.setCreatedDate(OffsetDateTime.now());
		savingsObj.setUpdatedDate(OffsetDateTime.now());
		savingsObj.setCreatedBy("1");
		savingsObj.setUpdatedBy(savingsObj.getCreatedBy());
		savingsObj.setIsDelete(false);
		return savingsdaoImpl.createSavings(savingsObj);
	}
	public Savings updateSavings(Savings savingsObj, String savingsId) throws Exception {
    	//log.info("[updatesavings] start updateSavings in Service");
    	savingsObj.setUpdatedDate(OffsetDateTime.now());
    	savingsObj.setCreatedBy("1");
//    	log.info("[IncomeServiceImpl] [updateInvestments] update some fields before update");
        return savingsdaoImpl.updateSavings(savingsObj, savingsId);
    }
	 public void deleteSavings(String savingsId, String userId) throws Exception {
	    //	log.info("[deleteInvestments] start deleteInvestments in Service");
	    	savingsdaoImpl.deleteSavings(savingsId, userId);
 }
	 public Savings getSavings(String savingsId, String userId) throws Exception {
	    	//log.info("[getSavings] start getSavings in Service");
			return savingsdaoImpl.getSavings(savingsId, userId);
	    }
	 public List<Savings> getSavingsAll(String userId) throws Exception {
			//log.info("[getInvestmentsAll] start getInvestmentsAll in Service");
			return savingsdaoImpl.getSavingsAll(userId);
	    }

}

