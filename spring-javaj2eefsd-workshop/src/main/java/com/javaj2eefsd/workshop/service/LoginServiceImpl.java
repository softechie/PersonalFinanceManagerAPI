package com.javaj2eefsd.workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.javaj2eefsd.workshop.dao.LoginDao;
import com.javaj2eefsd.workshop.model.UserDetail;
import com.javaj2eefsd.workshop.model.UserDetail;

//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/**
* @author Nagarjuna - LoginService interface used to validate the business logic and help to call the dao layer
*/
@Service
public class LoginServiceImpl implements LoginService {
	

	@Autowired
	LoginDao loginObj;
	
	@Autowired
    MongoTemplate mongoTemplate;

	@Override
	public UserDetail getLogin(UserDetail userdetail) throws Exception
	{
		
		return loginObj.getLogin(userdetail);
	
	}
	
	}
