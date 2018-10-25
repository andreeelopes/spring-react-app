package pt.unl.fct.ecma.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String email;


    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "company")
    private List<Employee> members = new LinkedList<>();

}
