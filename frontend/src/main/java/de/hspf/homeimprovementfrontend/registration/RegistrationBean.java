package de.hspf.homeimprovementfrontend.registration;

import de.hspf.homeimprovementfrontend.api.APIManager;
import de.hspf.homeimprovementfrontend.models.Account;
import de.hspf.homeimprovementfrontend.config.ViewContextUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 *
 * @author marcel
 */
@Named(value = "registrationBean")
@SessionScoped
public class RegistrationBean implements Serializable {

  private static final long serialVersionUID = 5698088241579720720L;

  @Inject
  private APIManager apiManager;
  private String name;
  private String surName;
  private String email;
  private String username;
  private String password;
  private String repeatedPassword;

  public String register() throws IOException {
    if (!isSamePassword()) {
      ViewContextUtil.getFacesContext().addMessage(null, new FacesMessage("Sing Up failed. You provided two different passwords."));
      return "";
    }

    try {
      Account acccountResponse = apiManager.postAccount(createAccount());
      if (acccountResponse == null) {
        throw new Exception(); // if user exists, authservice returns null
      }
      ViewContextUtil.getFacesContext().addMessage(null, new FacesMessage("Signup was successfull. Now you are able to login"));
      return "login";
    } catch (Exception e) {
      ViewContextUtil.getFacesContext().addMessage(null, new FacesMessage("Signup failed. Please give it another try!"));
      return "registration";
    }
  }

  public Account createAccount() {
    Account account = new Account();
    account.setEmail(email);
    account.setPassword(password);
    account.setUsername(username);
    return account;
  }

  public boolean isSamePassword() {
    return password.equals(repeatedPassword);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurName() {
    return surName;
  }

  public void setSurName(String surName) {
    this.surName = surName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRepeatedpassword() {
    return repeatedPassword;
  }

  public void setRepeatedpassword(String repeatedpassword) {
    this.repeatedPassword = repeatedpassword;
  }

  public String getRepeatedPassword() {
    return repeatedPassword;
  }

  public void setRepeatedPassword(String repeatedPassword) {
    this.repeatedPassword = repeatedPassword;
  }

}
