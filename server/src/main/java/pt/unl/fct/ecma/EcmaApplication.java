package pt.unl.fct.ecma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pt.unl.fct.ecma.models.*;
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



    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Employee emp = new Employee();
        emp.setEmail("simon@gmail.com");
        emp.setJob("Informatico");
        emp.setName("Simon");
        emp.setAdmin(true);
        Company company = new Company();
        company.setAddress("rua idk");
        company.setEmail("ecma@");

        emp.setCompany(company);

        companyRepository.save(company);

        for (int i = 0; i < 30; i++) {
            Proposal prop = new Proposal();
            prop.setStatus(Proposal.Status.APPROVED);
            Bid bid = new Bid();
            bid.setBidder(emp);
            bid.setProposal(prop);
            bid.setStatus(Bid.Status.WAITING.toString());

            Review review= new Review();
            review.setAuthor(emp);
            review.setProposal(prop);

            ProposalRole role = new ProposalRole();
            role.setEmployee(emp);
            role.setProposal(prop);
            role.setRole("PARTNER");

            emp.getRolesOnProposal().add(role);
            emp.getBiddedProposals().add(bid);
            emp.getReviews().add(review);
            proposalRepository.save(prop);
        }


        employeeRepository.save(emp);
    }
}
