package neu.edu.web;

import neu.edu.pojo.Cart;
import neu.edu.pojo.User;
import neu.edu.service.OrderService;
import neu.edu.service.impl.OrderServiceIml;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet{

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        User user=(User)req.getSession().getAttribute("user");
        System.out.println(user);
        if(user==null)
        {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId=user.getId();
        System.out.println(userId);
        OrderService orderService=new OrderServiceIml();
        String orderId=orderService.createOrder(cart,userId);
        //req.setAttribute("orderId",orderId);
        req.getSession().setAttribute("orderId",orderId);
        //使用请求转发可能会出现重复请求的bug所以这里使用重定向 上面需要换成session域
        //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
