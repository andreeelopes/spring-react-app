package pt.unl.fct.ecma;

import com.fasterxml.jackson.core.type.TypeReference;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pt.unl.fct.ecma.models.Company;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.repositories.CompanyRepository;
import pt.unl.fct.ecma.repositories.EmployeeRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
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

        Company company1 = new Company();
        company1.setName("Company1");
        company1.setEmail("company1@mail.com");
        company1.setAddress("Lisboa");

        Employee employee1 = new Employee("test", "test",
                "test@gmail.com", "Tile Painter", true,
                new BCryptPasswordEncoder().encode("password"));

        Employee employee2 = new Employee("JaneDoeUserName", "Jane",
                "janedoe@gmail.com", "Nails Painter", false,
                new BCryptPasswordEncoder().encode("password"));

        employee1.setCompany(company1);
        employee2.setCompany(company1);

        companyRepository.save(company1);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

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

        int expectedSize = 2;

        Employee employee1 = employeeRepository.findById(1L).get();
        Employee employee2 = employeeRepository.findById(2L).get();


        List<Employee> requestedEmployees =  requestGetEmployees(expectedSize);

        assertEquals(requestedEmployees.size(), expectedSize);
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

        authenticateUser("test", "test");
        requestUpdateEmployee(employeeId, employee);
        Employee updatedEmployee = requestGetEmployee(employeeId);

        assertNotEquals(updatedEmployee.getName(), previousName);
        assertEquals(updatedEmployee.getName(), newName);
    }

    @Test
    public void testGetEmployeeBids() throws Exception {

    }

    @Test
    public void testGetProposalPartner() throws Exception {

    }

    @Test
    public void testGetProposalStaff() throws Exception {

    }


    /**
     * Performs a GET request for a single employee with id equals too employeeId
     * @param employeeId
     * @return
     * @throws Exception
     */
    private Employee requestGetEmployee(long employeeId) throws Exception {
        final MvcResult result = this.mockMvc.perform(get("/employees/" +employeeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String json = result.getResponse().getContentAsString();

        return objectMapper.readValue(json, Employee.class);
    }

    private List<Employee> requestGetEmployees(int exceptedSize) throws Exception {

        final MvcResult result = this.mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String list = result.getResponse().getContentAsString();

        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(list);
        JsonObject array=element.getAsJsonObject();
        String content=array.get("content").toString();

        return objectMapper.readValue(content,new TypeReference<List<Employee>>(){});
    }


    private void requestUpdateEmployee(long employeeId, Employee updatedEmployee) throws Exception {
        this.mockMvc.perform(formLogin("/employees/"+ employeeId).user("test").password("password"));
        this.mockMvc.perform(put("/employees/"+ employeeId)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk());
    }

    private void authenticateUser(String username, String password){
        Authentication auth = new UsernamePasswordAuthenticationToken(username, "password");
        SecurityContext securityContext = SecurityContextHolder.getContext();

        securityContext.setAuthentication(auth);
    }

}




