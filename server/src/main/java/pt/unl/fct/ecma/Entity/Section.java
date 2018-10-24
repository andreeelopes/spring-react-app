package pt.unl.fct.ecma.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Section {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Proposal proposal;
    private String text;
    private String type;
}
