package neu.edu.web;

import neu.edu.pojo.User;
import neu.edu.service.UserService;
import neu.edu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        User user=new User(null,username,password,null);
        if(userService.Login(user)==null)
        {
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",req.getParameter("username"));

            System.out.println("账号或密码错误！");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
        else
        {

            System.out.println("登录成功！");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
}
