package neu.edu.service;

import neu.edu.pojo.User;

public interface UserService {
    public void registUser(User user);
    public User Login(User user);
    public User getUserDataByName(String name);
    public boolean existUsername(String uesrname);
}
