package neu.edu.web;

import neu.edu.pojo.Book;
import neu.edu.pojo.Page;
import neu.edu.service.BookService;
import neu.edu.service.impl.BookServiceIml;
import neu.edu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService=new BookServiceIml();
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        Book book= WebUtils.CopyCopyParamToBean(req.getParameterMap(),new Book());
        bookService.addBook(book);

        //req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        //因为请求转发是一次请求操作 所以这里使用请求转发的话 会出现表单重复提交的bug 所以应该使用重定向操作  重定向中/表示到工程的端口号所以应该加上工程路径
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        //Integer i=Integer.parseInt(id);
        bookService.deleteBookById(WebUtils.parseInt(id,0));
        //req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过list查询所有图书信息
        //把图书信息保存到request域中
        //请求转发到/pages/manager/book_manager.jsp页面
        List<Book> books =bookService.queryBook();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        Book book=WebUtils.CopyCopyParamToBean(req.getParameterMap(),new Book());

        System.out.println(book);
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求修改的图书的id
        Book book=bookService.queryBookById(WebUtils.parseInt(req.getParameter("id"),0));
        //2.调用bookservice.queryById()查找图书信息
        //3.将图书信息保存到request域中
        req.setAttribute("Book",book);
        //4.请求转发到pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1。获取请求参数 pagesize和pageno
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用Bookservic.page(pagesize,pageno)方法返回page对象

        Page<Book> page=bookService.page(pageNo,pageSize);
        //3.保存page对象到request域中
        page.setUrl("manager/bookServlet?action=page");
        req.setAttribute("page",page);
        System.out.println(page.getPageNo());
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
