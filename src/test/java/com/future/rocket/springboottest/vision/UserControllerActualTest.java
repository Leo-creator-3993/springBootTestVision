package com.future.rocket.springboottest.vision;

import com.future.rocket.springboottest.vision.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class UserControllerActualTest {

    @Autowired
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    //轻量级方式模拟HTTP请求,无需启动HTTP服务器
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userService.deleteAllUsers();
        userService.createUser("leo", "leo@future.com");
        userService.createUser("foo", "foo@future.com");
    }

    @Test
    public void testGetAllUsers() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("###===> responseBody = " + responseBody);

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name").value(hasItems("leo", "foo")))
                .andExpect(jsonPath("$[*].email").value(hasItems("leo@future.com", "foo@future.com")));
    }
}
