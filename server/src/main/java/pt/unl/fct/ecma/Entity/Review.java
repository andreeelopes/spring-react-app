package pt.unl.fct.ecma.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Review {
    public enum Score{BAD,POOR,OK,GREAT,EXCELENT}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Employee author;
    private String text;
    private Proposal proposal;
    private Score score;
}
