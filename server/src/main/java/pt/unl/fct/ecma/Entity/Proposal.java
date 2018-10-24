package pt.unl.fct.ecma.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Proposal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Employee approver;
    private Set<Employee> members = new HashSet<>();
    private Set<Employee> staff = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();
    private Set<Review> reviews = new HashSet<>();
    private Set<Section> Sections = new HashSet<>();
    private Status status;

    public enum Status{
        PLACED,APPROVED,DECLINED
    }
}
