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
        Employee emp = new Employee();
        emp.setEmail("simon@gmail.com");
        emp.setJob("Informatico");
        emp.setName("Simon");
        emp.setUsername("simon");
        emp.setPassword(new BCryptPasswordEncoder().encode("simon"));
        emp.setAdmin(true);

        Employee emp2 = new Employee();
        emp2.setEmail("andre@gmail.com");
        emp2.setJob("Canalizador");
        emp2.setName("andre");
        emp2.setUsername("andre");
        emp2.setPassword(new BCryptPasswordEncoder().encode("andre"));
        emp2.setAdmin(false);

        Company company = new Company();
        company.setAddress("rua idk");
        company.setEmail("ecma@");
        company.setName("atum");

        emp.setCompany(company);
        emp2.setCompany(company);

        companyRepository.save(company);
        employeeRepository.save(emp);
        employeeRepository.save(emp2);
        for (int i = 0; i < 30; i++) {
            Proposal prop = new Proposal();
            prop.setCompanyProposed(company);
            prop.setApprover(emp);
            prop.setStatus(Proposal.Status.APPROVED);
            Bid bid = new Bid();
            bid.setBidder(emp);
            bid.setProposal(prop);
            bid.setStatus(Bid.Status.WAITING.toString());

            Review review= new Review();
            review.setAuthor(emp);
            review.setProposal(prop);
            review.setScore(Review.Score.EXCELENT);
            review.setText("ola");

            Comment comment = new Comment();
            comment.setComment("Muito bom");

            ProposalRole role = new ProposalRole();
            role.setEmployee(emp);
            role.setProposal(prop);
            role.setRole("PARTNER");

            emp.getRolesOnProposal().add(role);
            emp.getBiddedProposals().add(bid);
            emp.getReviews().add(review);




            proposalRepository.save(prop);

        }


    }
}
