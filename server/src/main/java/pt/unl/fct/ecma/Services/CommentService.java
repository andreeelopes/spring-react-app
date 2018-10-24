package pt.unl.fct.ecma.Services;

import pt.unl.fct.ecma.repositories.CommentRepository;

public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }
}
