package com.javaj2eefsd.workshop.service;

import com.javaj2eefsd.workshop.model.Profile;

//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/**
* @author Nagarjuna - ProfileService interface used to validate the business logic and help to call the dao layer
*/
public interface ProfileService {

	/**
     * getIncomeAll method used to retrieve the income data from db
     *
     * @param incomeId
     * @return
     * @throws Exception
     */
	Profile getProfileDetails(String emailId) throws Exception;
	
	/**
	 * updateProfileDetails - this method is to update the user profile in Database
	 * 
	 *  @param prfoleObj
	 *  @return
	 *  @throws Exception
	 */
	
	Profile updateProfileDetails(Profile profileObj) throws Exception;

}
