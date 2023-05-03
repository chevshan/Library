package by.malisheuski.spring.dao;

import by.malisheuski.spring.models.Book;
import by.malisheuski.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM person where name = ?", new Object[]{name}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Person showOwner(int idBook) {
        return jdbcTemplate.query("select person.name from person JOIN book ON (person.id = book.person_id) where book.id = ?",
                new Object[]{idBook}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(name, birthday) values(?, ?)",
                person.getName(), person.getBirthday());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("update person set name = ?, birthday = ? where id = ?",
                person.getName(), person.getBirthday(), person.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id = ?", id);
    }

    public List<Book> getBooksByPersonId(int personId) {
        return jdbcTemplate.query("select name, author, year from book where person_id = ?",
                new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }
}
