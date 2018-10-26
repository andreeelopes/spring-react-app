package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.Services.ProposalService;
import pt.unl.fct.ecma.models.*;
import pt.unl.fct.ecma.repositories.ProposalRepository;

import javax.validation.Valid;

@RestController
public class ProposalController implements ProposalsApi {

    private ProposalService proposalService;

    ProposalController(ProposalService proposalService){
        this.proposalService = proposalService;
    }
    @Override
    public void addBidToProposal(@PathVariable Long id, @Valid @RequestBody Bid bid) {
        proposalService.addBidToProposal(id,bid);
    }

    @Override
    public void addComment(@PathVariable Long id, @Valid  @RequestBody Comment comment) {
        proposalService.addComment(id,comment);
    }

    @Override
    public void addPartner(@PathVariable Long id, @Valid @RequestBody Employee member) {
        proposalService.addPartner(id,member);
    }

    @Override
    public void addProposal(@Valid @RequestBody Proposal proposal) {
        proposalService.addProposal(proposal);
    }

    @Override
    public void addReview(@PathVariable Long id, @Valid @RequestBody Review review) {
        proposalService.addReview(id,review);
    }

    @Override
    public void addSection(@PathVariable Long id, @Valid @RequestBody Section section) {
        proposalService.addSection(id,section);
    }

    @Override
    public void addStaffMember(@PathVariable Long id, @Valid @RequestBody Employee staffMember) {
        proposalService.addStaffMember(id,staffMember);
    }

    @Override
    public void deleteBid(Long id, Long bidid) {

    }

    @Override
    public void deleteComment(Long id, Long commentid) {

    }

    @Override
    public void deletePartner(Long id, Long partnerid) {

    }

    @Override
    public void deleteProposal(Long id) {

    }

    @Override
    public void deleteReview(Long id, Long reviewid) {

    }

    @Override
    public void deleteSection(Long id, Long sectionid) {

    }

    @Override
    public void deleteStaff(Long id, Long staffid) {

    }

    @Override
    public Page<Proposal> findProposal(Pageable pageable, @Valid String title) {
        return null;
    }

    @Override
    public Page<Bid> getBids(Pageable pageable, Long id) {
        return null;
    }

    @Override
    public Proposal getProposal(Long id) {
        return null;
    }

    @Override
    public Page<Comment> getProposalComments(Pageable pageable, Long id) {
        return null;
    }

    @Override
    public Page<Employee> getProposalMembers(Pageable pageable, Long id) {
        return null;
    }

    @Override
    public Page<Review> getProposalReviews(Pageable pageable, Long id) {
        return null;
    }

    @Override
    public Page<Section> getProposalSections(Pageable pageable, Long id) {
        return null;
    }

    @Override
    public Page<Employee> getStaffMembers(Pageable pageable, Long id) {
        return null;
    }

    @Override
    public void reviewid(@Valid Review review, Long reviewid, Long id) {

    }

    @Override
    public void updateBid(@Valid Bid review, Long bidid, Long id) {

    }

    @Override
    public void updateComment(@Valid Comment section, Long commentid, Long id) {

    }

    @Override
    public void updateSection(@Valid Section section, Long sectionid, Long id) {

    }
}
