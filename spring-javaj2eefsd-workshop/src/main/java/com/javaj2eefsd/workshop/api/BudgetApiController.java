package com.javaj2eefsd.workshop.api;


import com.javaj2eefsd.workshop.model.DateRange;
import com.javaj2eefsd.workshop.service.IBudgetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.json.simple.JSONArray;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/***
 *  This is BudgetApiController this class connect with Budget module end point who where called
 *         in webservice give the response for search and retrieve the budget details.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-06T07:36:56.089+05:30")

@Controller
@CrossOrigin(origins = { "*" })
public class BudgetApiController implements BudgetApi {

    private static final Logger log = LoggerFactory.getLogger(BudgetApiController.class);
  
    //IDIOM:Item-0008: Use descriptive Identifiers
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    
    @Autowired
    IBudgetService budgetServiceImpl;

    @org.springframework.beans.factory.annotation.Autowired
    public BudgetApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    
    /***
     * getByDate method help to retrieve the budget details by date
     */
    
    @Override
    public ResponseEntity<JSONArray> getByDate(@ApiParam(value = "Returns a income, expense, investments over the dateRange." ,required=true )  @Valid @RequestBody final DateRange body) throws Exception {
    	final String accept = request.getHeader("Accept");
    	//List<Budget> budgetList = null;
    	JSONArray budgetList = null;
        if (accept != null && accept.contains("application/json")) {
            try {
            	budgetList = budgetServiceImpl.getBudgetByDate(body);
               
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        
        return new ResponseEntity<JSONArray>(budgetList, HttpStatus.OK);
    }

}

