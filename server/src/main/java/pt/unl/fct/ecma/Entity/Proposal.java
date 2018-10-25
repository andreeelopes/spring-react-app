package pt.unl.fct.ecma.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Proposal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    private Set<ProposalRole> team = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    private Set<Section> sections = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    @JsonIgnore
    private Set<Section> binded = new HashSet<>();

    private Status status;

    public enum Status{
        PLACED,APPROVED,DECLINED
    }
}
