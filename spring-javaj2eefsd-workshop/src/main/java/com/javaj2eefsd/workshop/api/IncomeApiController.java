package com.javaj2eefsd.workshop.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaj2eefsd.workshop.model.Income;
import com.javaj2eefsd.workshop.service.IncomeService;
import com.javaj2eefsd.workshop.util.PFMConstants;

import io.swagger.annotations.ApiParam;
//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/***
 *  This is IncomeApiController this class connect with income module end point who where called
 *         in webservice give the response for create ,update.delete,retrieve and search the income details.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-06T07:36:56.089+05:30")

@Controller
@CrossOrigin(origins = { "*" })
public class IncomeApiController implements IncomeApi {

    private static final Logger log = LoggerFactory.getLogger(IncomeApiController.class);
  //IDIOM:Item-0008: Use descriptive Identifiers
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
     
    //object for income service
    @Autowired
    IncomeService incomeServiceImpl;

    @org.springframework.beans.factory.annotation.Autowired
    public IncomeApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    /***
     * addIncome method help to create new income getting data from front end and call the income service to create
     * new income data in db
     */
    public ResponseEntity<ApiResponseMessage> addIncome(
    		@ApiParam(value = "Income object that needs to be added to the store" ,required=true )  @Valid @RequestBody Income body)
    		throws Exception {
    	
        final String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
            	incomeServiceImpl.createIncome(body);
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
        		PFMConstants.OK_CODE, PFMConstants.SUCCESS_INCOME_ADD),
        		HttpStatus.OK);
    }

    /**
     * deleteIncome method help to delete income data based on user inputs and this only soft deleted the income data
     * and call the income service to delete the data in db
     */
    public ResponseEntity<ApiResponseMessage> deleteIncome(
    		@ApiParam(value = "Income id to delete",required=true) @PathVariable("incomeId") String incomeId)
    		throws Exception {
    	
        final String accept = request.getHeader("Accept");
        
        try {
        	//TODO: add user model instead of hard coding
            final String userId = "1";
        	incomeServiceImpl.deleteIncome(incomeId, userId);
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
        		PFMConstants.OK_CODE, PFMConstants.SUCCESS_INCOME_DELETE),
        		HttpStatus.OK);
    }
    
    /**
     * getIncome this method used to search the income data based on user inputs and call the income service to
     * retrieve the data in db
     *
     * @throws Exception
     */
    public ResponseEntity<?> getIncome(
    		@ApiParam(value = "id to search for income",required=true) @PathVariable("incomeId") String incomeId)
    		throws Exception {
    	
    	final String accept = request.getHeader("Accept");
    	Income incomeObj = null;
    	
        try {
        	//TODO: add user model instead of hard coding
            final String userId = "1";
        	incomeObj = incomeServiceImpl.getIncome(incomeId, userId);
        	return new ResponseEntity<Income>(incomeObj, HttpStatus.OK);
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

    /**
     * getIncomeByKey this method used to search the income data based on user inputs and call the income service to
     * retrieve the data in db
     *
     * @throws Exception
     */
    public ResponseEntity<?> getIncomeByKey(
    		@NotNull @ApiParam(value = "Key to search for income", required = true) @Valid @RequestParam(value = "incomeKey", required = true) String incomeKey)
    		throws Exception {
    	
        final String accept = request.getHeader("Accept");
        List<Income> incomeList = null;
        
    	try {
            //TODO: add user model instead of hard coding
            final String userId = "1";
            if(incomeKey == null || incomeKey.isEmpty())
            	incomeList = incomeServiceImpl.getIncomeAll(userId);
            else
            	incomeList = incomeServiceImpl.searchIncome(incomeKey, userId);
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

        return new ResponseEntity<List<Income>>(incomeList, HttpStatus.OK);
    }

    /***
     * getIncomeList this method used to get the login user income data and non-deleted data and call the income
     * service to retrieve the income data
     */
    public ResponseEntity<?> getIncomeList() throws Exception {
    	
        final String accept = request.getHeader("Accept");
        List<Income> incomeList = null;
        
    	try {
    		//TODO: add user model instead of hard coding
            final String userId = "1";
            incomeList = incomeServiceImpl.getIncomeAll(userId);
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

        return new ResponseEntity<List<Income>>(incomeList, HttpStatus.OK);
    }

    /***
     * updateIncome is used to update the income data based on user action and call the income service to update the
     * income data
     */
    public ResponseEntity<ApiResponseMessage> updateIncome(
    		@ApiParam(value = "Income object that needs to be updated to the store" ,required=true )  @Valid @RequestBody Income body)
    		throws Exception {
    	
        final String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
            	//TODO: add user model instead of hard coding
                final String userId = "1";
            	incomeServiceImpl.updateIncome(body, userId);
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
        		PFMConstants.OK_CODE, PFMConstants.SUCCESS_INCOME_UPDATE),
        		HttpStatus.OK);
    }

}
