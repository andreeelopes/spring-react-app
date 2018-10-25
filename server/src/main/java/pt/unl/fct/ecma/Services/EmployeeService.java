package pt.unl.fct.ecma.Services;

import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.Entity.Employee;
import pt.unl.fct.ecma.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).get();
    }
}
