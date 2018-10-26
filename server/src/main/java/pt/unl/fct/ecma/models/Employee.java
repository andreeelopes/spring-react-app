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
    @JsonIgnore
    private List<ProposalRole> rolesOnProposal = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "bidder")
    @JsonIgnore
    private List<Bid> biddedProposals = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "author")
    @JsonIgnore
    private List<Comment> comments = new LinkedList<>();
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "author")
    @JsonIgnore
    private List<Review> reviews = new LinkedList<>();
}
