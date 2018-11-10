package pt.unl.fct.ecma.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.errors.NotFoundException;
import pt.unl.fct.ecma.models.Comment;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.models.Proposal;
import pt.unl.fct.ecma.repositories.CommentRepository;
import pt.unl.fct.ecma.repositories.EmployeeRepository;
import pt.unl.fct.ecma.repositories.ProposalRepository;

import java.util.Optional;

@Service
public class CommentService {
    private final ProposalRepository proposalRepository;
    private final EmployeeRepository employeeRepository;
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository, ProposalRepository proposalRepository, EmployeeRepository employeeRepository) {
        this.commentRepository = commentRepository;
        this.proposalRepository = proposalRepository;
        this.employeeRepository = employeeRepository;
    }

    public void addComment(Comment comment) {
        Proposal realProposal = findProposalById(comment.getProposal().getId());
        Employee author = comment.getAuthor();
        if (author != null) {
            Long authorid = author.getId();
            findEmployeeById(authorid);
            if (realProposal.getId().equals(authorid)) { //TODO something wrong here
                realProposal.getComments().add(comment);
                comment.setProposal(realProposal);
                proposalRepository.save(realProposal);
            } else throw new BadRequestException("The id of the comment is invalid");
        } else throw new BadRequestException("The comment does not have author");
    }

    public void deleteComment(Long proposalId, Long commentId) {
        Comment comment = findCommentById(commentId);
        if (comment.getProposal().getId().equals(proposalId)) {
            commentRepository.delete(comment);
        } else
            throw new NotFoundException(String.format("Comment with id %d does not have a proposal with id %d", commentId, proposalId));
    }

    public Page<Comment> getProposalComments(Pageable pageable, Long proposalId) {
        findProposalById(proposalId);
        return commentRepository.findAllByProposal_Id(proposalId, pageable);
    }

    public void updateComment(Comment comment) { //TODO this update does not set variables like the others?
        findProposalById(comment.getProposal().getId());
        Comment dbcomment = findCommentById(comment.getId());
        dbcomment.setComment(comment.getComment());
        commentRepository.save(dbcomment);
    }


    private Employee findEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else throw new NotFoundException(String.format("Employee with id %d does not exist", id));
    }

    private Proposal findProposalById(Long id) {
        Optional<Proposal> proposal = proposalRepository.findById(id);
        if (proposal.isPresent()) {
            return proposal.get();
        } else throw new NotFoundException(String.format("Proposal with id %d does not exist", id));
    }

    private Comment findCommentById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        } else throw new NotFoundException(String.format("Comment with id %d does not exist", id));
    }
}
