package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.CompaniesApi;
import pt.unl.fct.ecma.models.Company;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.services.CompanyService;

import javax.validation.Valid;

@RestController
public class CompanyController implements CompaniesApi {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) { //companyService should be static
        this.companyService = companyService;
    }

    //hasRole(ADMIN) && verificar se é admin da empresa do novo admin
    @Override
    public void addAdmin(@Valid @RequestBody Employee employee, @PathVariable Long id) {
        companyService.addAdmin(employee, id);
    }

    //hasRole(ADMIN)
    @Override
    public void addCompany(@Valid @RequestBody Company company) {
        companyService.addCompany(company);
    }

    //admin daquela empresa
    @Override
    public void addEmployee(@Valid @RequestBody Employee employee, @PathVariable Long id) {
        companyService.addEmployee(employee, id);
    }

    //hasRole(ADMIN)
    @Override
    public void deleteAdmin(@PathVariable("id") Long id, @PathVariable("adminId") Long adminId) {
        companyService.deleteAdmin(id, adminId);
    }

    //hasRole(ADMIN) e admin da empresa
    @Override
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }

    //admin daquela empresa
    @Override
    public void fireEmployee(@PathVariable("id")Long id, @PathVariable("employeeid") Long employeeid) {
        companyService.deleteEmployee(id, employeeid);
    }


    @Override
    public Page<Employee> getAdminsOfCompany(Pageable pageable, @PathVariable Long id) {

        return companyService.getAdminsOfCompany(id, pageable);
    }

    @Override
    public
Page<Company> getCompanies(Pageable pageable,
                                      @Valid @RequestParam(value = "search", required = false) String search) {
        return companyService.getAllCompanies(pageable, search);
    }


    @Override
    public Company getCompany(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }


    @Override
    public Page<Employee> getEmployeesOfCompany(Pageable pageable, @PathVariable Long id, @Valid String search) {

        return companyService.getEmployeesOfCompany(pageable, id, search);
    }

    //admin da empresa
    @Override
    public void updateCompany(@Valid @RequestBody Company company, @PathVariable Long id) {
        companyService.updateCompany(company, id);
    }
}
