package pt.unl.fct.ecma.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
@Getter
@Setter
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
    @JsonIgnore
    private List<Employee> employees = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "companyProposed")
    @JsonIgnore
    private List<Proposal> proposals;

}
