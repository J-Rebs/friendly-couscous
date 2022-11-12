package com.example.musictonic.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * <h3>This is the user to group member entity.</h3>
 * Note: required for compliance with checkstyle
 */
@Entity
@Table(name = "user_to_group_member")
public class UserToGroupMember implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = true)
  private User user;

  @ManyToOne
  @JoinColumn(name = "groupId", nullable = true)
  private Group group;

  protected UserToGroupMember() {
  }

  /**
   * Parameterized constructor for UserToGroupMember.
   *
   * @param id    - the unique ID for this UserToGroupMember object
   * @param user  - the User object associated with this UserToGroupMember
   * @param group - the Group object asssociated with this UserToGroupMember
   */
  public UserToGroupMember(Long id, User user, Group group) {
    this.id = id;
    this.user = user;
    this.group = group;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  @Override
  public String toString() {
    return "UserToGroupMember{"
        + "id=" + id
        + ", user=" + user
        + ", group=" + group
        + '}';
  }

}
