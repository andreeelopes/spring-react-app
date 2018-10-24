package pt.unl.fct.ecma.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Bid {
    public enum Status{DENIED,WAITING,ACCEPTED}
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Proposal proposal;
    private  Status status;

}
