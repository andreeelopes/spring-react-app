package pt.unl.fct.ecma.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Employee author;
    private String text;
    @ManyToOne
    @JsonIgnore
    private Proposal proposal;
    private Score score;
}
