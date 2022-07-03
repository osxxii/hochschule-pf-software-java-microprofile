package de.hspf.homeimprovementfrontend.admin;

import de.hspf.homeimprovementfrontend.api.APIManager;
import de.hspf.homeimprovementfrontend.models.Account;
import de.hspf.homeimprovementfrontend.models.ExpertLevel;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author marcel
 */
@Named(value = "adminBean")
@ApplicationScoped
public class AdminBean implements Serializable {

  private static final long serialVersionUID = -383992898674810212L;

  @Inject
  private APIManager apiManager;
  private List<Account> userList;
  private List<ExpertLevel> levelList;
  private List<String> rolesList;

  public void loadUserData() {
    setUsers(apiManager.getAccounts());

    levelList = new ArrayList();
    levelList.add(ExpertLevel.BEGINNER);
    levelList.add(ExpertLevel.INTERMEDIATE);
    levelList.add(ExpertLevel.EXPERT);

    rolesList = new ArrayList();
    rolesList.add("user");
    rolesList.add("admin");
  }

  public void updateUser() {
    apiManager.putAccountsAuthService(userList);
    apiManager.putAccountsProfileService(userList);
  }

  public List<Account> getUsers() {
    return userList;
  }

  public void setUsers(List<Account> users) {
    this.userList = users;
  }

  public List<ExpertLevel> getLevelList() {
    return levelList;
  }

  public void setLevelList(List<ExpertLevel> levelList) {
    this.levelList = levelList;
  }

  public List<String> getRolesList() {
    return rolesList;
  }

  public void setRolesList(List<String> rolesList) {
    this.rolesList = rolesList;
  }

}
