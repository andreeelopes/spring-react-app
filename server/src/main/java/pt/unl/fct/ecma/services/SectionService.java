package pt.unl.fct.ecma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.errors.NotFoundException;
import pt.unl.fct.ecma.models.Proposal;
import pt.unl.fct.ecma.models.Section;
import pt.unl.fct.ecma.repositories.ProposalRepository;
import pt.unl.fct.ecma.repositories.SectionRepository;

import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private ProposalRepository proposalRepository;


    public void deleteSection(Long proposalId, Long sectionId) {
        Section section = findSectionById(sectionId);
        Proposal proposal = findProposalById(proposalId);

        if(!sectionBelongsToProposal(section, proposal)){
            throw new BadRequestException(
                    String.format("Section with id %d does not belong to proposal with id %d",
                            sectionId, proposalId));
        }

        sectionRepository.delete(section);
    }

    public void addSection(Section section) {
        Proposal p = findProposalById(section.getProposal().getId());

        p.getSections().add(section);

        proposalRepository.save(p);
    }


    public void updateSection(Section section) {

        Section oldSection = findSectionById(section.getId());
        Proposal oldProposal = findProposalById(section.getProposal().getId());

        if(!sectionBelongsToProposal(oldSection, oldProposal)){
            throw new BadRequestException(
                    String.format("Section with id %d does not belong to proposal with id %d",
                            section.getId(), section.getProposal().getId()));
        }

        oldSection.setText(section.getText());
        oldSection.setType(section.getType());

        sectionRepository.save(oldSection);
    }

    public Page<Section> getProposalsSections(Pageable pageable, Long proposalId) {
        findProposalById(proposalId);

        return sectionRepository.findAllByProposal_Id(proposalId, pageable);

    }

    private Section findSectionById(Long sectionId){
        Optional<Section> section = sectionRepository.findById(sectionId);
        if(section.isPresent()) {
            return section.get();
        }else
            throw new NotFoundException(String.format("Section with id %d does not exist", sectionId));
    }

    private Proposal findProposalById(Long proposalId){
        Optional<Proposal> proposal = proposalRepository.findById(proposalId);
        if(proposal.isPresent()) {
            return proposal.get();
        }else
            throw new NotFoundException(String.format("Proposal with id %d does not exist", proposalId));
    }

    private boolean sectionBelongsToProposal(Section section, Proposal proposal){
        return section.getProposal().equals(proposal);
    }
}
