package com.javaj2eefsd.workshop.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaj2eefsd.workshop.model.ActivateUser;
import com.javaj2eefsd.workshop.service.RegisterService;
import com.javaj2eefsd.workshop.util.PFMConstants;

import io.swagger.annotations.ApiParam;
//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/***
 * This is ActivateUserApiController this class connect with ActivateUser module end point who where called
 *         in webservice give the response for activating the user details.
 */


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-06T07:36:56.089+05:30")

@CrossOrigin(origins = { "*" })
@Controller
public class ActivateUserApiController implements ActivateUserApi {

    private static final Logger log = LoggerFactory.getLogger(ActivateUserApiController.class);

  //IDIOM:Item-0008: Use descriptive Identifiers
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ActivateUserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    
    @Autowired
    RegisterService registerserviceObj;
    
    /***
     * activateUserLoginAccount method help to activate the registered user getting data from front end and call the activateUser service to activate
     * the user data in db
     */

    public ResponseEntity<ApiResponseMessage> activateUserLoginAccount(@ApiParam(value = "Activating user." ,required=true )  @Valid @RequestBody ActivateUser body) throws Exception {
    	//IDIOM:Item-0008: Use descriptive Identifiers
    	String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	registerserviceObj.activateUser(body.getEmailId() , body.getOtp());
            } catch (final IOException e) {
            	
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (final ApiException e) {
            	
            	log.error(e.getMessage());
            	return new ResponseEntity<ApiResponseMessage>(
            			new ApiResponseMessage(e.getCode(), e.getMessage()),
            			HttpStatus.BAD_REQUEST);
            } catch (final Exception e) {
            	
            	log.error(e.getMessage());
            	return new ResponseEntity<ApiResponseMessage>(
            			new ApiResponseMessage(PFMConstants.ERROR_CODE, PFMConstants.UNKNOWN_EXCEPTION),
            			HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(
        		PFMConstants.OK_CODE, PFMConstants.SUCCESS_USER_ACTIVATED),
        		HttpStatus.OK);
    }

}
