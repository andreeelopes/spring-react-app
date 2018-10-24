package pt.unl.fct.ecma.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.unl.fct.ecma.Entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {

}
