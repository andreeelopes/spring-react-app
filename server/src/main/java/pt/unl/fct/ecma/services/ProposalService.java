package pt.unl.fct.ecma.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.errors.NotFoundException;
import pt.unl.fct.ecma.models.*;
import pt.unl.fct.ecma.repositories.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProposalService {

    private EmployeeRepository employeeRepository;
    private ProposalRepository proposalRepository;

    public ProposalService(ProposalRepository proposalRepository, EmployeeRepository employeeRepository) {
        this.proposalRepository = proposalRepository;
        this.employeeRepository = employeeRepository;
    }


    public void addPartner(Long proposalId, Employee member) {
        Proposal realProposal = getProposal(proposalId);
        findEmployeeById(member.getId());
        ProposalRole proposalRole = new ProposalRole();

        proposalRole.setEmployee(member);
        proposalRole.setProposal(realProposal);
        proposalRole.setRole("PARTNER");

        realProposal.getTeam().add(proposalRole);

        proposalRepository.save(realProposal);
    }

    public void addProposal(Proposal proposal) {
        proposalRepository.save(proposal);
    }

    public void addStaffMember(Long proposalId, Employee staffMember) {
        Proposal realProposal = getProposal(proposalId);
        findEmployeeById(staffMember.getId());
        ProposalRole proposalRole = new ProposalRole();

        proposalRole.setEmployee(staffMember);
        proposalRole.setProposal(realProposal);
        proposalRole.setRole("STAFF");

        realProposal.getTeam().add(proposalRole);

        proposalRepository.save(realProposal);
    }

    public Proposal getProposal(Long proposalId) {
        Optional<Proposal> proposal = proposalRepository.findById(proposalId);
        if (proposal.isPresent()) {
            return proposal.get();
        } else throw new NotFoundException(String.format("Proposal with id %d does not exist", proposalId));
    }

    public void deletePartner(Long proposalId, Long partnerId) {
        getProposal(proposalId);
        findEmployeeById(partnerId);
        List<ProposalRole> r = proposalRepository.partnerExists(proposalId, partnerId);
        if (r.size() > 0)
            proposalRepository.deletePartner(proposalId, partnerId);
        else
            throw new NotFoundException(String.format("Proposal with id %d does not have partner with id %d", proposalId, partnerId));
    }

    public void deleteStaff(Long proposalId, Long staffId) {
        getProposal(proposalId);
        findEmployeeById(staffId);
        List<ProposalRole> r = proposalRepository.staffExists(proposalId, staffId);
        if (r.size() > 0)
            proposalRepository.deleteStaff(proposalId, staffId);
        else
            throw new NotFoundException(String.format("Proposal with id %d does not have partner with id %d", proposalId, staffId));
    }

    public void deleteProposal(Long proposalId) {
        Proposal prop = getProposal(proposalId);
        proposalRepository.delete(prop);
    }

    public Page<Employee> getProposalMembers(Long proposalId, Pageable pageable) {
        return proposalRepository.getProposalMembers(proposalId, pageable);
    }

    public Page<Employee> getProposalStaff(Long proposalId, Pageable pageable) {
        return proposalRepository.getProposalStaff(proposalId, pageable);
    }


    private Employee findEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return employee.get();
        } else throw new NotFoundException(String.format("Employee with id %d does not exist", employeeId));
    }
}
