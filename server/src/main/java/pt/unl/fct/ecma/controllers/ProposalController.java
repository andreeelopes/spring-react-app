package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.ProposalsApi;
import pt.unl.fct.ecma.security.annotations.BelongsToProposalStaff;
import pt.unl.fct.ecma.security.annotations.BelongsToProposalTeam;
import pt.unl.fct.ecma.security.annotations.IsApproverOfProposal;
import pt.unl.fct.ecma.security.annotations.IsPrincipal;
import pt.unl.fct.ecma.services.ProposalService;
import pt.unl.fct.ecma.models.*;

import javax.validation.Valid;

@RestController
public class ProposalController implements ProposalsApi {

    private ProposalService proposalService;

    ProposalController(ProposalService proposalService){
        this.proposalService = proposalService;
    }
/*
    // tem de ser membro ou staff
    @Override
    public Page<Proposal> getAllProposals(Pageable pageable, @Valid @RequestParam(value = "search", required = false) String search) {
        if(search == null)
            return proposalService.getAllProposals(pageable);
        else
            return proposalService.getProposalsByStatus(search, pageable);
    }*/

    @BelongsToProposalStaff
    @Override
    public void addPartner(@PathVariable Long proposalId, @Valid @RequestBody Employee member) {
        proposalService.addPartner(proposalId, member);
    }

    //TODO (não e politica de seguranca) adicionar o principal a staff da proposta e verificar se o principal e o approver pertence a company do proposal
    @Override
    public void addProposal(@Valid @RequestBody Proposal proposal) {
        proposalService.addProposal(proposal);
    }

    @BelongsToProposalStaff
    @Override
    public void addStaffMember(@PathVariable Long id, @Valid @RequestBody Employee staffMember) {
        proposalService.addStaffMember(id, staffMember);
    }

    /*
    @IsApproverOfProposal
    @Override
    public void updateProposal(@PathVariable Long proposalId, @Valid @RequestBody Proposal proposal){
        //TODO implementar
    }
    */

    @BelongsToProposalStaff
    @Override
    public void deletePartner(@PathVariable("id") Long id, @PathVariable("partnerid") Long partnerid) {
        proposalService.deletePartner(id, partnerid);
    }

    @BelongsToProposalStaff
    @Override
    public void deleteProposal(@PathVariable("id") Long id) {
        proposalService.deleteProposal(id);
    }

    @IsPrincipal //TODO verificar isto
    @Override
    public void deleteStaff(@PathVariable("id") Long proposalId, @PathVariable("staffid") Long id) {
        proposalService.deleteStaff(proposalId, id);
    }

    @BelongsToProposalTeam
    @Override
    public Proposal getProposal(@PathVariable("id") Long id) {
        return proposalService.getProposal(id);
    }

    @BelongsToProposalTeam
    @Override
    public Page<Employee> getProposalMembers(Pageable pageable, @PathVariable("id") Long id) {
        return proposalService.getProposalMembers(id, pageable);
    }

    @BelongsToProposalTeam
    @Override
    public Page<Employee> getStaffMembers(Pageable pageable, @PathVariable("id") Long id) {
        return proposalService.getProposalStaff(id, pageable);
    }




}
