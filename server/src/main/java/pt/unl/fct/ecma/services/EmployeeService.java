package pt.unl.fct.ecma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.models.Bid;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.models.Proposal;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.errors.NotFoundException;
import pt.unl.fct.ecma.models.SimpleEmployee;
import pt.unl.fct.ecma.repositories.EmployeeRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee getEmployee(Long employeeId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isPresent())
            return employeeOpt.get();
        else
            throw new NotFoundException("Employee not found");
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Page<Employee> getEmployeeByName(String name, Pageable pageable) {
        return employeeRepository.findByName(name, pageable);
    }

    public void updateEmployee(SimpleEmployee employee, Employee oldEmployee) {
        oldEmployee.setAdmin(employee.isAdmin());
        oldEmployee.setEmail(employee.getEmail());
        oldEmployee.setName(employee.getName());
        oldEmployee.setJob(employee.getJob());
        employeeRepository.save(oldEmployee);
    }

    public Page<Bid> getAllBids(Employee bidder, Pageable pageable) {
        return employeeRepository.findAllBids(pageable, bidder.getId());
    }

    public Page<Bid> getAllBidsByStatus(Employee bidder, String status, Pageable pageable) {
        return employeeRepository.findBidsByStatus(status, bidder.getId(), pageable);
    }

    public Page<Proposal> getProposalPartner(Pageable pageable, Employee partnerMember) {
        return employeeRepository.findProposalPartner(pageable, partnerMember.getId());
    }

    public Page<Proposal> getProposalStaff(Pageable pageable, Employee staffMember) {
        return employeeRepository.findProposalStaff(pageable, staffMember.getId());
    }
}
