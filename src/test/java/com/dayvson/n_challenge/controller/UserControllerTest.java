package com.dayvson.n_challenge.controller;

import com.dayvson.n_challenge.dto.UserRequest;
import com.dayvson.n_challenge.dto.UserResponse;
import com.dayvson.n_challenge.security.JwtUtil;
import com.dayvson.n_challenge.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public UserService userService() {
            return mock(UserService.class);
        }
    }

    @TestConfiguration
    static class JwtConfig {
        @Bean
        public JwtUtil jwtUtil() {
            return mock(JwtUtil.class);
        }
    }


    @Test
    @DisplayName("Should return 200 when fetching all users")
    void shouldReturnUsers() throws Exception {
        UserResponse response = new UserResponse();
        response.setId(1L);
        response.setFullName("Dayvson");
        response.setEmail("dayvson@email.com");
        response.setNationalId("123456789EU");

        when(userService.findAll()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("dayvson@email.com"));
    }

    @Test
    @DisplayName("Should successfully create a user")
    void shouldCreateUser() throws Exception {
        UserRequest request = new UserRequest();
        request.setFullName("Dayvson");
        request.setEmail("dayvson@email.com");
        request.setNationalId("123456789EU");
        request.setPassword("123");

        UserResponse response = new UserResponse();
        response.setId(1L);
        response.setFullName(request.getFullName());
        response.setEmail(request.getEmail());
        response.setNationalId(request.getNationalId());

        when(userService.create(any())).thenReturn(response);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("dayvson@email.com"));
    }
}
