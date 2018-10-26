package pt.unl.fct.ecma.Services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.errors.NotFoundException;
import pt.unl.fct.ecma.models.*;
import pt.unl.fct.ecma.repositories.*;

import javax.transaction.Transactional;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProposalService {

    private final CommentRepository commentRepository;
    private EmployeeRepository employeeRepository;
    private ProposalRepository proposalRepository;
    private ReviewRepository reviewRepository;
    private SectionRepository sectionRepository;

    public ProposalService(ProposalRepository proposalRepository, EmployeeRepository employeeRepository, CommentRepository commentRepository,ReviewRepository reviewRepository,SectionRepository sectionRepository){
        this.commentRepository = commentRepository;
        this.proposalRepository = proposalRepository;
        this.employeeRepository = employeeRepository;
        this.reviewRepository = reviewRepository;
        this.sectionRepository = sectionRepository;
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
            }else throw new BadRequestException("The id of employee in bid is invalid");

    }
    //Comment need to have user

    public void addComment(Long id, Comment comment) {

            Proposal realProposal = findById(id);
            Employee author = comment.getAuthor();
            if(author != null) {
                Long authorid = author.getId();
                findEmployeeById(authorid);
                if (realProposal.getId() == authorid) {
                    realProposal.getComments().add(comment);
                    comment.setProposal(realProposal);
                    proposalRepository.save(realProposal);
                }
                else throw new BadRequestException("The id of comment is invalid");
            }else throw new BadRequestException("The comment doesnt have author");

    }

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


    public void addProposal(Proposal proposal) {
        if(proposal.getId()==null) {
            proposalRepository.save(proposal);
        }else throw new BadRequestException("The proposal id should be null");
    }



    public void addReview(Long id, Review review) {
        Proposal realProposal = findById(id);
        Employee author = review.getAuthor();
        if(author != null) {
            Long authorid = author.getId();
            findEmployeeById(authorid);
            if (realProposal.getId() == authorid) {
                realProposal.getReviews().add(review);
                review.setProposal(realProposal);
                proposalRepository.save(realProposal);
            }
            else throw new BadRequestException("The id of comment is invalid");
        }else throw new BadRequestException("The comment doesnt have author");

        proposalRepository.save(realProposal);

    }
    // TODO: How i know who can edit section
    public void addSection(Long id, Section section) {
        Proposal proposal= findById(id);
        proposal.getSections().add(section);

        proposalRepository.save(proposal);
    }

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

    public void deleteBid(Long id,Long employeeid) {
        findById(id);
        findEmployeeById(employeeid);
        List<Bid> bids=proposalRepository.existsBid(id,employeeid);
        if(bids.size()>0)
        proposalRepository.deleteBidById(id,employeeid);
        else throw new NotFoundException(String.format("Proposal with id %d does not have a bidder with id %d", id,employeeid));
    }

    public void deleteComment(Long id, Long commentid) {
        Comment comment = findCommentById(commentid);
       if(comment.getId()==id) {
           commentRepository.delete(comment);
       }else throw new NotFoundException(String.format("Comment with id %d does not have a proposal with id %d", commentid,id));
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

    private Comment findCommentById(Long id){
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()) {
            return comment.get();
        }else throw new NotFoundException(String.format("Comment with id %d does not exist", id));
    }

    public void deletePartner(Long id, Long partnerid) {
        findById(id);
        findEmployeeById(partnerid);
        List<ProposalRole> r = proposalRepository.PartnerExists(id,partnerid);
        if(r.size()>0)
        proposalRepository.deletePartner(id,partnerid);
        else throw new NotFoundException(String.format("Proposal with id %d does not have partner with id %d", id,partnerid));
    }
    public void deleteStaff(Long id, Long staffid) {
        findById(id);
        findEmployeeById(staffid);
        List<ProposalRole> r = proposalRepository.staffExists(id,staffid);
        if(r.size()>0)
            proposalRepository.deleteStaff(id,staffid);
        else throw new NotFoundException(String.format("Proposal with id %d does not have partner with id %d", id,staffid));
    }
    public void deleteProposal(Long id) {
        Proposal prop = findById(id);
        proposalRepository.delete(prop);
    }

    public void deleteReview(Long id, Long reviewid) {
        Review review = findReviewById(reviewid);
        if(review.getProposal().getId()==id){
            reviewRepository.delete(review);
        }
        else throw new NotFoundException(String.format("Review with id %d does not have a proposal with id %d", reviewid,id));
    }
    private Review findReviewById(Long id){
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()) {
            return review.get();
        }else throw new NotFoundException(String.format("Review with id %d does not exist", id));
    }
    private Section findSectionById(Long id){
        Optional<Section> section = sectionRepository.findById(id);
        if(section.isPresent()) {
            return section.get();
        }else throw new NotFoundException(String.format("Review with id %d does not exist", id));
    }
    public void deleteSection(Long id, Long sectionid) {
        Section section=findSectionById(sectionid);
        if(section.getId()==id){
            sectionRepository.delete(section);
        }else throw new NotFoundException(String.format("Section with id %d does not have a proposal with id %d", sectionid,id));
    }
}
