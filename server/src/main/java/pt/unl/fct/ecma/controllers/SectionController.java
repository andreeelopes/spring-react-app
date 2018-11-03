package pt.unl.fct.ecma.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.SectionsApi;
import pt.unl.fct.ecma.models.Section;
import pt.unl.fct.ecma.services.SectionService;

import javax.validation.Valid;

@RestController
public class SectionController implements SectionsApi {

    SectionService sectionService;

    public SectionController(SectionService sectionService){
        this.sectionService = sectionService;
    }

    @Override
    public void addSection(Long id, @Valid Section section) {
        sectionService.addSection(id, section);
    }

    @Override
    public void deleteSection(Long id, Long sectionid) {
        sectionService.deleteSection(id, sectionid);
    }

    @Override
    public Page<Section> getProposalSections(Pageable pageable, Long id) {
        return sectionService.getProposalsSections(pageable, id);
    }

    @Override
    public void updateSection(@Valid Section section, Long sectionId, Long proposalId) {
        sectionService.updateSection(section, sectionId, proposalId);
    }
}
