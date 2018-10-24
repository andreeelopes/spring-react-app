package pt.unl.fct.ecma.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class ProposalRole {

    @Id
    @ManyToOne
    @JsonIgnore
    private Proposal proposal;
    @Id
    @ManyToOne
    @JsonIgnore
    private Employee employee;
    private String role;
}
