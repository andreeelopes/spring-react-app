package pt.unl.fct.ecma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pt.unl.fct.ecma.models.*;
import pt.unl.fct.ecma.repositories.CommentRepository;
import pt.unl.fct.ecma.repositories.CompanyRepository;
import pt.unl.fct.ecma.repositories.EmployeeRepository;
import pt.unl.fct.ecma.repositories.ProposalRepository;

import javax.transaction.Transactional;

@SpringBootApplication
public class EcmaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EcmaApplication.class, args);
    }

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProposalRepository proposalRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {


        //Company 1

        Company company1 = new Company();
        company1.setAddress("rua idk");
        company1.setEmail("company1@");
        company1.setName("company1");

        Employee admin1 = new Employee();
        admin1.setEmail("admin1@");
        admin1.setJob("admin");
        admin1.setName("Admin Company 1");
        admin1.setUsername("admin1");
        admin1.setPassword("password");
        admin1.setAdmin(true);
        admin1.setCompany(company1);

        Employee employee11 = new Employee();
        employee11.setEmail("employee11@");
        employee11.setJob("normal");
        employee11.setName("Employee 1 Company 1");
        employee11.setUsername("employee11");
        employee11.setPassword("password");
        employee11.setAdmin(false);
        employee11.setCompany(company1);

        Employee employee12 = new Employee();
        employee12.setEmail("employee12@");
        employee12.setJob("normal");
        employee12.setName("Employee 2 Company 1");
        employee12.setUsername("employee12");
        employee12.setPassword("password");
        employee12.setAdmin(false);
        employee12.setCompany(company1);

        //Company 2

        Company company2 = new Company();
        company2.setAddress("rua idk");
        company2.setEmail("company2@");
        company2.setName("company2");


        Employee admin2 = new Employee();
        admin2.setEmail("admin2@");
        admin2.setJob("admin");
        admin2.setName("Admin Company 2");
        admin2.setUsername("admin2");
        admin2.setPassword("password");
        admin2.setAdmin(true);
        admin2.setCompany(company2);

        Employee employee21 = new Employee();
        employee21.setEmail("employee21@");
        employee21.setJob("normal");
        employee21.setName("Employee 1 Company 2");
        employee21.setUsername("employee21");
        employee21.setPassword("password");
        employee21.setAdmin(false);
        employee21.setCompany(company2);

        Employee employee22 = new Employee();
        employee22.setEmail("employee22@");
        employee22.setJob("normal");
        employee22.setName("Employee 2 Company 2");
        employee22.setUsername("employee22");
        employee22.setPassword("password");
        employee22.setAdmin(false);
        employee22.setCompany(company2);

        //Company 3

        Company company3 = new Company();
        company3.setAddress("rua idk");
        company3.setEmail("company3@");
        company3.setName("company3");


        Employee admin3 = new Employee();
        admin3.setEmail("admin3@");
        admin3.setJob("admin");
        admin3.setName("Admin Company 3");
        admin3.setUsername("admin3");
        admin3.setPassword("password");
        admin3.setAdmin(true);
        admin3.setCompany(company3);

        Employee employee31 = new Employee();
        employee31.setEmail("employee31@");
        employee31.setJob("normal");
        employee31.setName("Employee 1 Company 3");
        employee31.setUsername("employee31");
        employee31.setPassword("password");
        employee31.setAdmin(false);
        employee31.setCompany(company3);

        Employee employee32 = new Employee();
        employee32.setEmail("employee32@");
        employee32.setJob("normal");
        employee32.setName("Employee 2 Company 3");
        employee32.setUsername("employee32");
        employee32.setPassword("password");
        employee32.setAdmin(false);
        employee32.setCompany(company3);

        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);

        employeeRepository.save(admin1);
        employeeRepository.save(admin2);
        employeeRepository.save(admin3);
        employeeRepository.save(employee11);
        employeeRepository.save(employee12);
        employeeRepository.save(employee21);
        employeeRepository.save(employee22);
        employeeRepository.save(employee31);
        employeeRepository.save(employee32);


    }
}
