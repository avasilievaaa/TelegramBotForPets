package com.course7.telegrambotforpets;

import com.course7.telegrambotforpets.controller.UserCatController;
import com.course7.telegrambotforpets.model.UserCat;
import com.course7.telegrambotforpets.repository.UserCatRepository;
import com.course7.telegrambotforpets.service.UserCatService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
public class UserCatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserCatRepository userCatRepository;

    @SpyBean
    private UserCatService userCatService;

    @InjectMocks
    private UserCatController userCatController;


    @Test
    public void contextLoads() throws Exception {
        assertThat(userCatController).isNotNull();

    }

    @Test
    public void saveUserCatTest() throws Exception {
        final String name = "Дружок";
        final long id = 2;
        JSONObject userCatObject = new JSONObject();
        userCatObject.put("name", name);

        UserCat userCat = new UserCat();
        userCat.setId(id);
        userCat.setUserName(name);
        when(userCatRepository.save(any(UserCat.class))).thenReturn(userCat);
        when(userCatRepository.findById(any(Long.class))).thenReturn(Optional.of(userCat));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cat-user")
                        .content(userCatObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.userName").value(name))
                .andExpect(jsonPath("$.id").value(id));
    }
}
