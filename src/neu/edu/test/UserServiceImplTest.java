package neu.edu.test;

import neu.edu.pojo.User;
import neu.edu.service.UserService;
import neu.edu.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceImplTest {

    @Test
    public void registUser() {
        UserService userService=new UserServiceImpl();
        User user=new User(null,"zhou","123456","bbj");
        userService.registUser(user);
    }

    @Test
    public void login() {
        UserService userService=new UserServiceImpl();
        System.out.println(userService.Login(new User(null,"liu","123",null)));
    }

    @Test
    public void existUsername() {
        UserService userService=new UserServiceImpl();
        System.out.println(userService.existUsername("liu"));
    }
}