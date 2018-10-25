package pt.unl.fct.ecma.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
    private boolean isAdmin = false;

    @ManyToOne
    @JsonIgnore
    private Company company;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "employee")
    private List<ProposalRole> rolesOnProposal = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "employee")
    @JsonIgnore
    private List<Bid> biddingProposals = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "author")
    private List<Comment> comments = new LinkedList<>();
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "author")
    private List<Review> reviews = new LinkedList<>();
}
