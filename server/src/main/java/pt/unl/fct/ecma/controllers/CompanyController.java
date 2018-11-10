package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.CompaniesApi;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.models.Company;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.models.EmployeeWithPw;
import pt.unl.fct.ecma.security.annotations.IsAdminOfCompany;
import pt.unl.fct.ecma.security.annotations.isSuperAdminOrAdmin;
import pt.unl.fct.ecma.services.CompanyService;

@RestController
public class CompanyController implements CompaniesApi {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) { //companyService should be static
        this.companyService = companyService;
    }

    @isSuperAdminOrAdmin
    //hasRole(ADMIN) && verificar se é admin da empresa do novo admin
    @Override
    public void addAdmin(EmployeeWithPw employee, Long companyId) {
        companyService.addAdmin(employee, companyId);
    }

    //@IsSuperAdmin
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void addCompany(Company company) {

        if (company.getId() != null)
            throw new BadRequestException("Can not define id of new company");

        companyService.addCompany(company);
    }

    @IsAdminOfCompany
    //admin daquela empresa
    @Override
    public void addEmployee(EmployeeWithPw employee, Long companyId) {
        companyService.addEmployee(employee, companyId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //hasRole(ADMIN)
    @Override
    public void deleteAdmin(Long companyId, Long adminId) {
        companyService.deleteAdmin(companyId, adminId);
    }

    @isSuperAdminOrAdmin
    //hasRole(ADMIN) e admin da empresa
    @Override
    public void deleteCompany(Long companyId) {
        companyService.deleteCompany(companyId);
    }

    @IsAdminOfCompany
    //admin daquela empresa
    @Override
    public void fireEmployee(Long companyId, Long employeeId) {
        companyService.deleteEmployee(companyId, employeeId);
    }

    @Override
    public Page<Employee> getAdminsOfCompany(Pageable pageable, Long companyId) {
        return companyService.getAdminsOfCompany(companyId, pageable);
    }

    @Override
    public Page<Company> getCompanies(Pageable pageable, String search) {
        if (search == null)
            return companyService.getAllCompanies(pageable);
        else
            return companyService.getCompaniesByName(pageable, search);
    }

    @Override
    public Company getCompany(Long companyId) {
        return companyService.getCompany(companyId);
    }

    @Override
    public Page<Employee> getEmployeesOfCompany(Pageable pageable, Long companyId, String search) {
        if (search == null)
            return companyService.getAllEmployeesOfCompany(pageable, companyId);
        else
            return companyService.getAllEmployeesOfCompanyByName(pageable, companyId, search);
    }

    @IsAdminOfCompany
    //admin da empresa
    @Override
    public void updateCompany(Company company, Long companyId) {

        if (!company.getId().equals(companyId))
            throw new BadRequestException("Ids of company do not match");

        companyService.updateCompany(company);
    }
}
