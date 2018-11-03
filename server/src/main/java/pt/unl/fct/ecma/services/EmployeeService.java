package pt.unl.fct.ecma.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.models.Bid;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.models.Proposal;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.errors.NotFoundException;
import pt.unl.fct.ecma.repositories.EmployeeRepository;

import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).get();
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Page<Employee> getEmployeeByName(String search,Pageable pageable) {
        return employeeRepository.findByName(search,pageable);
    }

    public void updateEmployee(Long id, Employee emp) {
        if(emp.getId()==id){
            Optional<Employee> old_emp= employeeRepository.findById(id);
            if(old_emp.isPresent()){
                Employee realemp =old_emp.get();
                realemp.setPassword(emp.getPassword());
                realemp.setAdmin(emp.isAdmin());
                realemp.setEmail(emp.getEmail());
                realemp.setName(emp.getName());
                realemp.setJob(emp.getJob());
                employeeRepository.save(realemp);

            }
            else throw new NotFoundException(String.format("Employee with id %d does not exist", id));
        }
        else throw new BadRequestException("Invalid request");
    }

    public Page<Bid> getAllBid(Long id,Pageable pageable) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if(emp.isPresent()){
            return employeeRepository.findAllBids(pageable,id);
          //  return new PageImpl<Bid>(bid,pageable,bid.size());

        }
        else throw new NotFoundException(String.format("Person with id %d does not exist", id));
    }

    public Page<Bid> getAllBidByStatus(Long id, String search,Pageable pageable) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if(emp.isPresent()) {
            return employeeRepository.findBidsByStatus(search, id,pageable);
        }
        else throw new NotFoundException(String.format("Employee with id %d does not exist", id));
    }

    public Page<Proposal> getProposalPartner(Pageable pageable,Long id){
        Optional<Employee> emp = employeeRepository.findById(id);
        if(emp.isPresent()) {
            return employeeRepository.findProposalPartner(pageable, id);
        }
        else throw new NotFoundException(String.format("Employee with id %d does not exist", id));
    }

    public Page<Proposal> getProposalStaff(Pageable pageable, Long id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if(emp.isPresent()) {
            return employeeRepository.findProposalStaff(pageable, id);
        }
        else throw new NotFoundException(String.format("Employee with id %d does not exist", id));
    }
}
