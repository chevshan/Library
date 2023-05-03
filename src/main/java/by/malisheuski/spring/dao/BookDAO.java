package by.malisheuski.spring.dao;

import by.malisheuski.spring.models.Book;
import by.malisheuski.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book(name, author, year) values(?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("update book set name = ?, author = ?, year = ? where id = ?",
                book.getName(), book.getAuthor(), book.getYear(), book.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where id = ?", id);
    }

    public void assignBook(int id, Person person) {
        jdbcTemplate.update("update book set person_id = ? where id = ?", person.getId(), id);
    }

    public void releaseBook(int id) {
        jdbcTemplate.update("update book set person_id = null where id = ?", id);
    }
}
