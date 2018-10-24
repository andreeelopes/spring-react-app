package pt.unl.fct.ecma.Services;

import pt.unl.fct.ecma.repositories.CompanyRepository;

public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }
}
