package de.hspf.profileservice;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Marcel
 */
@Entity
@Table(name = "account")
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
public class Account implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @Column
  private String username;
  @Column
  private String email;
  @Column
  private String password;
  @Column
  private Set<String> roles;
  @Column
  private byte[] profilePic;
  @Column
  private int age;
  @Column
  private String job;
  @Column
  private ExpertLevel level;
  @Column
  private List<String> topics;
  @Column
  private String description;

  public Account updateAccount(String job, int age, ExpertLevel level, List<String> topics, String description, Set<String> roles) {
    setJob(job);
    setAge(age);
    setLevel(level);
    setTopics(topics);
    setDescription(description);
    setRoles(roles);
    return this;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }

  public byte[] getProfilePic() {
    return profilePic;
  }

  public void setProfilePic(byte[] profilePic) {
    this.profilePic = profilePic;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public ExpertLevel getLevel() {
    return level;
  }

  public void setLevel(ExpertLevel level) {
    this.level = level;
  }

  public List<String> getTopics() {
    return topics;
  }

  public void setTopics(List<String> topics) {
    this.topics = topics;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
