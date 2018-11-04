package pt.unl.fct.ecma.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.errors.NotFoundException;
import pt.unl.fct.ecma.models.Bid;
import pt.unl.fct.ecma.models.Comment;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.models.Proposal;
import pt.unl.fct.ecma.repositories.EmployeeRepository;
import pt.unl.fct.ecma.repositories.ProposalRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class BidService {
    private final EmployeeRepository employeeRepository;
    private final ProposalRepository proposalRepository;

    public BidService(EmployeeRepository employeeRepository,ProposalRepository proposalRepository){
        this.proposalRepository = proposalRepository;
        this.employeeRepository = employeeRepository;
    }

    //Employee é inviado pela bid
    @Transactional
    public void addBidToProposal(Long id, Bid bid) {
        Long bidid = bid.getBidder().getId();
        Optional<Employee> employee = employeeRepository.findById(bidid);

        if(employee.isPresent()){
            if(!(proposalRepository.existsBid(id,bidid).size()>0)){
                Bid newBid = new Bid();
                Proposal realProposal = findProposalById(id);
                Employee realEmployee = employee.get();

                newBid.setBidder(realEmployee);
                newBid.setProposal(realProposal);
                newBid.setStatus("WAITING");


                realProposal.getBids().add(newBid);

                employeeRepository.save(realEmployee);
            } else throw new BadRequestException("The user already did the bid");
        }else throw new BadRequestException("The id of employee in bid is invalid");

    }

    public void deleteBid(Long id,Long employeeid) {
        findProposalById(id);
        findEmployeeById(employeeid);
        List<Bid> bids=proposalRepository.existsBid(id,employeeid);
        if(bids.size()>0)
            proposalRepository.deleteBidById(id,employeeid);
        else throw new NotFoundException(String.format("Proposal with id %d does not have a bidder with id %d", id,employeeid));
    }

    public Page<Bid> getBids(Pageable pageable, Long id) {
        findProposalById(id);
        return proposalRepository.findAllBids(pageable,id);
    }

    public void updateBid(Bid bid, Long employeeid, Long id) {
        findProposalById(id);

        List<Bid> bids = proposalRepository.existsBid(id,employeeid);
        if(bids.size()>0) {
            proposalRepository.changeBidStatus(bid.getStatus(),employeeid,id);
        }
        else  throw new BadRequestException("This bid doesnt exist");
    }


    private Employee findEmployeeById(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        }else throw new NotFoundException(String.format("Employee with id %d does not exist", id));
    }

    private Proposal findProposalById(Long id){
        Optional<Proposal> proposal = proposalRepository.findById(id);
        if(proposal.isPresent()) {
            return proposal.get();
        }else throw new NotFoundException(String.format("Proposal with id %d does not exist", id));
    }
}
