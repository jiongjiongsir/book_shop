package neu.edu.web;

import neu.edu.pojo.Book;
import neu.edu.pojo.Cart;
import neu.edu.pojo.CartItem;
import neu.edu.service.BookService;
import neu.edu.service.impl.BookServiceIml;
import neu.edu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(req.getParameter("id"));
        //获取请求的参数 商品编号
//        System.out.println(req.getHeader("Referer"));
        //http协议中请求头header中的Referer会在每次向服务器发起请求时一同将当前地址栏中的url地址一起发送给服务器
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        //调用bookService.queryById(id)得到图书的信息
        BookService bookService=new BookServiceIml();
        Book book=bookService.queryBookById(id);
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //把图书信息转化为cartitem商品项，调用cart.addItem()添加商品
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        if(cart==null)
        {
            cart=new Cart();
            cart.addItem(cartItem);
            req.getSession().setAttribute("cart",cart);
        }
        else
        {
            cart.addItem(cartItem);
        }
        req.getSession().setAttribute("lastName",book.getName());
        //重定向回商品列表页面
        System.out.println(cart);
        //重定向回原来商品的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if(cart!=null)
        {
            cart.remove(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        cart.clear();
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        System.out.println(req.getParameter("new_count"));
        int count=WebUtils.parseInt(req.getParameter("new_count"),0);
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        cart.updateCount(id,count);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
