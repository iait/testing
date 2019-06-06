package com.iait.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iait.testing.TestingApplication;
import com.iait.testing.entities.EmployeeEntity;
import com.iait.testing.repositories.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class EmployeeCtrlIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ObjectMapper om;

    @Test
    public void whenValidInput_thenCreateEmployee() throws IOException, Exception {
        EmployeeEntity bob = new EmployeeEntity("bob");
        mvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsBytes(bob)));

        List<EmployeeEntity> found = repository.findAll();
        assertThat(found).extracting(EmployeeEntity::getName).containsOnly("bob");
    }

    @Test
    public void givenAnEmployee_whenGetEmployees_thenStatus200() throws Exception {
        createTestEmployee("alex");

        mvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name", is("alex")));
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
        createTestEmployee("bob");
        createTestEmployee("alex");

        mvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
            .andExpect(jsonPath("$[0].name", is("bob")))
            .andExpect(jsonPath("$[1].name", is("alex")));
    }

    private void createTestEmployee(String name) {
        EmployeeEntity emp = new EmployeeEntity(name);
        repository.saveAndFlush(emp);
    }

    @After
    public void resetDb() {
        repository.deleteAll();
    }

}
