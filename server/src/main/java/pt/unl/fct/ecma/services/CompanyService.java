package pt.unl.fct.ecma.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.models.Company;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.errors.NotFoundException;
import pt.unl.fct.ecma.models.EmployeeWithPw;
import pt.unl.fct.ecma.repositories.CompanyRepository;
import pt.unl.fct.ecma.repositories.EmployeeRepository;

import java.util.Optional;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public void addAdmin(EmployeeWithPw employee, Long companyId) {

        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            Company realCompany = company.get();
            Employee dbemp = new Employee();
            dbemp.setJob(employee.getJob());
            dbemp.setName(employee.getName());
            dbemp.setEmail(employee.getEmail());
            dbemp.setPassword(employee.getPassword());
            dbemp.setUsername(employee.getUsername());
            realCompany.getEmployees().add(dbemp);
            dbemp.setAdmin(true);
            dbemp.setCompany(realCompany);
            companyRepository.save(realCompany);
        } else throw new NotFoundException(String.format("Company with id %d does not exist", companyId));
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public void addEmployee(EmployeeWithPw employee, Long companyId) {

        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            Company realCompany = company.get();
            Employee dbemp = new Employee();
            dbemp.setJob(employee.getJob());
            dbemp.setName(employee.getName());
            dbemp.setEmail(employee.getEmail());
            dbemp.setPassword(employee.getPassword());
            dbemp.setUsername(employee.getUsername());
            realCompany.getEmployees().add(dbemp);
            dbemp.setCompany(realCompany);
            companyRepository.save(realCompany);
        } else throw new NotFoundException(String.format("Company with id %d does not exist", companyId));
    }

    public void deleteAdmin(Long companyId, Long adminId) {
        Optional<Employee> employee = employeeRepository.findById(adminId);
        if (employee.isPresent()) {
            Employee realEmpoyee = employee.get();
            if (realEmpoyee.isAdmin() && realEmpoyee.getCompany().getId().equals(companyId)) {
                employeeRepository.delete(realEmpoyee);
            } else throw new BadRequestException("User is not admin of this company");
        } else throw new NotFoundException(String.format("Employee with id %d does not exist", companyId));
    }

    public void deleteCompany(Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            Company realCompany = company.get();
            companyRepository.delete(realCompany);
        } else throw new NotFoundException(String.format("Company with id %d does not exist", companyId));
    }

    public void deleteEmployee(Long companyId, Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            Employee realEmpoyee = employee.get();
            if (realEmpoyee.getCompany().getId().equals(companyId)) {

                employeeRepository.delete(realEmpoyee);
            } else throw new BadRequestException("User does not belong to this company");
        } else throw new NotFoundException(String.format("Employee with id %d does not exist", companyId));
    }

    public Page<Employee> getAdminsOfCompany(Long companyId, Pageable pageable) {
        return companyRepository.getAdminsOfCompany(companyId, pageable);
    }

    public Page<Company> getAllCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public Page<Company> getCompaniesByName(Pageable pageable, String name) {
        return companyRepository.findByName(name, pageable);
    }

    public Company getCompany(Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            return company.get();
        } else throw new NotFoundException(String.format("Company with id %d does not exist", companyId));
    }


    public Page<Employee> getAllEmployeesOfCompany(Pageable pageable, Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            return companyRepository.getAllEmployees(companyId, pageable);
        } else throw new NotFoundException(String.format("Company with id %d does not exist", companyId));
    }

    public Page<Employee> getAllEmployeesOfCompanyByName(Pageable pageable, Long companyId, String name) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            return companyRepository.getEmployeesByName(name, companyId, pageable);
        } else throw new NotFoundException(String.format("Company with id %d does not exist", companyId));
    }

    public void updateCompany(Company companyToBeUpdated) {
        Optional<Company> company = companyRepository.findById(companyToBeUpdated.getId());
        if (company.isPresent()) {
            Company realcompany = company.get();
            realcompany.setAddress(companyToBeUpdated.getAddress());
            realcompany.setEmail(companyToBeUpdated.getEmail());
            realcompany.setName(companyToBeUpdated.getName());
            companyRepository.save(realcompany);
        } else
            throw new NotFoundException(String.format("Company with id %d does not exist", companyToBeUpdated.getId()));
    }
}
