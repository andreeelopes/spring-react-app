package pt.unl.fct.ecma.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JsonIgnore
    private Employee author;

    private String comment;
    @ManyToOne
    @JsonIgnore
    private Proposal proposal;
}
