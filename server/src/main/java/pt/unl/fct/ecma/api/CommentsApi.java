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
import pt.unl.fct.ecma.models.Comment;

import javax.validation.Valid;

public interface CommentsApi {


    @ApiOperation(value = "Add a new comment to the proposal", nickname = "addComment", notes = "", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Added a new comment"),
            @ApiResponse(code = 405, message = "Invalid input")})
    @RequestMapping(value = "/proposals/{id}/comments/",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    void addComment(@ApiParam(value = "Proposal ID", required = true) @PathVariable("id") Long id,
                    @ApiParam(value = "Comment object to add to the proposal", required = true)
                    @Valid @RequestBody Comment comment);


    @ApiOperation(value = "Delete comment with the ID provided", nickname = "deleteComment", notes = "", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Proposal not found")})
    @RequestMapping(value = "/proposals/{id}/comments/{commentid}",
            method = RequestMethod.DELETE)
    void deleteComment(@ApiParam(value = "Proposal ID", required = true) @PathVariable("id") Long id,
                       @ApiParam(value = "comment ID", required = true) @PathVariable("commentid") Long commentid);


    @ApiOperation(value = "Get all comments by the proposal ID", nickname = "getProposalComments",
            notes = "Returns all comments", response = Comment.class, responseContainer = "List", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Comment.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Proposal not found")})
    @RequestMapping(value = "/proposals/{id}/comments/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    Page<Comment> getProposalComments(Pageable pageable,
                                      @ApiParam(value = "Proposal ID", required = true) @PathVariable("id") Long id);


    @ApiOperation(value = "Update coments with ID provided", nickname = "updateComment", notes = "", tags = {"proposals",})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Proposal or comment not found"),
            @ApiResponse(code = 405, message = "Validation exception")})
    @RequestMapping(value = "/proposals/{id}/comments/{commentid}",
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    void updateComment(@ApiParam(value = "Comment object that needs to be updated in the collection", required = true)
                       @Valid @RequestBody Comment section, @ApiParam(value = "Comment ID", required = true)
                       @PathVariable("commentid") Long commentid, @ApiParam(value = "ID of proposal to return", required = true)
                       @PathVariable("id") Long id);


}
