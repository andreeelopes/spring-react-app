package pt.unl.fct.ecma.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.models.*;
import pt.unl.fct.ecma.repositories.CompanyRepository;
import pt.unl.fct.ecma.repositories.EmployeeRepository;
import pt.unl.fct.ecma.repositories.ProposalRepository;

import java.util.Optional;

@Service
public class MySecurityService {
    private EmployeeRepository people;
    private ProposalRepository proposalRep;
    private CompanyRepository companyRepository;


    public MySecurityService(EmployeeRepository people,ProposalRepository proposalRepository,CompanyRepository companyRepository) {
        this.people = people;
        this.proposalRep = proposalRepository;
        this.companyRepository=companyRepository;

    }

    public boolean isPrincipal(User user, Long id) {
        Optional<Employee> person = people.findById(id);

        return person.isPresent() && person.get().getUsername().equals(user.getUsername());
    }

    public boolean isApproverOfProposal(User user,Long proposalid){
        Employee person = people.findByUsername(user.getUsername());
        Optional<Proposal> proposal = proposalRep.findById(proposalid);
        return proposal.isPresent() && proposal.get().getApprover().getId().equals(person.getId());
    }

    public boolean belongsToTeamProposal(User user,Long proposalid){
        Employee person = people.findByUsername(user.getUsername());
        Optional<Proposal> proposal = proposalRep.findById(proposalid);
        return proposal.isPresent() && (proposalRep.partnerExists(proposalid,person.getId()).size()>0 || proposalRep.staffExists(proposalid,person.getId()).size()>0);
    }

    public boolean bidHasPrincipal(User user, Bid bid){
        Employee person = people.findByUsername(user.getUsername());
        return bid.getBidder().getUsername().equals(person.getUsername());
    }

    public boolean isAuthorOfComment(User user, Comment comment){

        return comment.getAuthor().getUsername().equals(user.getUsername());
    }
    public boolean IsAdminOfCompany(User user,Long id){
        Employee employee = people.findByUsername(user.getUsername());
        return employee.isAdmin() && employee.getCompany().getId().equals(id);

    }

}