package pt.unl.fct.ecma.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
public class ProposalRole  implements Serializable {

    @Id
    @ManyToOne
    @JsonIgnore
    private Proposal proposal;

    @Id
    @ManyToOne
    @JsonIgnore
    private Employee employee;

    private String role;

    public enum Role {
        STAFF, PARTNER
    }
}
