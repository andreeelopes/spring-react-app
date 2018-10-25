package pt.unl.fct.ecma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pt.unl.fct.ecma.Entity.Employee;
import pt.unl.fct.ecma.repositories.EmployeeRepository;

import javax.transaction.Transactional;

@SpringBootApplication
public class EcmaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EcmaApplication.class, args);
    }

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Employee emp=new Employee();
        emp.setEmail("simon@gmail.com");
        emp.setJob("Informatico");
        emp.setName("Simon");
        employeeRepository.save(emp);
    }
}
