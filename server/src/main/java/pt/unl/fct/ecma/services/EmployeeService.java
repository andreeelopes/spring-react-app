package pt.unl.fct.ecma.services;

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
    private EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

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

    public Page<Employee> getEmployeeByName(String search, Pageable pageable) {
        return employeeRepository.findByName(search, pageable);
    }

    public void updateEmployee(SimpleEmployee emp) {
        Optional<Employee> old_emp = employeeRepository.findById(emp.getId());
        if (old_emp.isPresent()) {
            Employee realemp = old_emp.get();
            realemp.setAdmin(emp.isAdmin());
            realemp.setEmail(emp.getEmail());
            realemp.setName(emp.getName());
            realemp.setJob(emp.getJob());
            employeeRepository.save(realemp);
        } else throw new NotFoundException(String.format("Employee with id %d does not exist", emp.getId()));
    }

    public Page<Bid> getAllBids(Long employeeId, Pageable pageable) {
        Optional<Employee> emp = employeeRepository.findById(employeeId);
        if (emp.isPresent()) {
            return employeeRepository.findAllBids(pageable, employeeId);
            //  return new PageImpl<Bid>(bid,pageable,bid.size());

        } else throw new NotFoundException(String.format("Person with id %d does not exist", employeeId));
    }

    public Page<Bid> getAllBidsByStatus(Long employeeId, String search, Pageable pageable) {
        Optional<Employee> emp = employeeRepository.findById(employeeId);
        if (emp.isPresent()) {
            return employeeRepository.findBidsByStatus(search, employeeId, pageable);
        } else throw new NotFoundException(String.format("Employee with id %d does not exist", employeeId));
    }

    public Page<Proposal> getProposalPartner(Pageable pageable, Long employeeId) {
        Optional<Employee> emp = employeeRepository.findById(employeeId);
        if (emp.isPresent()) {
            return employeeRepository.findProposalPartner(pageable, employeeId);
        } else throw new NotFoundException(String.format("Employee with id %d does not exist", employeeId));
    }

    public Page<Proposal> getProposalStaff(Pageable pageable, Long employeeId) {
        Optional<Employee> emp = employeeRepository.findById(employeeId);
        if (emp.isPresent()) {
            return employeeRepository.findProposalStaff(pageable, employeeId);
        } else throw new NotFoundException(String.format("Employee with id %d does not exist", employeeId));
    }
}
