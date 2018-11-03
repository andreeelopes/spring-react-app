/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package pt.unl.fct.ecma.api;


import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pt.unl.fct.ecma.models.*;


import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-25T09:46:01.754Z")

@Api(value = "proposals", description = "the proposals API")
public interface ProposalsApi {


    @ApiOperation(value = "Add a new partner member to the proposal", nickname = "addPartner", notes = "", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Added a new Partner"),
            @ApiResponse(code = 405, message = "Invalid input")})
    @RequestMapping(value = "/proposals/{id}/partnermembers/",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    void addPartner(@ApiParam(value = "Proposal ID", required = true) @PathVariable("id") Long id, @ApiParam(value = "Partner member object to add on the proposal", required = true) @Valid @RequestBody Employee member);


    @ApiOperation(value = "Add a new proposal to the system", nickname = "addProposal", notes = "", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Added a new proposal"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 409, message = "Proposal already exists")})
    @RequestMapping(value = "/proposals",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    void addProposal(@ApiParam(value = "Proposal object to add to the system", required = true) @Valid @RequestBody Proposal proposal);


    @ApiOperation(value = "Add a new staff member to the proposal", nickname = "addStaffMember", notes = "", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Added a new staff member"),
            @ApiResponse(code = 405, message = "Invalid input")})
    @RequestMapping(value = "/proposals/{id}/staff/",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    void addStaffMember(@ApiParam(value = "Proposal ID", required = true) @PathVariable("id") Long id, @ApiParam(value = "Staff member object to add on the proposal", required = true) @Valid @RequestBody Employee staffMember);


    @ApiOperation(value = "Delete partner with the ID provided", nickname = "deletePartner", notes = "", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Company not found")})
    @RequestMapping(value = "/proposals/{id}/partnermembers/{partnerid}",
            method = RequestMethod.DELETE)
    void deletePartner(@ApiParam(value = "Proposal ID", required = true) @PathVariable("id") Long id, @ApiParam(value = "Partner ID", required = true) @PathVariable("partnerid") Long partnerid);


    @ApiOperation(value = "Delete proposal with the ID provided", nickname = "deleteProposal", notes = "Deletes a single proposal", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Company not found")})
    @RequestMapping(value = "/proposals/{id}",
            method = RequestMethod.DELETE)
    void deleteProposal(@ApiParam(value = "ID of proposal to return", required = true) @PathVariable("id") Long id);


    @ApiOperation(value = "Delete Staff with the ID provided", nickname = "deleteStaff", notes = "", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Proposal or staff not found")})
    @RequestMapping(value = "/proposals/{id}/staff/{staffid}",
            method = RequestMethod.DELETE)
    void deleteStaff(@ApiParam(value = "Proposal ID", required = true) @PathVariable("id") Long id, @ApiParam(value = "Staff ID", required = true) @PathVariable("staffid") Long staffid);


    @ApiOperation(value = "Find all proposals by title", nickname = "findProposal", notes = "Retrieves all proposals with our without a filter on title", response = Proposal.class, responseContainer = "List", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Proposal.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid title value")})
    @RequestMapping(value = "/proposals",
            produces = {"application/json"},
            method = RequestMethod.GET)
    Page<Proposal> findProposal(Pageable pageable, @ApiParam(value = "Title value to be considered for filter") @Valid @RequestParam(value = "title", required = false) String title);


    @ApiOperation(value = "Find proposal by ID", nickname = "getProposal", notes = "Returns a single proposal", response = Proposal.class, tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Proposal.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Proposal not found")})
    @RequestMapping(value = "/proposals/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    Proposal getProposal(@ApiParam(value = "ID of proposal to return", required = true) @PathVariable("id") Long id);


    @ApiOperation(value = "Get all partner members by the proposal ID", nickname = "getProposalMembers", notes = "Returns all partner members", response = Employee.class, responseContainer = "List", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Employee.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Proposal not found")})
    @RequestMapping(value = "/proposals/{id}/partnermembers/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    Page<Employee> getProposalMembers(Pageable pageable, @ApiParam(value = "Proposal ID", required = true) @PathVariable("id") Long id);


    @ApiOperation(value = "Get all staff members by the proposal ID", nickname = "getStaffMembers", notes = "Returns all staff members", response = Employee.class, responseContainer = "List", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Employee.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Proposal not found")})
    @RequestMapping(value = "/proposals/{id}/staff/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    Page<Employee> getStaffMembers(Pageable pageable, @ApiParam(value = "Proposal ID", required = true) @PathVariable("id") Long id);

}
