package pt.unl.fct.ecma.Services;

import pt.unl.fct.ecma.repositories.SectionRepository;

public class SectionService {
    private SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository){
        this.sectionRepository = sectionRepository;
    }
}
