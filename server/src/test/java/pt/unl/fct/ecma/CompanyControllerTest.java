package pt.unl.fct.ecma;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pt.unl.fct.ecma.models.Company;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.repositories.CompanyRepository;
import pt.unl.fct.ecma.repositories.EmployeeRepository;


import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        // Data Fixture
        companyRepository.deleteAll();
        employeeRepository.deleteAll();



        Company company2= new Company();
        company2.setName("MountainDew");
        company2.setEmail("mountaindew@outlook");
        company2.setAddress("Lisboa");


        Employee emp = new Employee();
        emp.setEmail("simon@gmail.com");
        emp.setJob("Informatico");
        emp.setName("Simon");
        emp.setUsername("simon");
        emp.setPassword(new BCryptPasswordEncoder().encode("simon"));
        emp.setAdmin(true);



        emp.setCompany(company2);
        companyRepository.save(company2);
        employeeRepository.save(emp);

    }

    private List<Company> getCompanies() throws Exception {

        final MvcResult result = this.mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andReturn()
        ;
        String list = result.getResponse().getContentAsString();

        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(list);
        JsonObject array=element.getAsJsonObject();
        String content=array.get("content").toString();


        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(content,new TypeReference<List<Company>>(){});
    }

    @Test
    public void testGetCompanies() throws Exception {

        List<Company> companies = getCompanies();
        assertTrue(companies.stream().anyMatch( (p) -> p.getName().equals("MountainDew")));
    }
    @Test
    public void testAddAdmin() throws Exception{

        Authentication auth = new UsernamePasswordAuthenticationToken("user", "password");
        SecurityContext securityContext = SecurityContextHolder.getContext();

        securityContext.setAuthentication(auth);
        List<Company> companies = getCompanies();
        Company company=companies.get(0);
        System.out.println(company.getName());
        this.mockMvc.perform(post("/companies/"+company.getId()+"/admins")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"id\":\"2\",\"name\":\"Andre\", \"job\":\"Canalizador\", \"username\":\"andre\" , \"email\":\"andre@\" , \"password\":\""+new BCryptPasswordEncoder().encode("andre")+"\"}"))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/companies/"+company.getId()+"/admins"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(2)))
        ;
    }
    @Test
    public void testGetAdmins() throws  Exception{
        List<Company> companies = getCompanies();
        Company company=companies.get(0);

        final MvcResult result = this.mockMvc.perform(get("/companies/"+company.getId()+"/admins"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andReturn()
        ;
        String list = result.getResponse().getContentAsString();

        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(list);
        JsonObject array=element.getAsJsonObject();
        String content=array.get("content").toString();

        ObjectMapper mapper = new ObjectMapper();
        List<Employee> admins=mapper.readValue(content,new TypeReference<List<Employee>>(){});

        assertTrue(admins.stream().anyMatch( (p) -> p.getName().equals("Simon")));
    }
    /*
            emp.setEmail("simon@gmail.com");
        emp.setJob("Informatico");
        emp.setName("Simon");
        emp.setUsername("simon");
 */




}
