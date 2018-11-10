package pt.unl.fct.ecma.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.SectionsApi;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.models.Section;
import pt.unl.fct.ecma.security.annotations.BelongsToProposalStaff;
import pt.unl.fct.ecma.security.annotations.BelongsToProposalTeam;
import pt.unl.fct.ecma.services.SectionService;

import javax.validation.Valid;

@RestController
public class SectionController implements SectionsApi {

    @Autowired
    SectionService sectionService;

    @BelongsToProposalStaff
    @Override
    public void addSection(Long proposalId, Section section) {

        if(!section.getProposal().getId().equals(proposalId))
            throw new BadRequestException("Ids of proposal do not match");

        if(section.getId() != null)
            throw new BadRequestException("Can not define id of new section");

        sectionService.addSection(section);
    }

    @BelongsToProposalStaff
    @Override
    public void deleteSection(Long proposalId, Long sectionId) {
        sectionService.deleteSection(proposalId, sectionId);
    }

    @BelongsToProposalTeam
    @Override
    public Page<Section> getProposalSections(Pageable pageable, Long proposalId) {
        return sectionService.getProposalsSections(pageable, proposalId);
    }

    @BelongsToProposalStaff
    @Override
    public void updateSection(Section section, Long sectionId, Long proposalId) {

        if(!section.getProposal().getId().equals(proposalId))
            throw new BadRequestException("Ids of proposal do not match");

        if(!section.getId().equals(sectionId))
            throw new BadRequestException("Ids of section do not match");

        sectionService.updateSection(section);
    }
}
