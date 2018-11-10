package pt.unl.fct.ecma.services;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProposalRepository proposalRepository;


    @Transactional
    public void addBidToProposal(Proposal proposal, Employee bidder) {
        Bid newBid = new Bid();

        newBid.setBidder(bidder);
        newBid.setProposal(proposal);
        newBid.setStatus("WAITING");

        proposal.getBids().add(newBid);

        proposalRepository.save(proposal);
    }

    public void deleteBid(Proposal proposal, Employee employee) {
        proposalRepository.deleteBidById(proposal.getId(), employee.getId());
    }

    public Page<Bid> getBids(Pageable pageable, Proposal proposal) {
        return proposalRepository.findAllBids(pageable, proposal.getId());
    }

    public void updateBid(Bid bid) {
        proposalRepository.changeBidStatus(bid.getStatus(), bid.getBidder().getId(), bid.getProposal().getId());
    }

    public boolean bidBelongsToProposal(Proposal proposal, Employee bidder) {
        List<Bid> bids = proposalRepository.existsBid(proposal.getId(), bidder.getId());
        if (bids.size() > 0) {
            return true;
        } else throw new BadRequestException("This bid does not exist");
    }
}
