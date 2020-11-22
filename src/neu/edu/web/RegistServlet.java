package neu.edu.web;

import neu.edu.pojo.User;
import neu.edu.service.UserService;
import neu.edu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
        String code=req.getParameter("code");
        //2.检查验证码是否正确 这里先写死 检查是否为abcde
        if(code.equalsIgnoreCase("abcde"))
        {
            if(userService.existUsername(username))
            {
                System.out.println("用户名["+username+"]已存在！");
                req.setAttribute("msg","用户名已存在!");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                System.out.println("验证码["+ code+"]错误！");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }
            else
            {
                User user=new User(null,username,password,email);
                userService.registUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }
        else
        {
            req.setAttribute("msg","验证码错误!");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            System.out.println("验证码["+ code+"]错误！");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}
