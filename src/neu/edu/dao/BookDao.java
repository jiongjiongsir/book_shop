package neu.edu.dao;

import neu.edu.pojo.Book;

import java.util.List;

public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book>queryBook();

    Integer queryForPageTotalCount();

    List<Book> queryForItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForItemsByPrice(int min, int max, int begin, int pageSize);
}
