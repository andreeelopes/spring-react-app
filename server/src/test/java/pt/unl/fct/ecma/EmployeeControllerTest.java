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
import static org.junit.Assert.assertTrue;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        Employee employeeAdmin = new Employee("JohnDoeUserName", "John",
                "johndoe@gmail.com", "Tile Painter", true,
                new BCryptPasswordEncoder().encode("password"));

        employeeAdmin.setCompany(company1);
        companyRepository.save(company1);
        employeeRepository.save(employeeAdmin);
    }

    @Test
    public void testGetEmployee() throws Exception {
        long id = 1;
        Employee testEmployee = employeeRepository.findById(id).get();
        Employee requestedEmployee = getTestEmployee(id);

        assertTrue(requestedEmployee.equals(testEmployee));

    }

    private Employee getTestEmployee(long employeeId) throws Exception {
        final MvcResult result = this.mockMvc.perform(get("/employees/" +employeeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(response);

//        ObjectMapper mapper = new ObjectMapper();
//
//        return mapper.readValue(content,new TypeReference<List<Company>>(){});


        return null;
    }

}




