package CRUD_API_Checkpoint.example.demo;

import CRUD_API_Checkpoint.example.demo.Model.User;
import CRUD_API_Checkpoint.example.demo.Repo.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {


    @Autowired
    MockMvc mvc;

    @Autowired
    UserRepo testUserRepo;
    ObjectMapper mapper = new ObjectMapper();
    User user1 = new User();
    User user2 = new User();
    @BeforeEach
    public void init(){
        user1.setEmail("john@example.com");
        user1.setPassword("something-secret");
        user2.setEmail("eliza@gmail.com");
        user2.setPassword("1234");
    }



    // Test Getting all the Users
    @Test
    @Transactional
    @Rollback
    public void testGetMapping() throws Exception {
        this.testUserRepo.save(user1);
        this.testUserRepo.save(user2);
        MockHttpServletRequestBuilder request = get("/users");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("john@example.com"))
                .andExpect(jsonPath("$[0].id").value(user1.getId()))
                .andExpect(jsonPath("$[1].email").value("eliza@gmail.com"));
    }

    //Test addding a new user
    @Test
    @Transactional
    @Rollback
    public void testAddingUser() throws  Exception
    {
        this.testUserRepo.save(user1);
        this.testUserRepo.save(user2);
        String json = mapper.writeValueAsString(user1);

        MockHttpServletRequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.id").value(user1.getId()));
            json = mapper.writeValueAsString(user2);
        MockHttpServletRequestBuilder requestTwo = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        this.mvc.perform(requestTwo)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("eliza@gmail.com"))
                .andExpect(jsonPath("$.id").value(user2.getId()));
    }

    //Get Single User Test

    @Test
    @Transactional
    @Rollback
    public void getSingleUser() throws Exception
    {
        this.testUserRepo.save(user1);
        MockHttpServletRequestBuilder requestTwo = get("/users/1");

        this.mvc.perform(requestTwo)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    @Transactional
    @Rollback
    public void patchUserById() throws Exception{
        String user2Data = mapper.writeValueAsString(user2);
        this.testUserRepo.save(user1);
        MockHttpServletRequestBuilder request = patch("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user2Data);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(user2.getEmail()));


    }

    @Test
    @Transactional
    @Rollback
    public void deleteUserById() throws Exception
    {
        this.testUserRepo.save(user1);
        this.testUserRepo.save(user2);
        MockHttpServletRequestBuilder requestGet = get("/users/1");
        MockHttpServletRequestBuilder request = delete("/users/1");

        this.mvc.perform(requestGet)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@example.com"));

        this.mvc.perform(request)
                .andExpect(status().isOk());




    }




}
