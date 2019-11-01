package com.javaj2eefsd.workshop.dao;

import com.javaj2eefsd.workshop.model.UserDetail;
//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/**
* @author Nagarjuna - LoginDao interface is used to connect java code in database and validate with data in db.
*       
*/
public interface LoginDao {
	
	//IDIOM:Item-0251: Remove redundant "public" from interface methods
	 UserDetail getLogin(UserDetail userdetail ) throws Exception;

}
