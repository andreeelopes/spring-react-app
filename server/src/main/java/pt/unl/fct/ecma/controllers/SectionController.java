package pt.unl.fct.ecma.controllers;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.SectionsApi;
import pt.unl.fct.ecma.models.Section;

import javax.validation.Valid;

@RestController
public class SectionController implements SectionsApi {

    //chamar apenas service do section se possivel. Esse service vai ter varios repos, o que nao e la muito bom

    @Override
    public void updateSection(@Valid Section section, Long sectionid, Long id) {

    }

    @Override
    public Page<Section> getProposalSections(Pageable pageable, Long id) {
        return null;
    }

    @Override
    public void deleteSection(@PathVariable("id") Long id, @PathVariable("sectionid") Long sectionid) {
        proposalService.deleteSection(id, sectionid);
    }


    @Override
    public void addSection(@PathVariable Long id, @Valid @RequestBody Section section) {
        proposalService.addSection(id, section);
    }
}
