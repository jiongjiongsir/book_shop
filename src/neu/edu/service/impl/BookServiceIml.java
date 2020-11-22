package neu.edu.service.impl;

import neu.edu.dao.BookDao;
import neu.edu.dao.impl.BaseDao;
import neu.edu.dao.impl.BookDaoIml;
import neu.edu.pojo.Book;
import neu.edu.pojo.Page;
import neu.edu.service.BookService;

import java.util.List;

public class BookServiceIml  implements BookService {
    private BookDao bookDao=new BookDaoIml();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBook() {
        return bookDao.queryBook();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book>page=new Page<Book>();
        Integer pageTotalCount=bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0)
        {
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);
        if(pageNo<1)
        {
            //System.out.println(pageNo);
            pageNo=1;
        }
        else if(pageNo>pageTotal)
        {
            pageNo=pageTotal;
        }

        page.setPageNo(pageNo);
        page.setPageSize(pageSize);


        int begin=(pageNo-1)*pageSize;
        List<Book>items=bookDao.queryForItems(begin,pageSize);
        page.setItems(items);
        return page;
    }
    public Page<Book> pageByPrice(int pageNo, int pageSize,int min,int max) {
        Page<Book>page=new Page<Book>();
        Integer pageTotalCount=bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0)
        {
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);
        if(pageNo<1)
        {
            //System.out.println(pageNo);
            pageNo=1;
        }
        else if(pageNo>pageTotal)
        {
            pageNo=pageTotal;
        }

        page.setPageNo(pageNo);
        page.setPageSize(pageSize);


        int begin=(pageNo-1)*pageSize;
        List<Book>items=bookDao.queryForItemsByPrice(min,max,begin,pageSize);
        page.setItems(items);
        return page;
    }
}
