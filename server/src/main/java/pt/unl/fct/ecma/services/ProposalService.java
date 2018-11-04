package pt.unl.fct.ecma.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.errors.NotFoundException;
import pt.unl.fct.ecma.models.*;
import pt.unl.fct.ecma.repositories.*;

import javax.transaction.Transactional;
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

    public Proposal findById(Long id){
        Optional<Proposal> proposal = proposalRepository.findById(id);
        if(proposal.isPresent()) {
            return proposal.get();
        }else throw new NotFoundException(String.format("Proposal with id %d does not exist", id));
    }

    public void deletePartner(Long id, Long partnerid) {
        findById(id);
        findEmployeeById(partnerid);
        List<ProposalRole> r = proposalRepository.partnerExists(id,partnerid);
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

    public Proposal getProposal(Long id) {

        return findById(id);
    }

    public Page<Employee> getProposalMembers(Long id,Pageable pageable) {
        return proposalRepository.getProposalMembers(id,pageable);
    }

    public Page<Employee> getProposalStaff(Long id,Pageable pageable) {
        return proposalRepository.getProposalStaff(id,pageable);
    }

    public Page<Proposal> getAllProposals(Pageable pageable) {
        return proposalRepository.findAll(pageable);
    }

    public Page<Proposal> getProposalsByStatus(String search, Pageable pageable) {
        return proposalRepository.findByStatus(pageable, search);
    }


    private Employee findEmployeeById(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        }else throw new NotFoundException(String.format("Employee with id %d does not exist", id));
    }
}
