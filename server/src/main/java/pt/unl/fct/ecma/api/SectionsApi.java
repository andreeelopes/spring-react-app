package pt.unl.fct.ecma.api;

import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import pt.unl.fct.ecma.models.Section;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-03T10:56:43.867Z")

@Api(value = "sections", description = "the sections API")
public interface SectionsApi {

    @ApiOperation(value = "Delete the section with the provided ID", nickname = "deleteSection", notes = "", tags={ "sections", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Section not found") })
    @RequestMapping(value = "/sections/{id}",
            method = RequestMethod.DELETE)
    void deleteSection(@ApiParam(value = "Section ID",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "Get the list of all sections", nickname = "getAllSections", notes = "Returns all the sections", response = Section.class, responseContainer = "List", tags={ "sections", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Section.class, responseContainer = "List") })
    @RequestMapping(value = "/sections",
            produces = { "application/json" },
            method = RequestMethod.GET)
    Page<Section> getAllSections(@ApiParam(value = "Filter sections by type") @Valid @RequestParam(value = "search", required = false) String search);


    @ApiOperation(value = "Find section by ID", nickname = "getSection", notes = "Returns a single section", response = Section.class, tags={ "sections", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = Section.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Section not found") })
    @RequestMapping(value = "/sections/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    Page<Section> getSection(@ApiParam(value = "ID of section to return",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "Update the section with the provided ID", nickname = "updateSection", notes = "", tags={ "sections", })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Section not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/sections/{id}",
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    void updateSection(@ApiParam(value = "Section object that will be updated" ,required=true )  @Valid @RequestBody Section section,@ApiParam(value = "ID of Section to return",required=true) @PathVariable("id") Long id);

}

