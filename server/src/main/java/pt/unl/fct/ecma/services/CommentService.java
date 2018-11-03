package pt.unl.fct.ecma.services;

import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.repositories.CommentRepository;
@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }
}
