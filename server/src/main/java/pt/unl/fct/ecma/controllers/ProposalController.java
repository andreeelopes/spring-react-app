package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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


    @Override
    public void addPartner(@PathVariable Long id, @Valid @RequestBody Employee member) {
        proposalService.addPartner(id,member);
    }

    @Override
    public void addProposal(@Valid @RequestBody Proposal proposal) {
        proposalService.addProposal(proposal);
    }



    @Override
    public void addStaffMember(@PathVariable Long id, @Valid @RequestBody Employee staffMember) {
        proposalService.addStaffMember(id,staffMember);
    }


    @Override
    public void deletePartner(@PathVariable("id") Long id, @PathVariable("partnerid") Long partnerid) {
        proposalService.deletePartner(id,partnerid);
    }

    @Override
    public void deleteProposal(@PathVariable("id") Long id) {
        proposalService.deleteProposal(id);
    }


    @Override
    public void deleteStaff(@PathVariable("id") Long id, @PathVariable("staffid") Long staffid) {
        proposalService.deleteStaff(id,staffid);
    }

    @Override
    public Proposal getProposal(@PathVariable("id") Long id) {
        return proposalService.getProposal(id);
    }

    @Override
    public Page<Employee> getProposalMembers(Pageable pageable,@PathVariable("id") Long id) {
        return proposalService.getProposalMembers(id,pageable);
    }


    @Override
    public Page<Employee> getStaffMembers(Pageable pageable,@PathVariable("id") Long id) {
        return proposalService.getProposalStaff(id,pageable);
    }




}
