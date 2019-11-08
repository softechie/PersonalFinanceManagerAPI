package com.javaj2eefsd.workshop.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaj2eefsd.workshop.model.Investments;
import com.javaj2eefsd.workshop.model.Savings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-06T07:36:56.089+05:30")

@Api(value = "savings", description = "the savings API")
public interface SavingsApi {
	 @ApiOperation(value = "Create new savings", nickname = "addSavingsAmount", notes = "Creates new savings", authorizations = {
		        @Authorization(value = "bearerAuth")
		    }, tags={ "savings", })
		    @ApiResponses(value = { 
		        @ApiResponse(code = 405, message = "Invalid input") })
		    @RequestMapping(value = "/savings/new",
		        produces = { "application/json" }, 
		        consumes = { "application/json" },
		        method = RequestMethod.PUT)
		    ResponseEntity<ApiResponseMessage> addSavingsAmount(
		    		@ApiParam(value = "Savings object that needs to be added to the store" ,required=true )  @Valid @RequestBody Savings body)
		    		throws Exception;
	 
@ApiOperation(value = "savings", nickname = "the savings API", notes = "Updates savings", authorizations = {
        @Authorization(value = "bearerAuth")
    }, tags={ "savings", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Savings not found"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/savings/edit",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<ApiResponseMessage> updateSavings(
    		@ApiParam(value = "savings object that needs to be updated to the store" ,required=true )  @Valid @RequestBody Savings body)
    		throws Exception;

@ApiOperation(value = "Deletes the Savings", nickname = "deleteSavings", notes = "Deletes the Savings", authorizations = {
        @Authorization(value = "bearerAuth")
    }, tags={ "savings", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Savings not found") })
    @RequestMapping(value = "/savings/delete/{savingsId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<ApiResponseMessage> deleteSavings(
    		@ApiParam(value = "Savings id to delete",required=true) @PathVariable("savingsId") String savingsId)
    		throws Exception;

@ApiOperation(value = "get savings by id", nickname = "getSavings", notes = "Returns the particular Savings", response = Savings.class, authorizations = {
        @Authorization(value = "bearerAuth")
    }, tags={ "savings", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Savings.class) })
    @RequestMapping(value = "/savings/{savingsId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<?> getSavings(
    		@ApiParam(value = "id to search for savings",required=true) @PathVariable("savingsId") String savingsId)
    		throws Exception;

@ApiOperation(value = "savings list", nickname = "getSavingsList", notes = "Returns the list of savings", response = Savings.class, responseContainer = "List", authorizations = {
        @Authorization(value = "bearerAuth")
    }, tags={ "savings", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Savings.class, responseContainer = "List") })
    @RequestMapping(value = "/savings/all",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<?> getSavingsList() throws Exception;
}