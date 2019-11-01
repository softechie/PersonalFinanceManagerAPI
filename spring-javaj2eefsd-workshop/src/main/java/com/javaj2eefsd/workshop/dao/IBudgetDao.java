package com.javaj2eefsd.workshop.dao;

import org.json.simple.JSONArray;
import com.javaj2eefsd.workshop.model.DateRange;
//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/**
* @author sathsubramanian IBudgetDao class is used to connect java code in database and retrieve
*         and search budget data
*/
public interface IBudgetDao {

	//List<Budget> getAllBudget(DateRange body) throws Exception;
	JSONArray getAllBudget(DateRange body) throws Exception;

}
