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
import com.javaj2eefsd.workshop.model.Investments;
import com.javaj2eefsd.workshop.service.InvestmentsService;
import com.javaj2eefsd.workshop.util.PFMConstants;
import io.swagger.annotations.ApiParam;
//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/***
 *  This is InvestmentsApiController this class connect with investments module end point who where called
 *         in webservice give the response for create ,update.delete,retrieve and search the investment details.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-06T07:36:56.089+05:30")

@Controller
@CrossOrigin(origins = { "*" })
public class InvestmentsApiController implements InvestmentsApi {

    private static final Logger log = LoggerFactory.getLogger(InvestmentsApiController.class);
  //IDIOM:Item-0008: Use descriptive Identifiers
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    InvestmentsService investmentsServiceImpl;

    @org.springframework.beans.factory.annotation.Autowired
    public InvestmentsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    /***
     * addInvestments method help to create new investment getting data from front end and call the investment service to create
     * new investment data in db
     */
    public ResponseEntity<ApiResponseMessage> addInvestments(
    		@ApiParam(value = "Investments object that needs to be added to the store" ,required=true )  @Valid @RequestBody Investments body)
    		throws Exception {
    	
        final String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
            	investmentsServiceImpl.createInvestments(body);
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
        		PFMConstants.OK_CODE, PFMConstants.SUCCESS_INVESTMENTS_ADD),
        		HttpStatus.OK);
    }

    /**
     * deleteInvestments method help to delete Investments data based on user inputs and this only soft deleted the Investments data
     * and call the investments service to delete the data in db
     */
    public ResponseEntity<ApiResponseMessage> deleteInvestments(
    		@ApiParam(value = "Investments id to delete",required=true) @PathVariable("investmentsId") String investmentsId)
    		throws Exception {
        
    	final String accept = request.getHeader("Accept");
        
        try {
        	//TODO: add user model instead of hard coding
            final String userId = "1";
        	investmentsServiceImpl.deleteInvestments(investmentsId, userId);
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
    
    /**
     * getInvestments this method used to search the investments data based on user inputs and call the investments service to
     * retrieve the data in db
     *
     * @throws Exception
     */
    public ResponseEntity<?> getInvestments(
    		@ApiParam(value = "id to search for investments",required=true) @PathVariable("investmentsId") String investmentsId)
    		throws Exception {
    	
        final String accept = request.getHeader("Accept");
        Investments investmentsObj = null;
        
        try {
        	//TODO: add user model instead of hard coding
        	final String userId = "1";
        	investmentsObj = investmentsServiceImpl.getInvestments(investmentsId, userId);
        	return new ResponseEntity<Investments>(investmentsObj, HttpStatus.OK);
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
     * getInvestmentsByKey this method used to search the investments data based on user inputs and call the expense service to
     * retrieve the data in db
     *
     * @throws Exception
     */
    public ResponseEntity<?> getInvestmentsByKey(
    		@NotNull @ApiParam(value = "ID of investments to return", required = true) @Valid @RequestParam(value = "investmentsKey", required = true) String investmentsKey)
    		throws Exception {
    	
        final String accept = request.getHeader("Accept");
        List<Investments> investmentsList = null;

    	try {
    		//TODO: add user model instead of hard coding
            final String userId = "1";
            if(investmentsKey == null || investmentsKey.isEmpty())
            	investmentsList = investmentsServiceImpl.getInvestmentsAll(userId);
            else
            	investmentsList = investmentsServiceImpl.searchInvestments(investmentsKey, userId);
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

        return new ResponseEntity<List<Investments>>(investmentsList, HttpStatus.OK);
    }

    /***
     * getInvestmentsList this method used to get the login user investments data and non-deleted data and call the investments
     * service to retrieve the investments data
     */
    public ResponseEntity<?> getInvestmentsList() throws Exception {
        
    	final String accept = request.getHeader("Accept");
        List<Investments> investmentsList = null;
        
    	try {
    		//TODO: add user model instead of hard coding
            final String userId = "1";
            investmentsList = investmentsServiceImpl.getInvestmentsAll(userId);
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

        return new ResponseEntity<List<Investments>>(investmentsList, HttpStatus.OK);
    }

    /***
     * updateInvestments is used to update the investment data based on user action and call the investment service to update the
     * investment data
     */
    public ResponseEntity<ApiResponseMessage> updateInvestments(
    		@ApiParam(value = "Investments object that needs to be updated to the store" ,required=true )  @Valid @RequestBody Investments body)
    		throws Exception {
    	
        final String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
            	//TODO: add user model instead of hard coding
                final String userId = "1";
            	investmentsServiceImpl.updateInvestments(body, userId);
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
        		PFMConstants.OK_CODE, PFMConstants.SUCCESS_INVESTMENTS_UPDATE),
        		HttpStatus.OK);
    }

}
