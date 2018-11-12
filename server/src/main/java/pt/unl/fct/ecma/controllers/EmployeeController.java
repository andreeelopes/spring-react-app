package pt.unl.fct.ecma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.EmployeesApi;
import pt.unl.fct.ecma.brokers.EmployeeBroker;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.models.SimpleEmployee;
import pt.unl.fct.ecma.security.annotations.IsPrincipal;
import pt.unl.fct.ecma.models.Bid;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.models.Proposal;

import javax.validation.Valid;

@RestController

public class EmployeeController implements EmployeesApi {

    @Autowired
    private EmployeeBroker employeeBroker;


    @Override
    public Employee getEmployee(@PathVariable("employeeId") Long employeeId) {
        return employeeBroker.getEmployee(employeeId);
    }

    @Override
    @IsPrincipal
    public Page<Bid> getEmployeeBids(@PathVariable("employeeId") Long employeeId,
                                     @Valid @RequestParam(value = "search", required = false) String search,
                                     Pageable pageable) {
        if (search == null) {
            return employeeBroker.getAllBids(employeeId, pageable);
        } else {
            return employeeBroker.getAllBidsByStatus(employeeId, search, pageable);
        }
    }


    @Override
    public Page<Employee> getEmployees(@Valid @RequestParam(value = "search", required = false) String search,
                                       Pageable pageable) {
        if (search == null) {
            return employeeBroker.getAllEmployees(pageable);
        } else {
            return employeeBroker.getEmployeeByName(search, pageable);
        }
    }

    @Override
    @IsPrincipal
    public Page<Proposal> getProposalPartner(@PathVariable("employeeId") Long employeeId,
                                             @Valid @RequestParam(value = "search", required = false) String search,
                                             Pageable pageable) { //TODO search?
        return employeeBroker.getProposalPartner(pageable, employeeId);
    }

    @Override
    @IsPrincipal
    public Page<Proposal> getProposalStaff(@PathVariable("employeeId") Long employeeId,
                                           @Valid @RequestParam(value = "search", required = false) String search,
                                           Pageable pageable) { //TODO search?
        return employeeBroker.getProposalStaff(pageable, employeeId);
    }

    @Override
    @IsPrincipal
    public void updateEmployee(@Valid @RequestBody SimpleEmployee employee,
                               @PathVariable("employeeId") Long employeeId) {

        if (!employee.getId().equals(employeeId))
            throw new BadRequestException("Ids of employee do not match");

        employeeBroker.updateEmployee(employee);
    }
}
