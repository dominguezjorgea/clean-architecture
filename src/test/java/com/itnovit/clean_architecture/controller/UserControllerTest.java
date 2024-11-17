package com.itnovit.clean_architecture.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.itnovit.clean_architecture.entity.User;
import com.itnovit.clean_architecture.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import static org.mockito.Mockito.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetAllUsers() throws Exception {
        // Mock the service to return a list of users
        when(userService.getAllUsers()).thenReturn(Arrays.asList(
                new User("John Doe", "john.doe@example.com"),
                new User("Jane Smith", "jane.smith@example.com")
        ));

        // Perform GET request and verify the JSON response
        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));

        // Verify the interaction with the mocked service
        verify(userService, times(1)).getAllUsers();
    }
}
