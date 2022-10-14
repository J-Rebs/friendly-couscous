package com.example.musictonic.model;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long user_UID;

    @Column(name = "real_name")
    private String real_name;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType user_type;

    @Column(name = "main_genre")
    private String main_genre;

    @Column(name = "age")
    private Integer age;

    public User()
    {

    }

    public Long getUser_UID() {
        return user_UID;
    }

    public void setUser_UID(Long user_UID) {
        this.user_UID = user_UID;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_UID=" + user_UID +
                ", real_name='" + real_name + '\'' +
                ", user_type=" + user_type +
                ", main_genre='" + main_genre + '\'' +
                ", age=" + age +
                '}';
    }

    public User(String real_name, UserType userType, String main_genre, Integer age){
        this.real_name = real_name;
        this.user_type = userType;
        this.main_genre = main_genre;
        this.age = age;
    }

}

