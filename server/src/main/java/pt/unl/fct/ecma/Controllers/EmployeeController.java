package pt.unl.fct.ecma.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.Entity.Employee;
import pt.unl.fct.ecma.Errors.NotFoundException;
import pt.unl.fct.ecma.Services.EmployeeService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(EmployeeController.BASE_URL)
public class EmployeeController {

    public static final String BASE_URL="/employee";

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable long id){
        try {
            return employeeService.getEmployeeById(id);
        }catch (NoSuchElementException exception){
            throw new NotFoundException("Employee not found.");
        }
    }
}
