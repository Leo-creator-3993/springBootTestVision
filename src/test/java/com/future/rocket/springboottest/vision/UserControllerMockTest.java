package com.future.rocket.springboottest.vision;

import com.future.rocket.springboottest.vision.controller.UserController;
import com.future.rocket.springboottest.vision.model.User;
import com.future.rocket.springboottest.vision.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerMockTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testMockGetAllUsers() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("leo");
        user.setEmail("leo@future.com");
        when(userService.getAllUser()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("leo"))
                .andExpect(jsonPath("$[0].email").value("leo@future.com"));
        verify(userService, times(1)).getAllUser();
    }
}
