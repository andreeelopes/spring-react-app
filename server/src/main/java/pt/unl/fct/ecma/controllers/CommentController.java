package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.CommentsApi;
import pt.unl.fct.ecma.models.Comment;

import javax.validation.Valid;

@RestController
public class CommentController implements CommentsApi {


    @Override
    public void addComment(@PathVariable Long id, @Valid @RequestBody Comment comment) {
        proposalService.addComment(id, comment);
    }


    @Override
    public void deleteComment(@PathVariable("id") Long id, @PathVariable("commentid") Long commentid) {
        proposalService.deleteComment(id, commentid);
    }


    @Override
    public Page<Comment> getProposalComments(Pageable pageable, Long id) {
        return null;
    }


    @Override
    public void updateComment(@Valid Comment section, Long commentid, Long id) {

    }
}
