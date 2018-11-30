/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package pt.unl.fct.ecma.api;


import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pt.unl.fct.ecma.models.Bid;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.models.Proposal;
import pt.unl.fct.ecma.models.SimpleEmployee;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-25T09:46:01.754Z")

@Api(value = "employees", description = "the employees API")
public interface EmployeesApi {

    @ApiOperation(value = "Find employee by ID", nickname = "getEmployee", notes = "Returns a single employee", response = Employee.class, tags={ "employees", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Employee.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Employee not found") })
    @RequestMapping(value = "/employees/{employeeId}",
            produces = { "application/json" },
            method = RequestMethod.GET)

    Employee getEmployee(@ApiParam(value = "ID of employee to return",required=true) @PathVariable("employeeId") Long employeeId);


    @ApiOperation(value = "Get the list of all bids where this employee bid", nickname = "getEmployeeBids", notes = "", response = Bid.class, responseContainer = "List", tags={ "employees", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Bid.class, responseContainer = "List") })
    @RequestMapping(value = "/employees/{employeeId}/bids",
            produces = { "application/json" },
            method = RequestMethod.GET)
    Page<Bid> getEmployeeBids(@ApiParam(value = "Employee ID",required=true) @PathVariable("employeeId") Long employeeId,
                              @ApiParam(value = "Filter bids by the status") @Valid @RequestParam(value = "search", required = false) String search,
                              Pageable pageable);


    @ApiOperation(value = "Get the list of all employees", nickname = "getEmployees", notes = "", response = Employee.class, responseContainer = "List", tags={ "employees", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Employee.class, responseContainer = "List") })
    @RequestMapping(value = "/employees",
            produces = { "application/json" },
            method = RequestMethod.GET)
    Page<Employee> getEmployees(@ApiParam(value = "Filter employees by name")
                                @Valid @RequestParam(value = "search", required = false) String search, Pageable pageable,@RequestParam(value = "exist", required = false) String exist);


    @ApiOperation(value = "Get the list of all proposals where this employee is partner", nickname = "getProposalPartner", notes = "", response = Proposal.class, responseContainer = "List", tags={ "employees", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Proposal.class, responseContainer = "List") })
    @RequestMapping(value = "/employees/{employeeId}/partnerproposals",
            produces = { "application/json" },
            method = RequestMethod.GET)
    Page<Proposal> getProposalPartner(@ApiParam(value = "Employee ID",required=true) @PathVariable("employeeId") Long employeeId,
                                      @ApiParam(value = "Filter proposal by the status")
                                      @Valid @RequestParam(value = "search", required = false) String search,Pageable pageable);


    @ApiOperation(value = "Get the list of all proposals where this employee is staff", nickname = "getProposalStaff", notes = "", response = Proposal.class, responseContainer = "List", tags={ "employees", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Proposal.class, responseContainer = "List") })
    @RequestMapping(value = "/employees/{employeeId}/staffproposals",
            produces = { "application/json" },
            method = RequestMethod.GET)
    Page<Proposal> getProposalStaff(@ApiParam(value = "Employee ID",required=true) @PathVariable("employeeId") Long employeeId,
                                    @ApiParam(value = "Filter proposal by the status")
                                    @Valid @RequestParam(value = "search", required = false) String search,Pageable pageable);


    @ApiOperation(value = "Update employee with ID provided", nickname = "updateEmployee", notes = "", tags={ "employees", })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Employee not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/employees/{employeeId}",
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    void updateEmployee(@ApiParam(value = "Employee object that needs to be updated in the collection" ,required=true )
                        @Valid @RequestBody SimpleEmployee employee, @ApiParam(value = "ID of emoloyee to return",required=true)
    @PathVariable("employeeId") Long employeeId);


}