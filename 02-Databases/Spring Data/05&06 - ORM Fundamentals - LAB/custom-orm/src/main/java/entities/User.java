package entities;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

import java.time.LocalDate;

@Entity(name = "users") //в базата данни таблица users
public class User {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "username") //в базата данни колона username
    private String username;

    @Column(name = "age") //в базата данни колона age
    private int age;

    @Column(name = "registration_date") //в базата данни колона registration_date
    private LocalDate registrationDate;

    //Add new field when adding column to the database - we change only in the User class
    @Column(name = "last_logged_in")
    private LocalDate lastLoggedIn;

    public User() {
    }

    public User(String username, int age, LocalDate registrationDate) {
        this.username = username;
        this.age = age;
        this.registrationDate = registrationDate;
        this.lastLoggedIn = LocalDate.now();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", registrationDate=" + registrationDate +
                ", lastLoggedIn=" + lastLoggedIn +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(LocalDate lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }
}
