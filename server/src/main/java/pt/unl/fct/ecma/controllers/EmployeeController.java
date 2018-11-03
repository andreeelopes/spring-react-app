package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.EmployeesApi;
import pt.unl.fct.ecma.security.isPrincipal;
import pt.unl.fct.ecma.models.Bid;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.models.Proposal;
import pt.unl.fct.ecma.errors.NotFoundException;
import pt.unl.fct.ecma.services.EmployeeService;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController

public class EmployeeController implements EmployeesApi {

    public static final String BASE_URL="/employees";

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }



    @Override
    @isPrincipal
    public Employee getEmployee(@PathVariable Long id) {
            return employeeService.getEmployeeById(id);
    }

    @Override
    @isPrincipal
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
    @isPrincipal
    public Page<Proposal> getProposalPartner(@PathVariable Long id, @Valid @RequestParam(value = "search", required = false) String search, Pageable pageable) {
        return  employeeService.getProposalPartner(pageable,id);
    }

    @Override
    @isPrincipal
    public Page<Proposal> getProposalStaff(@PathVariable Long id, @Valid @RequestParam(value = "search", required = false) String search, Pageable pageable) {
        return  employeeService.getProposalStaff(pageable,id);
    }

    @Override
    @isPrincipal
    public void updateEmployee(@Valid @RequestBody Employee employee, @PathVariable Long id) {
        employeeService.updateEmployee(id,employee);
    }
}
