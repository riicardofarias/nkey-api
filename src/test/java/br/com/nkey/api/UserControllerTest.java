package br.com.nkey.api;

import br.com.nkey.api.model.User;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenCreateUser_thenReturns200() throws Exception {
        User user = new User("User", "user@email.com", "secret");

        objectMapper.disable(MapperFeature.USE_ANNOTATIONS);

        mockMvc.perform(post("/users/register")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isOk());
    }

    @Test
    public void whenInvalidValue_thenReturns400() throws Exception {
        User user = new User("User", "user@email.com", null);

        objectMapper.disable(MapperFeature.USE_ANNOTATIONS);

        mockMvc.perform(post("/users/register")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void whenUserNotFound_thenReturns401() throws Exception {
        User user = new User(null, "notfounduser@email.com", "wrongpassword");

        objectMapper.disable(MapperFeature.USE_ANNOTATIONS);

        mockMvc.perform(post("/users/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void whenLoginSuccess_thenReturns200() throws Exception {
        User user = new User(null, "admin@email.com", "123");

        objectMapper.disable(MapperFeature.USE_ANNOTATIONS);

        mockMvc.perform(post("/users/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isOk());
    }
}
