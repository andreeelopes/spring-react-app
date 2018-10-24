package pt.unl.fct.ecma.Services;

import pt.unl.fct.ecma.repositories.EmployeeRepository;

public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
}
