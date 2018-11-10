package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.EmployeesApi;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.security.annotations.IsPrincipal;
import pt.unl.fct.ecma.models.Bid;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.models.Proposal;
import pt.unl.fct.ecma.services.EmployeeService;

@RestController

public class EmployeeController implements EmployeesApi {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public Employee getEmployee(Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @Override
    @IsPrincipal
    public Page<Bid> getEmployeeBids(Long employeeId, String search, Pageable pageable) {
        if (search == null) {
            return employeeService.getAllBids(employeeId, pageable);
        } else {
            return employeeService.getAllBidsByStatus(employeeId, search, pageable);
        }
    }


    @Override
    public Page<Employee> getEmployees(String search, Pageable pageable) {
        if (search == null) {
            return employeeService.getAllEmployees(pageable);
        } else {
            return employeeService.getEmployeeByName(search, pageable);
        }
    }

    @Override
    @IsPrincipal
    public Page<Proposal> getProposalPartner(Long employeeId, String search, Pageable pageable) { //TODO search?
        return employeeService.getProposalPartner(pageable, employeeId);
    }

    @Override
    @IsPrincipal
    public Page<Proposal> getProposalStaff(Long employeeId, String search, Pageable pageable) { //TODO search?
        return employeeService.getProposalStaff(pageable, employeeId);
    }

    @Override
    @IsPrincipal
    public void updateEmployee(Employee employee, Long employeeId) {

        if(!employee.getId().equals(employeeId))
            throw new BadRequestException("Ids of employee do not match");

        employeeService.updateEmployee(employee);
    }
}
