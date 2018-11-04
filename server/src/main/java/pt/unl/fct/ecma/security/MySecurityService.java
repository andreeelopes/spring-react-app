package pt.unl.fct.ecma.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.repositories.EmployeeRepository;

import java.util.Optional;

@Service
public class MySecurityService {
    private EmployeeRepository people;


    public MySecurityService(EmployeeRepository people) {
        this.people = people;

    }

    public boolean isPrincipal(User user, Long id) {
        Optional<Employee> person = people.findById(id);

        return person.isPresent() && person.get().getUsername().equals(user.getUsername());
    }


}