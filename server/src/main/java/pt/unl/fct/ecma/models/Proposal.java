package pt.unl.fct.ecma.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class Proposal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    @JsonIgnore
    private List<ProposalRole> team = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    private List<Comment> comments = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    private List<Review> reviews = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")

    private List<Section> sections = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "proposal")
    @JsonIgnore
    private List<Bid> bids = new LinkedList<>();

    private Status status;

    public enum Status{
        PLACED,APPROVED,DECLINED
    }
}
