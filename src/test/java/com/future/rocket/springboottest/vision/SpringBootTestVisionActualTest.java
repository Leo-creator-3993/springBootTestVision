package com.future.rocket.springboottest.vision;

import com.future.rocket.springboottest.vision.model.User;
import com.future.rocket.springboottest.vision.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SpringBootTestVisionActualTest {

    @Autowired
    private UserService userService;

    @Before
    public void init() {
        userService.deleteAllUsers();
    }

    @Test
    public void testCreateUser() {
        User user = userService.createUser("leo", "leo@future.com");
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotNull();
        assertThat(user.getName()).isEqualTo("leo");

        long userCount = userService.countUsers();
        assertThat(userCount).isEqualTo(1);
    }

    @Test
    public void testSelectAllUser() {
        userService.createUser("foo", "foo@future.com");
        userService.createUser("leo", "leo@future.com");

        long userCount = userService.countUsers();
        assertThat(userCount).isEqualTo(2);
        System.out.println("result ==> " + userService.getAllUser());
    }
}
