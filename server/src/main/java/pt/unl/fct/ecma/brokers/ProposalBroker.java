package pt.unl.fct.ecma.brokers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.models.Proposal;
import pt.unl.fct.ecma.services.EmployeeService;
import pt.unl.fct.ecma.services.ProposalService;

@Service
public class ProposalBroker {

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private EmployeeService employeeService;

    public void addPartner(Long proposalId, Employee member) {
        Proposal proposal = proposalService.getProposal(proposalId);
        employeeService.getEmployee(member.getId());

        proposalService.addPartner(proposal, member);
    }

    public void addProposal(Proposal proposal) {
        proposalService.addProposal(proposal);
    }

    public void addStaffMember(Long proposalId, Employee staffMember) {
        Proposal proposal = proposalService.getProposal(proposalId);
        employeeService.getEmployee(staffMember.getId());

        proposalService.addStaffMember(proposal, staffMember);
    }

    public void updateProposal(Long proposalId, Proposal proposal) {
        Proposal oldProposal = proposalService.getProposal(proposalId);
        proposalService.updateProposal(proposal, oldProposal);
    }

    public void deletePartner(Long proposalId, Long partnerId) {
        Proposal proposal = proposalService.getProposal(proposalId);
        Employee partnerMember = employeeService.getEmployee(partnerId);

        if (proposalService.belongsToProposalPartners(proposal, partnerMember))
            proposalService.deletePartner(proposal, partnerMember);
    }

    public void deleteProposal(Long proposalId) {
        Proposal proposal = proposalService.getProposal(proposalId);
        proposalService.deleteProposal(proposal);
    }

    public void deleteStaff(Long proposalId, Long staffId) {
        Proposal proposal = proposalService.getProposal(proposalId);
        Employee staffMember = employeeService.getEmployee(staffId);

        if (proposalService.belongsToProposalStaff(proposal, staffMember))
            proposalService.deleteStaff(proposal, staffMember);
    }

    public Proposal getProposal(Long proposalId) {
        return proposalService.getProposal(proposalId);
    }

    public Page<Employee> getProposalMembers(Long proposalId, Pageable pageable) {
        Proposal proposal = proposalService.getProposal(proposalId);
        return proposalService.getProposalMembers(proposal, pageable);
    }

    public Page<Employee> getProposalStaff(Long proposalId, Pageable pageable) {
        Proposal proposal = proposalService.getProposal(proposalId);
        return proposalService.getProposalStaff(proposal, pageable);
    }

}
