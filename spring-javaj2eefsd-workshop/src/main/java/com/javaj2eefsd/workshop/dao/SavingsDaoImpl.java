package com.javaj2eefsd.workshop.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.javaj2eefsd.workshop.api.ApiException;
import com.javaj2eefsd.workshop.model.Investments;
import com.javaj2eefsd.workshop.model.Savings;
import com.javaj2eefsd.workshop.util.PFMConstants;
import com.mongodb.WriteResult;
@Component
public class SavingsDaoImpl implements SavingsDao {
	@Autowired
    MongoTemplate mongoTemplate;

	@Override
	public Savings createSavings(Savings savingsObj) throws Exception {
		// TODO Auto-generated method stub
		try {
            mongoTemplate.save(savingsObj);
           // log.info("[createInvestments] Successfully saved data");
        }
        catch (final Exception e) {
        	//log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
		 return savingsObj;
			}
	
    public Savings updateSavings(Savings savingsObj, String userId) throws Exception {
		//log.info("[updateInvestments] Start updateInvestments method in DAO");
		final WriteResult result;
		try {
            final Query query = new Query();
           query.addCriteria(Criteria.where("savingsId").is(savingsObj.getSavingsId()));
            query.addCriteria(Criteria.where("isDelete").is(false));
            query.addCriteria(Criteria.where("createdBy").is(userId));
            final Update update = new Update();
            update.set("savingsAmount", savingsObj.getSavingsAmount());
           // update.set("investmentsType", investmentsObj.getInvestmentsType());
            update.set("savingsDate", savingsObj.getSavingsDate());
            update.set("updatedDate", savingsObj.getUpdatedDate());
            update.set("updatedBy", savingsObj.getUpdatedBy());
            result = mongoTemplate.updateFirst(query, update, Savings.class);
        }
        catch (final Exception e) {
        //	log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
		
		if (!result.isUpdateOfExisting()) {
           // log.info("[updateSavings] Something is wrong throwing exception");
            throw new ApiException(PFMConstants.ERROR_CODE, PFMConstants.INVALID_SAVINGSID);
        }
      //  log.info("[updateInvestments] Successfully updated data");
		
        return savingsObj;
	}
    
    @Override
    public void deleteSavings(String savingsId, String userId) throws Exception {
		//log.info("[deleteInvestments] Start deleteInvestments method in DAO");
		final WriteResult result;
		try {
            final Query query = new Query();
            query.addCriteria(Criteria.where("savingsId").is(savingsId));
            query.addCriteria(Criteria.where("isDelete").is(false));
            query.addCriteria(Criteria.where("createdBy").is(userId));
            final Update update = new Update();
            update.set("isDelete", true);
            result = mongoTemplate.updateFirst(query, update, Savings.class);
        }
        catch (final Exception e) {
        	//log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
		
		if (!result.isUpdateOfExisting()) {
           // log.info("[deleteInvestments] Something is wrong throwing exception");
            throw new ApiException(PFMConstants.ERROR_CODE, PFMConstants.INVALID_SAVINGSID);
        }
       // log.info("[deleteInvestments] Successfully deleted data");
	}
    public Savings getSavings(String savingsId, String userId) throws Exception {
	//	log.info("[getInvestments] Start getInvestments method in DAO");
		Savings savingsObj = null;
		try {
            final Query query = new Query();
            query.addCriteria(Criteria.where("savingsId").is(savingsId));
            query.addCriteria(Criteria.where("isDelete").is(false));
            query.addCriteria(Criteria.where("createdBy").is(userId));
            savingsObj = mongoTemplate.findOne(query, Savings.class);
          //  log.info("[getInvestments] Successfully executed query");
        }
        catch (final Exception e) {
        	//log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
		
		Optional.ofNullable(savingsObj).orElseThrow(() -> new ApiException(PFMConstants.ERROR_CODE, PFMConstants.INVALID_SAVINGSID));
		return savingsObj;
	}
    public List<Savings> getSavingsAll(String userId) throws Exception {
		//log.info("[getInvestmentsAll] Start getInvestmentsAll method in DAO");
		List<Savings> savingsList = null;
        try {
            final Query query = new Query();
            query.addCriteria(Criteria.where("isDelete").is(false));
            query.addCriteria(Criteria.where("createdBy").is(userId));
            savingsList = mongoTemplate.find(query, Savings.class);
         //   log.info("[getSavingsAll] Successfully executed query");
        }
        catch (final Exception e) {
        	//log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        
        return savingsList;
    }
}
