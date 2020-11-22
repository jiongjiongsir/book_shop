package neu.edu.test;

import neu.edu.dao.BookDao;
import neu.edu.dao.impl.BookDaoIml;
import neu.edu.pojo.Book;
import org.junit.After;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoTest {
    BookDao bookDao=new BookDaoIml();
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"jiongjiong","liu",new BigDecimal(12345),9999,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(31,"jiongjiong","liu",new BigDecimal(23333),9999,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(20));
    }

    @Test
    public void queryBook() {
        System.out.println(bookDao.queryBook());
    }
    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }
    @Test
    public void queryForItems() {
        for (Book book : bookDao.queryForItems(1, 4)) {
            System.out.println(book);
        }
    }
}