package pt.unl.fct.ecma.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Review {
    public enum Score{BAD,POOR,OK,GREAT,EXCELENT}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Employee author;
    private String text;
    @ManyToOne
    private Proposal proposal;
    private Score score;
}
