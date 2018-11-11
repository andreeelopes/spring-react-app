package pt.unl.fct.ecma;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import pt.unl.fct.ecma.models.*;
import pt.unl.fct.ecma.repositories.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

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


    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Before
    public void init() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();

        // Data Fixture
        companyRepository.deleteAll();
        employeeRepository.deleteAll();
        proposalRepository.deleteAll();
        bidRepository.deleteAll();

        Company company1 = new Company();
        company1.setName("Company1");
        company1.setEmail("company1@mail.com");
        company1.setAddress("Lisboa");

        Company company2 = new Company();
        company2.setName("Company2");
        company2.setEmail("company2@mail.com");
        company2.setAddress("Porto");

        company1 = companyRepository.save(company1);
        company2 = companyRepository.save(company2);

        Employee employee1 = new Employee("test1", "test1",
                "test1@gmail.com", "Tile Painter", true,
                new BCryptPasswordEncoder().encode("password"));

        Employee employee2 = new Employee("test2", "test2",
                "test2@gmail.com", "Nails Painter", false,
                new BCryptPasswordEncoder().encode("password"));

        employee1.setCompany(company1);
        employee2.setCompany(company2);

        employee1 = employeeRepository.save(employee1);
        employee2 = employeeRepository.save(employee2);

        //first proposal
        Proposal proposal1 = new Proposal();
        proposal1.setCompanyProposed(company1);
        proposal1.setApprover(employee1);
        proposal1.setStatus(Proposal.Status.APPROVED.toString());
        proposal1.setTargetCompany(company2);

        //second proposal
        Proposal proposal2 = new Proposal();
        proposal2.setCompanyProposed(company2);
        proposal2.setApprover(employee2);
        proposal2.setStatus(Proposal.Status.APPROVED.toString());
        proposal2.setTargetCompany(company1);

        proposal1 = proposalRepository.save(proposal1);
        proposal2 = proposalRepository.save(proposal2);

        //Bid 1 for proposal 1
        Bid bid1 = new Bid();
        BidKey bid1Key = new BidKey();
        bid1Key.setProposal(proposal1);
        bid1Key.setBidder(employee2);
        bid1.setPk(bid1Key);
        bid1.setStatus(Bid.Status.ACCEPTED.toString());

        //Bid 2 for proposal 2
        Bid bid2 = new Bid();
        BidKey bid2Key = new BidKey();
        bid2Key.setProposal(proposal2);
        bid2Key.setBidder(employee1);
        bid2.setPk(bid2Key);
        bid2.setStatus(Bid.Status.ACCEPTED.toString());

        bidRepository.save(bid1);
        bidRepository.save(bid2);



        ProposalRole employee1role1 = new ProposalRole();
        employee1role1.setProposal(proposal1);
        employee1role1.setEmployee(employee1);
        employee1role1.setRole("STAFF");
        List<ProposalRole> a = employee1.getRolesOnProposal();
        a.add(employee1role1);
        proposal1.getTeam().add(employee1role1);
        proposal1 = proposalRepository.save(proposal1);



        /*
        prop.setCompanyProposed(company1);

+        prop.setApprover(employee1);

+        prop.getTeam().add(role);

+        prop.setStatus(Proposal.Status.PLACED);

+        prop.setTargetCompany(company2);
         */

//        ProposalRole employee1role2 = new ProposalRole(proposal2, employee1, "PARTNER");
//
//        ProposalRole employee2role1 = new ProposalRole(proposal2, employee2, "STAFF");
//        ProposalRole employee2role2 = new ProposalRole(proposal1, employee2, "PARTNER");
//
//        employee1.getRolesOnProposal().add(employee1role1);
//        employee1.getRolesOnProposal().add(employee1role2);
//        employee2.getRolesOnProposal().add(employee2role1);
//        employee2.getRolesOnProposal().add(employee2role2);
//
//        employeeRepository.save(employee2);


    }

    @Test
    public void testGetEmployee() throws Exception {
        long id = 1;
        Employee testEmployee = employeeRepository.findById(id).get();
        Employee requestedEmployee = requestGetEmployee(id);

        assertEquals(requestedEmployee.getUsername(), testEmployee.getUsername());
    }

    @Test
    public void testGetEmployees() throws Exception {

        Employee employee1 = employeeRepository.findById(1L).get();
        Employee employee2 = employeeRepository.findById(2L).get();

        List<Employee> requestedEmployees = requestGetEmployees();

        assertEquals(requestedEmployees.size(), 2);
        assertTrue(requestedEmployees.stream()
                .anyMatch((p) -> p.getUsername().equals(employee1.getUsername())));
        assertTrue(requestedEmployees.stream()
                .anyMatch((p) -> p.getUsername().equals(employee2.getUsername())));
    }

    @Test
    public void testUpdateEmployee() throws Exception {

        long employeeId = 1L;

        Employee employee = employeeRepository.findById(employeeId).get();
        String previousName = employee.getName();
        String newName = "David Gilmour";
        employee.setName(newName);

        authenticateUser("test1", "password");
        requestUpdateEmployee(employeeId, employee);
        Employee updatedEmployee = requestGetEmployee(employeeId);

        assertNotEquals(updatedEmployee.getName(), previousName);
        assertEquals(updatedEmployee.getName(), newName);
    }

    @Test
    public void testGetEmployeeBids() throws Exception {

        long employeeId = 1L;

        authenticateUser("test1", "password");
        List<Bid> bidsOfEmployee = requestGetEmployeeBids(employeeId);

        assertEquals(bidsOfEmployee.size(), 1);
    }


    @Test
    public void testGetProposalPartner() throws Exception {

        long employeeId = 1L;

        authenticateUser("test1", "password");
        List<Proposal> partnerProposalsOfEmployee = requestGetProposalPartner(employeeId);

        assertEquals(partnerProposalsOfEmployee.size(), 1);
    }

    @Test
    public void testGetProposalStaff() throws Exception {

        long employeeId = 1L;

        authenticateUser("test1", "password");
        List<Proposal> staffProposalsOfEmployee = requestGetStaffPartner(employeeId);
        assertEquals(staffProposalsOfEmployee.size(), 1);
    }


    /**
     * Performs a GET request for a single employee with id equals too employeeId
     *
     * @param employeeId
     * @return
     * @throws Exception
     */
    private Employee requestGetEmployee(long employeeId) throws Exception {
        final MvcResult result = this.mockMvc.perform(get("/employees/" + employeeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String json = result.getResponse().getContentAsString();

        return objectMapper.readValue(json, Employee.class);
    }

    private List<Employee> requestGetEmployees() throws Exception {

        final MvcResult result = this.mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String jsonList = result.getResponse().getContentAsString();

        JavaType type = objectMapper.getTypeFactory().
                constructCollectionType(List.class, Employee.class);

        return toList(jsonList, type);
    }


    private void requestUpdateEmployee(long employeeId, Employee updatedEmployee) throws Exception {
        this.mockMvc.perform(put("/employees/" + employeeId)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk());
    }

    private void authenticateUser(String username, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(username, "password");
        SecurityContext securityContext = SecurityContextHolder.getContext();

        securityContext.setAuthentication(auth);
    }

    private List<Bid> requestGetEmployeeBids(long employeeId) throws Exception {
        final MvcResult result = this.mockMvc.perform(get("/employees/" + employeeId + "/bids"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String bidsJson = result.getResponse().getContentAsString();

        JavaType type = objectMapper.getTypeFactory().
                constructCollectionType(List.class, Bid.class);

        return toList(bidsJson, type);
    }

    private List<Proposal> requestGetStaffPartner(long employeeId) throws Exception {

        final MvcResult result = this.mockMvc.perform(get("/employees/" + employeeId + "/partnerproposals"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String proposalsJson = result.getResponse().getContentAsString();

        JavaType type = objectMapper.getTypeFactory().
                constructCollectionType(List.class, Proposal.class);

        return toList(proposalsJson, type);
    }

    private List<Proposal> requestGetProposalPartner(long employeeId) throws Exception {

        final MvcResult result = this.mockMvc.perform(get("/employees/" + employeeId + "/staffproposals"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String proposalsJson = result.getResponse().getContentAsString();

        JavaType type = objectMapper.getTypeFactory().
                constructCollectionType(List.class, Proposal.class);

        return toList(proposalsJson, type);
    }

    private <T> List<T> toList(String jsonArray, JavaType type) throws IOException {

        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(jsonArray);
        JsonObject array = element.getAsJsonObject();
        String content = array.get("content").toString();

        return objectMapper.readValue(content, type);
    }

}




