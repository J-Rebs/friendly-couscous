package com.example.musictonic.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    // definition of entity
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long userId;

    // column names can have format word_word
    // but spring may get confused if you do the same with the Java variable
    // instead do db has "real_name", Java has variable "realName"
    @Column(name = "real_name")
    private String realName;

    @Enumerated(EnumType.ORDINAL) // see: https://www.baeldung.com/jpa-persisting-enums-in-jpa
    @Column(name = "user_type")
    private UserType userType;

    @Column(name = "main_genre")
    private String mainGenre;

    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "user")
    private Set<PlaylistToSubscriber> playlistToSubscriber;

    @OneToMany(mappedBy = "user")
    private Set<UserToGroupMember> userToGroupMember;

    @OneToMany(mappedBy = "user")
    private Set<AnalyticsUser> analyticsUser;

    // constructors
    protected User() {
    }

    // public constructor for use

    public User(Long userId, String realName, UserType userType, String mainGenre, Integer age) {
        this.userId = userId;
        this.realName = realName;
        this.userType = userType;
        this.mainGenre = mainGenre;
        this.age = age;
    }

    // getters and setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getMainGenre() {
        return mainGenre;
    }

    public void setMainGenre(String mainGenre) {
        this.mainGenre = mainGenre;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setUser_id(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + userId +
                ", real_name='" + realName + '\'' +
                ", user_type=" + userType +
                ", main_genre='" + mainGenre + '\'' +
                ", age=" + age +
                '}';
    }


}

