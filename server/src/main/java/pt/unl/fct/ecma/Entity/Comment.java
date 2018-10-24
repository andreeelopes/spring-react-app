package pt.unl.fct.ecma.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Employee author;

    private String comment;
    @ManyToOne
    private Proposal proposal;
}
