package pt.unl.fct.ecma.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pt.unl.fct.ecma.Entity.Bid;
import pt.unl.fct.ecma.Entity.Employee;
import pt.unl.fct.ecma.Errors.NotFoundException;
import pt.unl.fct.ecma.Services.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(EmployeeController.BASE_URL)
public class EmployeeController {

    public static final String BASE_URL="/employees";

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

    @GetMapping
    public Page<Employee> getAllEmployee(@PageableDefault(size = 10) Pageable pageable, @RequestParam(required = false) String search){

        if(search == null) {
            return employeeService.getAllEmployees(pageable);
        }
        else{
            return employeeService.getEmployeeByName(search,pageable);
        }

    }
    @PutMapping
    public void updateEmployee(@PathVariable Long id,@RequestBody Employee emp){

        employeeService.updateEmployee(id,emp);

    }
    @GetMapping("{id}/bids")
    public Page<Bid> getAllBid(@PathVariable Long id,@RequestParam(required = false) String search,Pageable pageable){
        if(search == null) {
           return employeeService.getAllBid(id,pageable);
        }
        else{
            return employeeService.getAllBidByStatus(id,search,pageable);
        }
    }
}
