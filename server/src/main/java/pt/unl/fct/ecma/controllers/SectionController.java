package pt.unl.fct.ecma.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.SectionsApi;
import pt.unl.fct.ecma.brokers.SectionBroker;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.models.Section;
import pt.unl.fct.ecma.security.annotations.BelongsToProposalStaff;
import pt.unl.fct.ecma.security.annotations.BelongsToProposalTeam;

@RestController
public class SectionController implements SectionsApi {

    @Autowired
    private SectionBroker sectionBroker;

    @BelongsToProposalStaff
    @Override
    public void addSection(Long proposalId, Section section) {

        if (!section.getProposal().getId().equals(proposalId))
            throw new BadRequestException("Ids of proposal do not match");

        if (section.getId() != null)
            throw new BadRequestException("Can not define id of new section");

        sectionBroker.addSection(section);
    }

    @BelongsToProposalStaff
    @Override
    public void deleteSection(Long proposalId, Long sectionId) {
        sectionBroker.deleteSection(proposalId, sectionId);
    }

    @BelongsToProposalTeam
    @Override
    public Page<Section> getProposalSections(Pageable pageable, Long proposalId) {
        return sectionBroker.getProposalsSections(pageable, proposalId);
    }

    @BelongsToProposalStaff
    @Override
    public void updateSection(Section section, Long sectionId, Long proposalId) {

        if (!section.getProposal().getId().equals(proposalId))
            throw new BadRequestException("Ids of proposal do not match");

        if (!section.getId().equals(sectionId))
            throw new BadRequestException("Ids of section do not match");

        sectionBroker.updateSection(section);
    }
}
