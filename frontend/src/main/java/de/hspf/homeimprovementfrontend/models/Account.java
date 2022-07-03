package de.hspf.homeimprovementfrontend.models;

import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author Marcel
 * @author thomas.schuster
 */
@Data
public class Account {

  private int id;
  private String username;
  private String email;
  private String password;
  private Set<String> roles;
  private byte[] profilePic;
  private int age;
  private String job;
  private ExpertLevel level;
  private List<String> topics;
  private String description;

}
