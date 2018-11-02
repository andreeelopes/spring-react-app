package pt.unl.fct.ecma.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;


import java.util.Optional;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.repositories.EmployeeRepository;

@Service
public class mySecurityService {
    private EmployeeRepository people;


    public mySecurityService(EmployeeRepository people) {
        this.people = people;

    }

    public boolean isPrincipal(User user, Long id) {
        Optional<Employee> person = people.findById(id);

        return person.isPresent() && person.get().getUsername().equals(user.getUsername());
    }



}
