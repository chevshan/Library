package by.malisheuski.spring.models;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "ФИО не должно быть пустым")
    private String name;

    @Min(value = 1900, message = "Возраст человека должен в формате [****]")
    @NotNull(message = "Год рождения не должен быть пустым")
    private int birthday;

    public Person() {

    }

    public Person(int id, String name, int birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
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

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }
}
