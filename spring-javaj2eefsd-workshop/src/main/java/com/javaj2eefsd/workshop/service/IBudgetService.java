package com.javaj2eefsd.workshop.service;


import org.json.simple.JSONArray;
import com.javaj2eefsd.workshop.model.DateRange;

public interface IBudgetService {
	
	//List<Budget> getBudgetByDate(DateRange body) throws Exception;
	JSONArray getBudgetByDate(DateRange body) throws Exception;

}

