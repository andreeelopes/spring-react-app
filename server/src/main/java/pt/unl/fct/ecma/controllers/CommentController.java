package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.CommentsApi;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.models.Comment;
import pt.unl.fct.ecma.security.annotations.CanAddComment;
import pt.unl.fct.ecma.security.annotations.IsAuthorOfComment;
import pt.unl.fct.ecma.security.annotations.BelongsToProposalTeam;
import pt.unl.fct.ecma.services.CommentService;

import javax.validation.Valid;

@RestController
public class CommentController implements CommentsApi {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @CanAddComment
    @Override
    public void addComment(Long proposalId, Comment comment) {

        if (!comment.getProposal().getId().equals(proposalId))
            throw new BadRequestException("Ids of proposal do not match");

        if(comment.getId() != null)
            throw new BadRequestException("Can not define id of new comment");

        commentService.addComment(comment);
    }

    @IsAuthorOfComment
    @Override
    public void deleteComment(Long proposalId, Long commentId) {
        commentService.deleteComment(proposalId, commentId);
    }

    @BelongsToProposalTeam
    @Override
    public Page<Comment> getProposalComments(Pageable pageable, Long proposalId) {
        return commentService.getProposalComments(pageable, proposalId);
    }

    @IsAuthorOfComment
    @Override
    public void updateComment(Comment comment, Long commentId, Long proposalId) {

        if (!comment.getProposal().getId().equals(proposalId))
            throw new BadRequestException("Ids of proposal do not match");

        if (!comment.getId().equals(commentId))
            throw new BadRequestException("Ids of comment do not match");

        commentService.updateComment(comment);
    }
}
