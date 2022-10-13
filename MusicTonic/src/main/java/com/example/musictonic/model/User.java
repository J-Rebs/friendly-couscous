package com.example.musictonic.model;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long user_UID;

    @Column(name="real_name")
    private String real_name;

    @Enumerated(EnumType.STRING)
    @Column(name="user_type")
    private UserType user_type;

    @Column(name="main_genre")
    private String main_genre;

    @Column(name="age")
    private Integer age;

    public User()
    {

    }

    public User(String real_name, UserType userType, String main_genre, Integer age){
        this.real_name = real_name;
        this.user_type = userType;
        this.main_genre = main_genre;
        this.age = age;
    }



}

