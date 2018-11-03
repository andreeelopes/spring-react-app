package pt.unl.fct.ecma.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
public class Proposal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    @JsonIgnore
    private List<ProposalRole> team = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    @JsonIgnore
    private List<Comment> comments = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    @JsonIgnore
    private List<Review> reviews = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    @JsonIgnore
    private List<Section> sections = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    @JsonIgnore
    private List<Bid> bids = new LinkedList<>();

    @ManyToOne
    private Company companyProposed;

    private Status status;

    public enum Status{
        PLACED,APPROVED,DECLINED
    }
}
