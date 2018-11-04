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
import pt.unl.fct.ecma.services.SectionService;

import javax.validation.Valid;

@RestController
public class SectionController implements SectionsApi {

    @Autowired
    SectionService sectionService;

    //staff
    @Override
    public void addSection(Long id, @Valid Section section) {
        if(id != null)
            throw new BadRequestException("Invalid id supplied");
        sectionService.addSection(id, section);
    }

    //staff
    @Override
    public void deleteSection(Long id, Long sectionid) {
        sectionService.deleteSection(id, sectionid);
    }

    //pertencer à team
    @Override
    public Page<Section> getProposalSections(Pageable pageable, Long id) {
        return sectionService.getProposalsSections(pageable, id);
    }

    //staff
    @Override
    public void updateSection(@Valid Section section, Long sectionId, Long proposalId) {
        sectionService.updateSection(section, sectionId, proposalId);
    }
}
