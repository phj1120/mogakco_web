package xyz.parkh.with.mogakco;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.parkh.with.mogakco.entity.User;
import xyz.parkh.with.mogakco.service.UserService;

import java.util.List;

@SpringBootTest
public class UserTests {

    @Autowired
    UserService userService;

    @Test
    public void userListTest(){
        List<User> userList = userService.getUserListAll();
        Assertions.assertThat(userList.isEmpty()).isFalse();
    }
}
