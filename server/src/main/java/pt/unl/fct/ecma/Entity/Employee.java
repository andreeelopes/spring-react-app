package pt.unl.fct.ecma.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Company company;
    private Set<Proposal> staffProposals = new HashSet<>();
    private Set<Proposal> partnerProposals = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();
    private Set<Review> reviews = new HashSet<>();
}
