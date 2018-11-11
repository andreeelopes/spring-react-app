package pt.unl.fct.ecma;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pt.unl.fct.ecma.models.*;
import pt.unl.fct.ecma.repositories.*;

import javax.transaction.Transactional;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class ProposalControllerTest {


    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    @Transactional
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        proposalRepository.deleteAll();

        Company company= new Company();
        company.setName("MountainDew");
        company.setEmail("mountaindew@outlook");
        company.setAddress("Lisboa");

        companyRepository.save(company);


        Employee emp = new Employee();
        emp.setEmail("simon@gmail.com");
        emp.setJob("Informatico");
        emp.setName("Simon");
        emp.setUsername("simon");
        emp.setPassword(new BCryptPasswordEncoder().encode("simon"));
        emp.setAdmin(true);
        emp.setCompany(company);

        employeeRepository.save(emp);

        Employee emp2 = new Employee();
        emp2.setEmail("andre@gmail.com");
        emp2.setJob("Canalizador");
        emp2.setName("Andre");
        emp2.setUsername("andre");
        emp2.setPassword(new BCryptPasswordEncoder().encode("andre"));
        emp2.setAdmin(false);
        emp2.setCompany(company);

        employeeRepository.save(emp2);

        Proposal prop = new Proposal();
        prop.setCompanyProposed(company);
        prop.setApprover(emp);
        prop.setStatus(Proposal.Status.APPROVED);
        prop.setTargetCompany(company);
        ProposalRole role = new ProposalRole();
        ProposalRoleKey proposalRoleKey = new ProposalRoleKey();
        proposalRoleKey.setEmployee(emp2);
        proposalRoleKey.setProposal(prop);

        role.setPk(proposalRoleKey);
        role.setRole(ProposalRole.Role.STAFF.toString());
        prop.getTeam().add(role);
        proposalRepository.save(prop);
    }

    @Test
    public void testGetProposal() throws Exception{
        Proposal dbProposal = proposalRepository.findById(1L).get();
        Proposal testProposal = requestGetProposal(1L);

        assertEquals(dbProposal.getId(), testProposal.getId());
        assertEquals(dbProposal.getApprover().getId(), testProposal.getApprover().getId());
    }
    private Proposal requestGetProposal(long proposalid) throws Exception {
        LoginWithSimon();
        final MvcResult result = this.mockMvc.perform(get("/proposals/" + proposalid))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String json = result.getResponse().getContentAsString();

        return objectMapper.readValue(json, Proposal.class);
    }

    private void LoginWithSimon() {
        Authentication auth = new UsernamePasswordAuthenticationToken("simon", "simon");
        SecurityContext securityContext = SecurityContextHolder.getContext();

        securityContext.setAuthentication(auth);
    }

    private Company getCompany() {
        Iterator<Company> itCompany =companyRepository.findAll().iterator();
        return itCompany.next();
    }
    private Employee getEmployee() {
        Iterator<Employee> itEmployee =employeeRepository.findAll().iterator();
        return itEmployee.next();
    }
    private Proposal getProposal() {
        Iterator<Proposal> itProposal =proposalRepository.findAll().iterator();
        return itProposal.next();
    }

    @Test
    public void testAddProposal() throws Exception{

        Proposal prop = new Proposal();
        prop.setCompanyProposed(getCompany());
        prop.setApprover(getEmployee());
        prop.setStatus(Proposal.Status.APPROVED);
        prop.setTargetCompany(getCompany());

        String json = objectMapper.writeValueAsString(prop);
        this.mockMvc.perform(post("/proposals/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isOk());
        long size =proposalRepository.count();
        assertEquals(size, 2L);
    }
    @Test
    public void testAddMember() throws Exception{
        Authentication auth = new UsernamePasswordAuthenticationToken("simon", "simon");
        SecurityContext securityContext = SecurityContextHolder.getContext();

        securityContext.setAuthentication(auth);

        Employee emp = employeeRepository.findById(2L).get();

        String json = objectMapper.writeValueAsString(emp);
        this.mockMvc.perform(post("/proposals/"+getProposal().getId()+"/partnermembers/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isOk());

    }
}