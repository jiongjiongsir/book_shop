package neu.edu.service.impl;

import neu.edu.dao.UserDao;
import neu.edu.dao.impl.UserDaoImpl;
import neu.edu.pojo.User;
import neu.edu.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User Login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if(userDao.queryUserByUsername(username)==null)
        {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public User getUserDataByName(String name) {
        return userDao.queryUserByUsername(name);
    }
}
