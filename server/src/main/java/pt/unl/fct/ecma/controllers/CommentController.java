package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.CommentsApi;
import pt.unl.fct.ecma.models.Comment;
import pt.unl.fct.ecma.security.CanAddComment;
import pt.unl.fct.ecma.services.CommentService;

import javax.validation.Valid;

@RestController
public class CommentController implements CommentsApi {


    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    // @CanAddComment
    //pertencer à team da proposal e confirmar o autor com o principal
    @CanAddComment
    @Override
    public void addComment(@PathVariable Long id, @Valid @RequestBody Comment comment) {
        commentService.addComment(id, comment);
    }

    //@IsAuthorOfComment
    //ser autor do comentario
    @Override
    public void deleteComment(@PathVariable("id") Long id, @PathVariable("commentid") Long commentid) {
        commentService.deleteComment(id, commentid);
    }

    //@BelongsToTeam
    //pertencer à team da proposal
    @Override
    public Page<Comment> getProposalComments(Pageable pageable,@PathVariable Long id) {
        return commentService.getProposalComments(pageable,id);
    }

    //@IsAuthorOfComment
    //ser autor do comentario
    @Override
    public void updateComment(@Valid @RequestBody Comment comment,@PathVariable("commentid") Long commentid,@PathVariable("id") Long id) {
        commentService.updateComment(comment,commentid,id);
    }
}
