package pt.unl.fct.ecma.Services;

import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.errors.NotFoundException;
import pt.unl.fct.ecma.models.*;
import pt.unl.fct.ecma.repositories.EmployeeRepository;
import pt.unl.fct.ecma.repositories.ProposalRepository;

import javax.transaction.Transactional;
import java.util.EnumMap;
import java.util.Optional;

@Service
public class ProposalService {

    private EmployeeRepository employeeRepository;
    private ProposalRepository proposalRepository;

    public ProposalService(ProposalRepository proposalRepository,EmployeeRepository employeeRepository){
        this.proposalRepository = proposalRepository;
        this.employeeRepository = employeeRepository;
    }

    //Employee é inviado pela bid
    @Transactional
    public void addBidToProposal(Long id, Bid bid) {
        Long bidid = bid.getBidder().getId();

            Optional<Employee> employee = employeeRepository.findById(bidid);
            if(employee.isPresent()){

                Bid newBid = new Bid();
                Proposal realProposal = findById(id);
                Employee realEmployee = employee.get();

                newBid.setBidder(realEmployee);
                newBid.setProposal(realProposal);
                newBid.setStatus("WAITING");


                realProposal.getBids().add(newBid);

                employeeRepository.save(realEmployee);
            }throw new BadRequestException("The id of employee in bid is invalid");

    }
    //Comment need to have user
    // TODO: 26-10-2018 Need to be tested..
    public void addComment(Long id, Comment comment) {

            Proposal realProposal = findById(id);
            Employee author = comment.getAuthor();
            if(author != null) {
                if (realProposal.getId() == author.getId()) {
                    realProposal.getComments().add(comment);
                    proposalRepository.save(realProposal);
                }
                throw new BadRequestException("The id of comment is invalid");
            }throw new BadRequestException("The comment doesnt have author");

    }
    // TODO: 26-10-2018 Need to be tested..
    public void addPartner(Long id, Employee member) {
        Proposal realProposal = findById(id);
        findEmployeeById(member.getId());
        ProposalRole proposalRole = new ProposalRole();

        proposalRole.setEmployee(member);
        proposalRole.setProposal(realProposal);
        proposalRole.setRole("PARTNER");

        realProposal.getTeam().add(proposalRole);

        proposalRepository.save(realProposal);
    }

    // TODO: 26-10-2018 Need to be tested..
    public void addProposal(Proposal proposal) {
        if(proposal.getId()==null) {
            proposalRepository.save(proposal);
        }throw new BadRequestException("The proposal id should be null");
    }

    private Proposal findById(Long id){
        Optional<Proposal> proposal = proposalRepository.findById(id);
        if(proposal.isPresent()) {
            return proposal.get();
        }else throw new NotFoundException(String.format("Proposal with id %d does not exist", id));
    }

    private Employee findEmployeeById(Long id){
            Optional<Employee> employee = employeeRepository.findById(id);
            if(employee.isPresent()) {
                return employee.get();
            }else throw new NotFoundException(String.format("Employee with id %d does not exist", id));
    }
    // TODO: 26-10-2018 Need to be tested..
    public void addReview(Long id, Review review) {
        Proposal proposal= findById(id);
        proposal.getReviews().add(review);

        proposalRepository.save(proposal);

    }
    // TODO: 26-10-2018 Need to be tested..
    public void addSection(Long id, Section section) {
        Proposal proposal= findById(id);
        proposal.getSections().add(section);

        proposalRepository.save(proposal);
    }
    //TODO: employee must be a admin?
    public void addStaffMember(Long id, Employee staffMember) {
        Proposal realProposal = findById(id);
        findEmployeeById(staffMember.getId());
        ProposalRole proposalRole = new ProposalRole();

        proposalRole.setEmployee(staffMember);
        proposalRole.setProposal(realProposal);
        proposalRole.setRole("STAFF");

        realProposal.getTeam().add(proposalRole);

        proposalRepository.save(realProposal);
    }
}
