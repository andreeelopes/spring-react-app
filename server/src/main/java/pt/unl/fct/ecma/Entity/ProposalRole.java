package pt.unl.fct.ecma.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class ProposalRole {

    @Id
    @ManyToOne
    private Proposal proposal;
    @Id
    @ManyToOne
    private Employee employee;
    private String role;
}
