package pt.unl.fct.ecma.services;

import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.repositories.SectionRepository;

@Service
public class SectionService {
    private SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository){
        this.sectionRepository = sectionRepository;
    }
}
