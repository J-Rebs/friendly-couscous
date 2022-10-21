package com.example.musictonic.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "user_to_group_member")
public class UserToGroupMember implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long groupId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "groupId", nullable = true)
    private Group group;

    protected UserToGroupMember() {
    }

    public UserToGroupMember(Long userId, Long groupId, User user, Group group) {
        this.userId = userId;
        this.groupId = groupId;
        this.user = user;
        this.group = group;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "UserToGroupMember{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                '}';
    }

}
