package pt.unl.fct.ecma.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.unl.fct.ecma.models.Bid;

import javax.validation.Valid;

public interface BidsApi {

    @ApiOperation(value = "Add a new Bid to the proposal", nickname = "addBidToProposal", notes = "", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Added a new bid"),
            @ApiResponse(code = 405, message = "Invalid input")})
    @RequestMapping(value = "/proposals/{id}/bids",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    void addBidToProposal(@ApiParam(value = "Proposal ID", required = true) @PathVariable("id") Long id, @ApiParam(value = "Bid object to add on the proposal", required = true) @Valid @RequestBody Bid bid);


    @ApiOperation(value = "Delete Bid with the ID provided", nickname = "deleteBid", notes = "", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Proposal or bid not found")})
    @RequestMapping(value = "/proposals/{id}/bids/{bidid}",
            method = RequestMethod.DELETE)
    void deleteBid(@ApiParam(value = "Proposal ID", required = true) @PathVariable("id") Long id, @ApiParam(value = "Bid ID", required = true) @PathVariable("bidid") Long bidid);


    @ApiOperation(value = "Get all bids by the proposal ID", nickname = "getBids", notes = "Returns all bids", response = Bid.class, responseContainer = "List", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Bid.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Proposal not found")})
    @RequestMapping(value = "/proposals/{id}/bids",
            produces = {"application/json"},
            method = RequestMethod.GET)
    Page<Bid> getBids(Pageable pageable, @ApiParam(value = "Proposal ID", required = true) @PathVariable("id") Long id);


    @ApiOperation(value = "Update bid with ID provided", nickname = "updateBid", notes = "", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Proposal or bid not found"),
            @ApiResponse(code = 405, message = "Validation exception")})
    @RequestMapping(value = "/proposals/{id}/bids/{bidid}",
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    void updateBid(
            @ApiParam(value = "Bid object that needs to be updated in the collection", required = true)
            @Valid @RequestBody Bid review,
            @ApiParam(value = "Bid ID", required = true) @PathVariable("bidid") Long bidid,
            @ApiParam(value = "ID of proposal", required = true) @PathVariable("id") Long id);



}
