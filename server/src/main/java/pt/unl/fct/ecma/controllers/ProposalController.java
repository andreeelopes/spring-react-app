package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.ProposalsApi;
import pt.unl.fct.ecma.services.ProposalService;
import pt.unl.fct.ecma.models.*;

import javax.validation.Valid;

@RestController
public class ProposalController implements ProposalsApi {

    private ProposalService proposalService;

    ProposalController(ProposalService proposalService){
        this.proposalService = proposalService;
    }

    //só se pode ir buscar as propostas em que participei
    @Override
    public Page<Proposal> getAllProposals(Pageable pageable, @Valid @RequestParam(value = "search", required = false) String search) {
        if(search == null)
            return proposalService.getAllProposals(pageable);
        else
            return proposalService.getProposalsByStatus(search, pageable);

    }

    //staff
    @Override
    public void addPartner(@PathVariable Long id, @Valid @RequestBody Employee member) {
        proposalService.addPartner(id,member);
    }

    //isPrincipal
    @Override
    public void addProposal(@Valid @RequestBody Proposal proposal) {
        proposalService.addProposal(proposal);
    }

    //ser do staff da proposal
    @Override
    public void addStaffMember(@PathVariable Long id, @Valid @RequestBody Employee staffMember) {
        proposalService.addStaffMember(id,staffMember);
    }

    //approver?
    @Override
    public void deletePartner(@PathVariable("id") Long id, @PathVariable("partnerid") Long partnerid) {
        proposalService.deletePartner(id,partnerid);
    }

    //approver?
    @Override
    public void deleteProposal(@PathVariable("id") Long id) {
        proposalService.deleteProposal(id);
    }

    //approver?
    @Override
    public void deleteStaff(@PathVariable("id") Long id, @PathVariable("staffid") Long staffid) {
        proposalService.deleteStaff(id,staffid);
    }

    //pertencer à team
    @Override
    public Proposal getProposal(@PathVariable("id") Long id) {
        return proposalService.getProposal(id);
    }

    //pertencer à team
    @Override
    public Page<Employee> getProposalMembers(Pageable pageable,@PathVariable("id") Long id) {
        return proposalService.getProposalMembers(id,pageable);
    }

    //pertencer à team
    @Override
    public Page<Employee> getStaffMembers(Pageable pageable,@PathVariable("id") Long id) {
        return proposalService.getProposalStaff(id,pageable);
    }




}
