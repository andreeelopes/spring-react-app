package pt.unl.fct.ecma.Services;

import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.repositories.CompanyRepository;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }
}
