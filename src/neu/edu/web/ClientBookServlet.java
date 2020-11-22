package neu.edu.web;

import neu.edu.pojo.Book;
import neu.edu.pojo.Page;
import neu.edu.service.BookService;
import neu.edu.service.impl.BookServiceIml;
import neu.edu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{
    BookService bookService=new BookServiceIml();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1。获取请求参数 pagesize和pageno
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用Bookservic.page(pagesize,pageno)方法返回page对象

        Page<Book> page=bookService.page(pageNo,pageSize);
        //3.保存page对象到request域中
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page",page);
        System.out.println("前台的servlet");
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1。获取请求参数 pagesize和pageno
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min=WebUtils.parseInt(req.getParameter("min"),0);
        int max=WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        //2.调用Bookservic.page(pagesize,pageno)方法返回page对象

        Page<Book> page=bookService.pageByPrice(pageNo,pageSize,min,max);
        //3.保存page对象到request域中
        StringBuilder sb=new StringBuilder("client/bookServlet?action=pageByPrice");
        if(req.getParameter("min")!=null)
        {
            sb.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max")!=null)
        {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        req.setAttribute("page",page);
        System.out.println("前台的servlet");
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
