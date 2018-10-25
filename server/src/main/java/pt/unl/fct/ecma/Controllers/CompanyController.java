package pt.unl.fct.ecma.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.Entity.Company;
import pt.unl.fct.ecma.Entity.Employee;
import pt.unl.fct.ecma.Services.CompanyService;

import javax.validation.Valid;
@RestController
public class CompanyController implements CompaniesApi {
    CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @Override
    public void addAdmin(@Valid @RequestBody Employee employee,@PathVariable Long id) {
        companyService.addAdmin(employee,id);
    }

    @Override
    public void addCompany(@Valid @RequestBody Company company) {
        companyService.addCompany(company);
    }

    @Override
    public void addEmployee(@Valid @RequestBody Employee employee, @PathVariable Long id) {
        companyService.addEmployee(employee,id);
    }

    @Override
    public void deleteAdmin(@PathVariable("id") Long id,@PathVariable("adminId")  Long adminId) {
        companyService.deleteAdmin(id,adminId);
    }

    @Override
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }

    @Override
    public void fireEmployee(Long id, Long employeeid) {

    }

    @Override
    public Page<Employee> getAdminsOfCompany(Pageable pageable, Long id, @Valid String search) {
        return null;
    }

    @Override
    public Page<Company> getCompanies(Pageable pageable, @Valid String search) {
        return null;
    }

    @Override
    public Company getCompany(Long id) {
        return null;
    }

    @Override
    public Page<Employee> getEmployeesOfCompany(Pageable pageable, Long id, @Valid String search) {
        return null;
    }

    @Override
    public void updateCompany(@Valid Company company, Long id) {

    }
}
