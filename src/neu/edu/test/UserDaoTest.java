package neu.edu.test;

import neu.edu.dao.UserDao;
import neu.edu.dao.impl.UserDaoImpl;
import neu.edu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao=new UserDaoImpl();
        System.out.println(userDao.queryUserByUsername("zhang"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao=new UserDaoImpl();
    }

    @Test
    public void saveUser() {
        User user=new User(null,"zhang","123456","asdasd233");
        UserDao userDao=new UserDaoImpl();
        userDao.saveUser(user);
    }
}