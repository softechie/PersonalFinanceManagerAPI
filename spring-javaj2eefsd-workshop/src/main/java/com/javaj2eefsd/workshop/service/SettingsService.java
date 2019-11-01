package com.javaj2eefsd.workshop.service;

import com.javaj2eefsd.workshop.model.UserCurrency;
import com.javaj2eefsd.workshop.model.UserPassword;
import com.javaj2eefsd.workshop.model.UserSettingsByEmailId;

//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/**
* @author Nagarjuna - SettingsService interface used to validate the business logic and help to call the dao layer
*/
public interface SettingsService {
	UserSettingsByEmailId getSettings(String emailId) throws Exception;
	void  updateCurrency(UserCurrency usercurrencymodel) throws Exception;
	void  updatePassword(UserPassword userpasswordmodel) throws Exception;
	
}
