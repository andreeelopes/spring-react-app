package pt.unl.fct.ecma.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.models.*;
import pt.unl.fct.ecma.repositories.CommentRepository;
import pt.unl.fct.ecma.repositories.CompanyRepository;
import pt.unl.fct.ecma.repositories.EmployeeRepository;
import pt.unl.fct.ecma.repositories.ProposalRepository;

import java.util.Optional;

@Service
public class MySecurityService {

    private EmployeeRepository peopleRepository;
    private ProposalRepository proposalRepository;
    private CompanyRepository companyRepository;
    private CommentRepository commentRepository;



    public MySecurityService(EmployeeRepository peopleRepository, ProposalRepository proposalRepository,
                             CompanyRepository companyRepository, CommentRepository commentRepository) {
        this.peopleRepository = peopleRepository;
        this.proposalRepository = proposalRepository;
        this.companyRepository = companyRepository;
        this.commentRepository = commentRepository;

    }

    public boolean isPrincipal(User user, Long id) {
        Optional<Employee> person = peopleRepository.findById(id);

        return person.isPresent() && person.get().getUsername().equals(user.getUsername());
    }

    public boolean isApproverOfProposal(User user,Long proposalid){
        Employee person = peopleRepository.findByUsername(user.getUsername());
        Optional<Proposal> proposal = proposalRepository.findById(proposalid);
        return proposal.isPresent() && proposal.get().getApprover().getId().equals(person.getId());
    }

    public boolean belongsToTeamProposal(User user,Long proposalid){
        Employee person = peopleRepository.findByUsername(user.getUsername());
        Optional<Proposal> proposal = proposalRepository.findById(proposalid);
        return proposal.isPresent() && (proposalRepository.partnerExists(proposalid,person.getId()).size()>0 || proposalRepository.staffExists(proposalid,person.getId()).size()>0);
    }

    public boolean bidHasPrincipal(User user, Bid bid){
        Employee person = peopleRepository.findByUsername(user.getUsername());
        return bid.getBidder().getUsername().equals(person.getUsername());
    }

    public boolean isAuthorOfComment(User user, Comment comment){

        return comment.getAuthor().getUsername().equals(user.getUsername());
    }
    public boolean IsAdminOfCompany(User user,Long id){
        Employee employee = peopleRepository.findByUsername(user.getUsername());
        return employee.isAdmin() && employee.getCompany().getId().equals(id);

    }

    public boolean isAuthorOfExistingComment(User user, Long commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        boolean isAuthor = false;

        if(comment.isPresent()){
            isAuthor = isAuthorOfComment(user, comment.get());
        }

        return isAuthor;
    }

}