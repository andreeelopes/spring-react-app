package pt.unl.fct.ecma.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String job;

    @ManyToOne
    @JsonIgnore
    private Company company;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "employee")
    private Set<ProposalRole> rolesOnProposal = new HashSet<>();
   // private Set<Proposal> partnerProposals = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "author")
    private Set<Comment> comments = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "author")
    private Set<Review> reviews = new HashSet<>();
}
