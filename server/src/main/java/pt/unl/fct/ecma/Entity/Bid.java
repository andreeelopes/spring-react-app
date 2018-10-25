package pt.unl.fct.ecma.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Entity
public class Bid implements Serializable {
    public enum Status{DENIED,WAITING,ACCEPTED}
    @ManyToOne
    @Id
    private Employee employee;
    @ManyToOne
    @Id
    private Proposal proposal;
    private  String status;

}
