package pt.unl.fct.ecma.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pt.unl.fct.ecma.Entity.Company;
import pt.unl.fct.ecma.Entity.Employee;
import pt.unl.fct.ecma.Entity.Proposal;
import pt.unl.fct.ecma.Entity.ProposalRole;
import pt.unl.fct.ecma.Errors.BadRequestException;
import pt.unl.fct.ecma.Errors.NotFoundException;
import pt.unl.fct.ecma.repositories.CompanyRepository;
import pt.unl.fct.ecma.repositories.EmployeeRepository;

import java.util.Optional;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;
    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository){
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public void addAdmin( Employee employee,  Long id) {

        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()){
            Company realCompany = company.get();
            realCompany.getMembers().add(employee);
            employee.setAdmin(true);
            employee.setCompany(realCompany);
            companyRepository.save(realCompany);
        }
        else throw new NotFoundException(String.format("Company with id %d does not exist", id));
    }

    public void addCompany(Company company) {
        if(company.getId()!=null) {
            companyRepository.save(company);
        }
        else throw new BadRequestException("Invalid input");
    }

    public void addEmployee(Employee employee, Long id) {

        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()){
            Company realCompany = company.get();
            realCompany.getMembers().add(employee);
            employee.setCompany(realCompany);
            companyRepository.save(realCompany);
        }
        else throw new NotFoundException(String.format("Company with id %d does not exist", id));
    }

    public void deleteAdmin(Long id, Long adminId) {
        Optional<Employee> employee = employeeRepository.findById(adminId);
        if(employee.isPresent()){
            Employee realEmpoyee = employee.get();
            if(realEmpoyee.isAdmin() && realEmpoyee.getCompany().getId()==adminId){

                employeeRepository.delete(realEmpoyee);
            }
            else throw new BadRequestException("O utilizador não é admin da empresa");
        }
        else throw new NotFoundException(String.format("Employee with id %d does not exist", id));
    }

    public void deleteCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()) {
            Company realCompany = company.get();
            companyRepository.delete(realCompany);
        }
        else throw new NotFoundException(String.format("Company with id %d does not exist", id));
    }
}
