package by.malisheuski.spring.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Book {
    private int id;

    @NotEmpty(message = "Название книги не должно быть пустое")
    private String name;

    @NotEmpty(message = "Имя автора не должно быть пустое")
    private String author;

    @Min(value = 1, message = "Год выпуска книги должен быть больше нуля")
    @NotNull(message = "Год выпуска книги не должен быть пустым")
    private int year;

    public Book() {}

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}