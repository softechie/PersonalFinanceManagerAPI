package com.javaj2eefsd.workshop.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaj2eefsd.workshop.model.Investments;
import com.javaj2eefsd.workshop.model.Savings;
import com.javaj2eefsd.workshop.service.InvestmentsService;
import com.javaj2eefsd.workshop.service.SavingsService;
import com.javaj2eefsd.workshop.util.PFMConstants;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-06T07:36:56.089+05:30")

@Controller
@CrossOrigin(origins = { "*" })
public class SavingsApiController implements SavingsApi {
	 private static final Logger log = LoggerFactory.getLogger(InvestmentsApiController.class);

	    private final ObjectMapper objectMapper;

	    private final HttpServletRequest request;
	    
	    @Autowired
	    SavingsService SavingsServiceImpl;

	    @org.springframework.beans.factory.annotation.Autowired
	    public SavingsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
	        this.objectMapper = objectMapper;
	        this.request = request;
	    }

	    public ResponseEntity<ApiResponseMessage> addSavingsAmount(
	    		@ApiParam(value = "Savings object that needs to be added to the store" ,required=true )  @Valid @RequestBody Savings body)
	    		throws Exception {
	    	
	        final String accept = request.getHeader("Accept");
	        
	        if (accept != null && accept.contains("application/json")) {
	            try {
	               SavingsServiceImpl.createSavings(body);
	            }
	            catch (final IOException e) {
	            	
	                log.error("Couldn't serialize response for content type application/json", e);
	                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	            } catch (final Exception e) {
	            	
	            	log.error(e.getMessage());
	            	return new ResponseEntity<ApiResponseMessage>(
	            			new ApiResponseMessage(PFMConstants.ERROR_CODE, PFMConstants.UNKNOWN_EXCEPTION),
	            			HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	        } else {
	        	return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	        }
	        
	        return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(
	        		PFMConstants.OK_CODE, PFMConstants.SUCCESS_SAVINGS_ADD),
	        		HttpStatus.OK);
	    }

	    public ResponseEntity<ApiResponseMessage> updateSavings(
	    		@ApiParam(value = "Savings object that needs to be updated to the store" ,required=true )  @Valid @RequestBody Savings body)
	    		throws Exception {
	    	
	        final String accept = request.getHeader("Accept");
	        
	        if (accept != null && accept.contains("application/json")) {
	            try {
	            	//TODO: add user model instead of hard coding
	                final String savingsId = "1";
	            	SavingsServiceImpl.updateSavings(body, savingsId);
	            }
	            catch (final IOException e) {
	            	
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
	        } else {
	        	return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	        }
	        
	        return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(
	        		PFMConstants.OK_CODE, PFMConstants.SUCCESS_SAVINGS_UPDATE),
	        		HttpStatus.OK);
	    }
	    
	    public ResponseEntity<ApiResponseMessage> deleteSavings(
	    		@ApiParam(value = "Savings id to delete",required=true) @PathVariable("savingsId") String savingsId)
	    		throws Exception {
	        
	    	final String accept = request.getHeader("Accept");
	        
	        try {
	        	//TODO: add user model instead of hard coding
	            final String userId = "1";
	        	SavingsServiceImpl.deleteSavings(savingsId, userId);
	        }
	        catch (final ApiException e) {
	        	
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
	        
	        return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(
	        		PFMConstants.OK_CODE, PFMConstants.SUCCESS_INVESTMENTS_DELETE),
	        		HttpStatus.OK);
	    }

	    
	    public ResponseEntity<?> getSavings(
	    		@ApiParam(value = "id to search for savings",required=true) @PathVariable("savingsId") String savingsId)
	    		throws Exception {
	    	
	        final String accept = request.getHeader("Accept");
	        Savings savingsObj = null;
	        
	        try {
	        	//TODO: add user model instead of hard coding
	        	final String userId = "1";
	        	savingsObj = SavingsServiceImpl.getSavings(savingsId, userId);
	        	return new ResponseEntity<Savings>(savingsObj, HttpStatus.OK);
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
	    
	    public ResponseEntity<?> getSavingsList() throws Exception {
	        
	    	final String accept = request.getHeader("Accept");
	        List<Savings> savingsList = null;
	        
	    	try {
	    		//TODO: add user model instead of hard coding
	            final String userId = "1";
	            savingsList = SavingsServiceImpl.getSavingsAll(userId);
	        }
	    	catch (final IOException e) {
	        	
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

	        return new ResponseEntity<List<Savings>>(savingsList, HttpStatus.OK);
	    }
}
