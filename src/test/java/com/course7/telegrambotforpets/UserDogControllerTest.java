package com.course7.telegrambotforpets;

import com.course7.telegrambotforpets.controller.UserDogController;
import com.course7.telegrambotforpets.model.UserDog;
import com.course7.telegrambotforpets.repository.UserDogRepository;
import com.course7.telegrambotforpets.service.UserDogService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserDogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDogRepository userDogRepository;

    @SpyBean
    private UserDogService userDogService;

    @InjectMocks
    private UserDogController userDogController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(userDogController).isNotNull();

    }

    @Test
    public void saveUserDogTest() throws Exception {
        final String name = "Дружок";
        final long id = 2;
        JSONObject userDogObject = new JSONObject();
        userDogObject.put("name", name);

        UserDog userDog = new UserDog();
        userDog.setId(id);
        userDog.setUserName(name);
        when(userDogRepository.save(any(UserDog.class))).thenReturn(userDog);
        when(userDogRepository.findById(any(Long.class))).thenReturn(Optional.of(userDog));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/dog-user")
                        .content(userDogObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.userName").value(name))
                .andExpect(jsonPath("$.id").value(id));
    }
}