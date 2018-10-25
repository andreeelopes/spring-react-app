package pt.unl.fct.ecma.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.unl.fct.ecma.Entity.Bid;
import pt.unl.fct.ecma.Entity.Employee;
import pt.unl.fct.ecma.Entity.Proposal;
import pt.unl.fct.ecma.Errors.NotFoundException;
import pt.unl.fct.ecma.Services.EmployeeService;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController

public class EmployeeController implements EmployeesApi{

    public static final String BASE_URL="/employees";

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }



    @Override
    public Employee getEmployee(@PathVariable Long id) {
        try {
            return employeeService.getEmployeeById(id);
        }catch (NoSuchElementException exception){
            throw new NotFoundException("Employee not found.");
        }
    }

    @Override
    public Page<Bid> getEmployeeBids(@PathVariable Long id, @Valid @RequestParam(value = "search", required = false) String search,Pageable pageable) {
        if(search == null) {
            return employeeService.getAllBid(id,pageable);
        }
        else{
            return employeeService.getAllBidByStatus(id,search,pageable);
        }
    }

    @Override
    public Page<Employee> getEmployees(@Valid @RequestParam(value = "search", required = false) String search, Pageable pageable) {
        if(search == null) {
            return employeeService.getAllEmployees(pageable);
        }
        else{
            return employeeService.getEmployeeByName(search,pageable);
        }
    }

    @Override
    public Page<Proposal> getProposalPartner(@PathVariable Long id, @Valid @RequestParam(value = "search", required = false) String search, Pageable pageable) {
        return  employeeService.getProposalPartner(pageable,id);
    }

    @Override
    public Page<Proposal> getProposalStaff(@PathVariable Long id, @Valid @RequestParam(value = "search", required = false) String search, Pageable pageable) {
        return  employeeService.getProposalStaff(pageable,id);
    }

    @Override
    public void updateEmployee(@Valid @RequestBody Employee employee, @PathVariable Long id) {
        employeeService.updateEmployee(id,employee);
    }
}
