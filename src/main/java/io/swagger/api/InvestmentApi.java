/**
 * NOTE: This class is auto generated by the swagger code generator program (1.0.14).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Investment;
import io.swagger.model.Project;

@Api(value = "investment", description = "the investment API")
@RequestMapping("/investment")
public interface InvestmentApi {

    @ApiOperation(value = "registers an investment", nickname = "registerInvestment", notes = "Registers a new investment", tags={ "admins","investors","projectOwners", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful registration"),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 409, message = "an existing investment already exists") })
    @RequestMapping(produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Investment> registerInvestment(@ApiParam(value = "Invest tot register"  )  @Valid @RequestBody Investment investment);


    @ApiOperation(value = "searches all investments", nickname = "searchAllInvestments", notes = "Searches all investments", response = Investment.class, responseContainer = "List", tags={ "admins","investors","projectOwners", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns all investments", response = Investment.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "not investment registered") })
    @RequestMapping(value = "/investment/",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    List<Investment> searchAllInvestments();

    @ApiOperation(value = "searches investment", nickname = "searchInvestment", notes = "given an ID it searches an investment", response = Object.class, tags={ "admins","investors","projectOwners", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria!", response = Object.class),
        @ApiResponse(code = 400, message = "investment not found!") })
    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Investment> searchInvestment(@ApiParam(value = "id investment to find",required=true) @PathVariable("id") Integer id);

    @ApiOperation(value = "searches a list of projects given an investment", nickname = "searchAllInvestments", notes = "Searches all investments", response = Investment.class, responseContainer = "List", tags={ "admins","investors","projectOwners", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns all projects", response = Investment.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "not investment registered") })
    @RequestMapping(value = "/{id}/projects",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    List<Project> listProjects(@ApiParam(value = "id investment to find",required=true) @PathVariable("idTorneo") Integer id);

}