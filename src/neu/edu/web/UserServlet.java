package neu.edu.web;

import neu.edu.pojo.User;
import neu.edu.service.UserService;
import neu.edu.service.impl.UserServiceImpl;
import neu.edu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        User user=new User(null,username,password,null);
        User user1 =new User();
        if(userService.Login(user)==null)
        {
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",req.getParameter("username"));
            System.out.println("账号或密码错误！");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
        else
        {
            user=userService.getUserDataByName(username);
            req.getSession().setAttribute("user",user);
            System.out.println("登录成功！");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
        String token=req.getParameter("code");
        String code=(String)req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");


        //2.检查验证码是否正确 这里先写死 检查是否为abcde
        if(code.equalsIgnoreCase(token))
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
                try {
                    User user= WebUtils.CopyCopyParamToBean(req.getParameterMap(),new User());
                    userService.registUser(user);
                    user=userService.getUserDataByName(username);
                    req.getSession().setAttribute("user",user);
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

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

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }


}
