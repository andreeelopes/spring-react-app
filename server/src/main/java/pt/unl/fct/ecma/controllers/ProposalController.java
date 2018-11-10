package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.ProposalsApi;
import pt.unl.fct.ecma.errors.BadRequestException;
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

    ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }


    @BelongsToProposalStaff
    @Override
    public void addPartner(Long proposalId, Employee member) {
        proposalService.addPartner(proposalId, member);
    }

    //TODO (não e politica de seguranca) adicionar o principal a staff da proposta e verificar se o principal e o approver pertence a company do proposal
    @Override
    public void addProposal(Proposal proposal) {

        if (proposal.getId() != null)
            throw new BadRequestException("Can not define id of new proposal");

        proposalService.addProposal(proposal);
    }

    @BelongsToProposalStaff
    @Override
    public void addStaffMember(Long proposalId, Employee staffMember) {
        proposalService.addStaffMember(proposalId, staffMember);
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
    public void deletePartner(Long proposalId, Long partnerId) {
        proposalService.deletePartner(proposalId, partnerId);
    }

    @BelongsToProposalStaff
    @Override
    public void deleteProposal(Long proposalId) {
        proposalService.deleteProposal(proposalId);
    }

    @IsPrincipal //TODO verificar isto
    @Override
    public void deleteStaff(Long proposalId, Long staffId) {
        proposalService.deleteStaff(proposalId, staffId);
    }

    @BelongsToProposalTeam
    @Override
    public Proposal getProposal(Long proposalId) {
        return proposalService.getProposal(proposalId);
    }

    @BelongsToProposalTeam
    @Override
    public Page<Employee> getProposalMembers(Pageable pageable, Long proposalId) {
        return proposalService.getProposalMembers(proposalId, pageable);
    }

    @BelongsToProposalTeam
    @Override
    public Page<Employee> getStaffMembers(Pageable pageable, Long proposalId) {
        return proposalService.getProposalStaff(proposalId, pageable);
    }


}
