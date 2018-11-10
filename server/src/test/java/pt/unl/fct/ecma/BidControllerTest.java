package pt.unl.fct.ecma;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pt.unl.fct.ecma.models.Bid;
import pt.unl.fct.ecma.models.Company;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.models.Proposal;
import pt.unl.fct.ecma.repositories.CompanyRepository;
import pt.unl.fct.ecma.repositories.EmployeeRepository;
import pt.unl.fct.ecma.repositories.ProposalRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidControllerTest {


        @Autowired
        private WebApplicationContext wac;

        @Autowired
        private CompanyRepository companyRepository;

        @Autowired
        private EmployeeRepository employeeRepository;

        @Autowired
        private ProposalRepository proposalRepository;

        private MockMvc mockMvc;

        @Before
        public void init() {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

            // Data Fixture
            companyRepository.deleteAll();
            employeeRepository.deleteAll();
/*
            Bid bid = new Bid();
            bid.setBidder(emp);
            bid.setProposal(prop);
            bid.setStatus(Bid.Status.WAITING.toString());
*/
            Company company= new Company();
            company.setName("MountainDew");
            company.setEmail("mountaindew@outlook");
            company.setAddress("Lisboa");


            Employee emp = new Employee();
            emp.setEmail("simon@gmail.com");
            emp.setJob("Informatico");
            emp.setName("Simon");
            emp.setUsername("simon");
            emp.setPassword(new BCryptPasswordEncoder().encode("simon"));
            emp.setAdmin(true);

            companyRepository.save(company);
            employeeRepository.save(emp);

            Proposal prop = new Proposal();
            prop.setCompanyProposed(company);
            prop.setApprover(emp);
            prop.setStatus(Proposal.Status.APPROVED);
            proposalRepository.save(prop);
        }

}