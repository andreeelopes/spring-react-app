package pt.unl.fct.ecma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.ProposalsApi;
import pt.unl.fct.ecma.brokers.ProposalBroker;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.security.annotations.*;
import pt.unl.fct.ecma.services.ProposalService;
import pt.unl.fct.ecma.models.*;

import javax.validation.Valid;

@RestController
public class ProposalController implements ProposalsApi {

    @Autowired
    private ProposalBroker proposalBroker;


    @BelongsToProposalStaff
    @Override
    public void addPartner(@PathVariable("proposalId") Long proposalId,
                            @RequestBody Employee member) {
        proposalBroker.addPartner(proposalId, member);
    }

    //TODO (não e politica de seguranca) adicionar o principal a staff da proposta e verificar se o principal e o approver pertence a company do proposal
    @Override
    public void addProposal(@Valid @RequestBody Proposal proposal) {

        if (proposal.getId() != null)
            throw new BadRequestException("Can not define id of new proposal");

        proposalBroker.addProposal(proposal);
    }

    @BelongsToProposalStaff
    @Override
    public void addStaffMember(@PathVariable("proposalId") Long proposalId,
                               @RequestBody Employee staffMember) {
        proposalBroker.addStaffMember(proposalId, staffMember);
    }


    @IsApproverOfProposal
    @Override
    public void updateProposal(@PathVariable Long proposalId,
                                @RequestBody Proposal proposal) {
        proposalBroker.updateProposal(proposalId, proposal);
    }


    @BelongsToProposalStaff
    @Override
    public void deletePartner(@PathVariable("proposalId") Long proposalId,
                              @PathVariable("partnerId") Long partnerId) {
        proposalBroker.deletePartner(proposalId, partnerId);
    }

    @BelongsToProposalStaff
    @Override
    public void deleteProposal(@PathVariable("proposalId") Long proposalId) {
        proposalBroker.deleteProposal(proposalId);
    }

    @StaffIsPrincipal
    @Override
    public void deleteStaff(@PathVariable("proposalId") Long proposalId,
                            @PathVariable("staffId") Long staffId) {
        proposalBroker.deleteStaff(proposalId, staffId);
    }

    @BelongsToProposalTeam
    @Override
    public Proposal getProposal(@PathVariable("proposalId") Long proposalId) {
        return proposalBroker.getProposal(proposalId);
    }

    @BelongsToProposalTeam
    @Override
    public Page<Employee> getProposalMembers(Pageable pageable,
                                             @PathVariable("proposalId") Long proposalId) {
        return proposalBroker.getProposalMembers(proposalId, pageable);
    }

    @BelongsToProposalTeam
    @Override
    public Page<Employee> getStaffMembers(Pageable pageable,
                                          @PathVariable("proposalId") Long proposalId) {
        return proposalBroker.getProposalStaff(proposalId, pageable);
    }


}
