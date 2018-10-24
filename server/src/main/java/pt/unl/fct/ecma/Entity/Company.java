package pt.unl.fct.ecma.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private String email;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "company")
    private Set<Employee> members = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "company")
    private Set<Employee> staff = new HashSet<>();
}
