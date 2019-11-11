package com.javaj2eefsd.workshop.service;

import com.javaj2eefsd.workshop.model.UserDetail;

//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/**
* @author Nagarjuna - LoginService interface used to validate the business logic and help to call the dao layer
*/
public interface LoginService {
	//IDIOM:Item-0251: Remove redundant "public" from interface methods
     UserDetail getLogin(UserDetail userdetail) throws Exception;

}
