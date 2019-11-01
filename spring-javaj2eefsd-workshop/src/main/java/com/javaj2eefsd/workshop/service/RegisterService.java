package com.javaj2eefsd.workshop.service;

import com.javaj2eefsd.workshop.model.User;

//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/**
* @author Nagarjuna - RegisterService interface used to validate the business logic and help to call the dao layer
*/
public interface RegisterService {
	
	//IDIOM:Item-0251: Remove redundant "public" from interface methods
	 User registerUser(User registeruser) throws Exception;
	 void activateUser(String emailId, Integer otp) throws Exception;
	 void sendEmail(String emailId, Integer otp) throws Exception;

}
