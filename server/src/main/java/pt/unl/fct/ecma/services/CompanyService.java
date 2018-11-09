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

    public void addAdmin(EmployeeWithPw employee, Long id) {

        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            Company realCompany = company.get();
            Employee dbemp= new Employee();
            dbemp.setJob(employee.getJob());
            dbemp.setName(employee.getName());
            dbemp.setEmail(employee.getEmail());
            dbemp.setPassword(employee.getPassword());
            dbemp.setUsername(employee.getUsername());
            realCompany.getEmployees().add(dbemp);
            dbemp.setAdmin(true);
            dbemp.setCompany(realCompany);
            companyRepository.save(realCompany);
        } else throw new NotFoundException(String.format("Company with id %d does not exist", id));
    }

    public void addCompany(Company company) {
        if (company.getId() == null) {
            companyRepository.save(company);
        } else throw new BadRequestException("Invalid input");
    }

    public void addEmployee(EmployeeWithPw employee, Long id) {

        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            Company realCompany = company.get();
            Employee dbemp= new Employee();
            dbemp.setJob(employee.getJob());
            dbemp.setName(employee.getName());
            dbemp.setEmail(employee.getEmail());
            dbemp.setPassword(employee.getPassword());
            dbemp.setUsername(employee.getUsername());
            realCompany.getEmployees().add(dbemp);
            dbemp.setCompany(realCompany);
            companyRepository.save(realCompany);
        } else throw new NotFoundException(String.format("Company with id %d does not exist", id));
    }

    public void deleteAdmin(Long id, Long adminId) {
        Optional<Employee> employee = employeeRepository.findById(adminId);
        if (employee.isPresent()) {
            Employee realEmpoyee = employee.get();
            if (realEmpoyee.isAdmin() && realEmpoyee.getCompany().getId() == id) {
                employeeRepository.delete(realEmpoyee);
            } else throw new BadRequestException("O utilizador não é admin da empresa");
        } else throw new NotFoundException(String.format("Employee with id %d does not exist", id));
    }

    public void deleteCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            Company realCompany = company.get();
            companyRepository.delete(realCompany);
        } else throw new NotFoundException(String.format("Company with id %d does not exist", id));
    }

    public void deleteEmployee(Long id, Long employeeid) {
            Optional<Employee> employee = employeeRepository.findById(employeeid);
        if (employee.isPresent()) {
            Employee realEmpoyee = employee.get();
            if (realEmpoyee.getCompany().getId() == id) {

                employeeRepository.delete(realEmpoyee);
            } else throw new BadRequestException("O utilizador não pertence á empresa");
        } else throw new NotFoundException(String.format("Employee with id %d does not exist", id));
    }

    public Page<Employee> getAdminsOfCompany(Long id, Pageable pageable) {
        return companyRepository.getAdminsOfCompany(id, pageable);
    }

    public Page<Company> getAllCompanies(Pageable pageable, String search) {
        if (search == null) {
            return companyRepository.findAll(pageable);
        } else {
            return companyRepository.findByName(search, pageable);
        }
    }

    public Company getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return company.get();
        } else throw new NotFoundException(String.format("Company with id %d does not exist", id));
    }

    public Page<Employee> getEmployeesOfCompany(Pageable pageable, Long id, String search) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {

            if (search == null) {
                return companyRepository.getAllEmployees(id, pageable);
            } else return companyRepository.getEmployeesByName(search, id, pageable);
        } else throw new NotFoundException(String.format("Company with id %d does not exist", id));
    }

    public void updateCompany(Company companyToBeUpdated, Long id) {
        Optional<Company> company = companyRepository.findById(companyToBeUpdated.getId());
        if (company.isPresent()) {
            Company realcompany = company.get();
            if (realcompany.getId() == id) {
                realcompany.setAddress(companyToBeUpdated.getAddress());
                realcompany.setEmail(companyToBeUpdated.getEmail());
                realcompany.setName(companyToBeUpdated.getName());
                companyRepository.save(realcompany);
            } else throw new BadRequestException("O id é invalido");
        } else throw new NotFoundException(String.format("Company with id %d does not exist", id));
    }
}
