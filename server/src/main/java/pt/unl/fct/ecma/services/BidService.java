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

    public BidService(EmployeeRepository employeeRepository, ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void addBidToProposal(Bid bid) {
        Long bidderId = bid.getBidder().getId();
        Optional<Employee> employee = employeeRepository.findById(bidderId);

        if (employee.isPresent()) {
            if (!(proposalRepository.existsBid(bid.getProposal().getId(), bidderId).size() > 0)) {
                Bid newBid = new Bid();
                Proposal realProposal = findProposalById(bid.getProposal().getId());
                Employee realEmployee = employee.get();

                newBid.setBidder(realEmployee);
                newBid.setProposal(realProposal);
                newBid.setStatus("WAITING");

                realProposal.getBids().add(newBid);

                employeeRepository.save(realEmployee);
            } else throw new BadRequestException("The user already did the bid");
        } else throw new BadRequestException("The id of employee in bid is invalid");

    }

    public void deleteBid(Long proposalId, Long employeeId) {
        findProposalById(proposalId);
        findEmployeeById(employeeId);
        List<Bid> bids = proposalRepository.existsBid(proposalId, employeeId);
        if (bids.size() > 0)
            proposalRepository.deleteBidById(proposalId, employeeId);
        else
            throw new NotFoundException(String.format("Proposal with id %d does not have a bidder with id %d", proposalId, employeeId));
    }

    public Page<Bid> getBids(Pageable pageable, Long proposalId) {
        findProposalById(proposalId);
        return proposalRepository.findAllBids(pageable, proposalId);
    }

    public void updateBid(Bid bid) {
        findProposalById(bid.getProposal().getId());

        List<Bid> bids = proposalRepository.existsBid(bid.getProposal().getId(), bid.getBidder().getId());
        if (bids.size() > 0) {
            proposalRepository.changeBidStatus(bid.getStatus(), bid.getBidder().getId(), bid.getProposal().getId());
        } else throw new BadRequestException("This bid does not exist");
    }


    private Employee findEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return employee.get();
        } else throw new NotFoundException(String.format("Employee with id %d does not exist", employeeId));
    }

    private Proposal findProposalById(Long proposalId) {
        Optional<Proposal> proposal = proposalRepository.findById(proposalId);
        if (proposal.isPresent()) {
            return proposal.get();
        } else throw new NotFoundException(String.format("Proposal with id %d does not exist", proposalId));
    }
}
