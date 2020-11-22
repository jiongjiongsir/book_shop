package neu.edu.web;

import com.google.gson.Gson;
import neu.edu.service.UserService;
import neu.edu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AjaxServlet extends BaseServlet{

    protected void ajaxQueryName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        UserService userService=new UserServiceImpl();
        boolean existUsername=userService.existUsername(username);
        Map<String,Object>map=new HashMap<String,Object>();
        map.put("existUsername",existUsername);
        Gson gson=new Gson();
        String json=gson.toJson(map);
        resp.getWriter().write(json);

    }
}
