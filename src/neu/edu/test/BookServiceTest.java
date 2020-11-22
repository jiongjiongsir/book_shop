package neu.edu.test;

import neu.edu.dao.BookDao;
import neu.edu.pojo.Book;
import neu.edu.service.BookService;
import neu.edu.service.impl.BookServiceIml;
import org.junit.After;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {
    private BookService bookService=new BookServiceIml();
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"哈哈","liu",new BigDecimal(12345) ,12345,0,null));
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBook() {
    }
    @Test
    public void page()
    {
        System.out.println(bookService.page(1,4));
    }
    @Test
    public void pageByPrice()
    {
        System.out.println(bookService.pageByPrice(1,4,0,99));
    }
}