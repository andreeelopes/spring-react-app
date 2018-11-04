package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.CommentsApi;
import pt.unl.fct.ecma.models.Comment;
import pt.unl.fct.ecma.security.CanAddComment;
import pt.unl.fct.ecma.security.IsAuthorOfComment;
import pt.unl.fct.ecma.security.BelongsToProposalTeam;
import pt.unl.fct.ecma.services.CommentService;

import javax.validation.Valid;

@RestController
public class CommentController implements CommentsApi {

    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    @CanAddComment
    @Override
    public void addComment(@PathVariable Long id, @Valid @RequestBody Comment comment) {
        commentService.addComment(id, comment);
    }

    @IsAuthorOfComment
    @Override
    public void deleteComment(@PathVariable("id") Long id, @PathVariable("commentid") Long commentid) {
        commentService.deleteComment(id, commentid);
    }

    @BelongsToProposalTeam
    @Override
    public Page<Comment> getProposalComments(Pageable pageable,@PathVariable Long id) {
        return commentService.getProposalComments(pageable,id);
    }

    @IsAuthorOfComment
    @Override
    public void updateComment(@Valid @RequestBody Comment comment,@PathVariable("commentid") Long commentId,@PathVariable("id") Long id) {
        commentService.updateComment(comment,commentid,id);
    }
}
