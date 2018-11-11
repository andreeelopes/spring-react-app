package pt.unl.fct.ecma;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pt.unl.fct.ecma.models.*;
import pt.unl.fct.ecma.repositories.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest
public class SectionControllerTest {

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
    private SectionRepository sectionRepository;

    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();

        Company company1 = new Company();
        company1.setName("Company1");
        company1.setEmail("company1@mail.com");
        company1.setAddress("Lisboa");

        Company company2 = new Company();
        company2.setName("Company2");
        company2.setEmail("company2@mail.com");
        company2.setAddress("Porto");

        Employee employee1 = new Employee("test1", "test1",
                "test1@gmail.com", "Tile Painter", true,
                new BCryptPasswordEncoder().encode("password"));

        Employee employee12 = new Employee("test12", "test12",
                "test12@gmail.com", "Tile Painter", false,
                new BCryptPasswordEncoder().encode("password"));

        Employee employee2 = new Employee("test2", "test2",
                "test2@gmail.com", "Nails Painter", false,
                new BCryptPasswordEncoder().encode("password"));

        employee1.setCompany(company1);
        employee12.setCompany(company1);
        employee2.setCompany(company2);

        companyRepository.save(company1);
        companyRepository.save(company2);

        employeeRepository.save(employee1);
        employeeRepository.save(employee12);
        employeeRepository.save(employee2);


        Proposal prop = new Proposal();

        ProposalRole role = new ProposalRole();
        ProposalRoleKey proposalRoleKey = new ProposalRoleKey();
        proposalRoleKey.setEmployee(employee12);
        proposalRoleKey.setProposal(prop);

        role.setPk(proposalRoleKey);
        role.setRole(ProposalRole.Role.STAFF.toString());

        prop.setCompanyProposed(company1);
        prop.setApprover(employee1);
        prop.getTeam().add(role);
        prop.setStatus(Proposal.Status.PLACED.toString());
        prop.setTargetCompany(company2);

        proposalRepository.save(prop);

        Section title = new Section();
        title.setText("Testing is really boring");
        title.setType(Section.Type.TITLE.toString());
        title.setProposal(prop);

        sectionRepository.save(title);
    }

    @Test
    public void testAddSection() throws Exception {

    }

    @Test
    public void testDeleteSection() throws Exception {

    }

    @Test
    public void testProposalSections() throws Exception {
        Proposal proposal = proposalRepository.findById(1L).get();
        Section title = sectionRepository.findById(1L).get();

        List<Section> requestedSections = requestProposalSections(1L);

        assertEquals(requestedSections.size(), 1);
        assertTrue(requestedSections.stream()
                .anyMatch((p) -> p.getText().equals(title.getText())));
        assertTrue(requestedSections.stream()
                .anyMatch((p) -> p.getType().equals(title.getType())));

    }


    private List<Section> requestProposalSections(Long proposalId) throws Exception {

        authenticateUser("test12", "password");

        final MvcResult result = this.mockMvc.perform(get("/proposals/" + proposalId + "/sections/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String json = result.getResponse().getContentAsString();

        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(json);
        JsonObject array = element.getAsJsonObject();
        String content = array.get("content").toString();

        return objectMapper.readValue(content, new TypeReference<List<Section>>() {
        });

    }


    @Test
    public void testUpdateSection() throws Exception {

    }


    private void authenticateUser(String username, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContext securityContext = SecurityContextHolder.getContext();

        securityContext.setAuthentication(auth);
    }

}
