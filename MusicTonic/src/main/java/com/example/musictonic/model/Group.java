package com.example.musictonic.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long groupId;

    @Column(name = "admin")
    private String admin;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "group")
    private Set<UserToGroupMember> userToGroupMembers;

    @OneToMany(mappedBy = "group")
    private Set<AnalyticsGroup> analyticsGroups;

    // constructors
    protected Group() {
    }

    public Group(Long groupId, String admin, String groupName, String description) {
        this.groupId = groupId;
        this.admin = admin;
        this.groupName = groupName;
        this.description = description;
    }


    // getters and setters
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", admin='" + admin + '\'' +
                ", groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

