package com.javaj2eefsd.workshop.dao;

import com.javaj2eefsd.workshop.model.User;

public interface RegisterDao {
	//IDIOM:Item-0251: Remove redundant "public" from interface methods
	 User registerUser(User registeruser) throws Exception;
	 void activateUser(String emailId, Integer otp) throws Exception;
	

}
