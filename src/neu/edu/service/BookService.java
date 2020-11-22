package neu.edu.service;

import neu.edu.pojo.Book;
import neu.edu.pojo.Page;

import java.util.List;

public interface BookService {
    public void addBook(Book book);
    public void deleteBookById(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBook();

    Page<Book> page(int pageNo, int pageSize);



    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
