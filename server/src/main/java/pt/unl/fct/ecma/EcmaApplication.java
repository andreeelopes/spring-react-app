package pt.unl.fct.ecma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pt.unl.fct.ecma.Entity.Bid;
import pt.unl.fct.ecma.Entity.Employee;
import pt.unl.fct.ecma.Entity.Proposal;
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

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Employee emp=new Employee();
        emp.setEmail("simon@gmail.com");
        emp.setJob("Informatico");
        emp.setName("Simon");

        for(int i=0;i<30;i++) {
            Proposal prop = new Proposal();
            prop.setStatus(Proposal.Status.APPROVED);
            Bid bid = new Bid();
            bid.setEmployee(emp);
            bid.setProposal(prop);
            bid.setStatus(Bid.Status.WAITING.toString());

            emp.getBiddingProposals().add(bid);

            proposalRepository.save(prop);
        }
        
        employeeRepository.save(emp);
    }
}
