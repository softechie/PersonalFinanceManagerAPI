package com.javaj2eefsd.workshop.service;


import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaj2eefsd.workshop.dao.IBudgetDao;
import com.javaj2eefsd.workshop.model.DateRange;

//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/**
* @author Nagarjuna - BudgetServiceImpl class used to validate the business logic and help to call the dao layer
*/
@Service
public class BudgetServiceImpl implements IBudgetService {
	
	@Autowired
    IBudgetDao budgetDaoImpl;
	@Override
	//public List<Budget> getBudgetByDate(DateRange body) throws Exception {
	public JSONArray getBudgetByDate(DateRange body) throws Exception {
		// TODO Auto-generated method stub
		//return null;
		return budgetDaoImpl.getAllBudget(body);
	}

}
