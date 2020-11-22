package neu.edu.test;

import neu.edu.dao.BookDao;
import neu.edu.dao.impl.BookDaoIml;
import neu.edu.pojo.Book;
import neu.edu.service.BookService;
import neu.edu.service.impl.BookServiceIml;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookDaoImlTest {
    BookDao bookDao=new BookDaoIml();
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,9999));
    }

    @Test
    public void queryForItemsByPrice() {
        for (Book book : bookDao.queryForItemsByPrice(0,90,1, 4)) {
            System.out.println(book);
        }
    }
}