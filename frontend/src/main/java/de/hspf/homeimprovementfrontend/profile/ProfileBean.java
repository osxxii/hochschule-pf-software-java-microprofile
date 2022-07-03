package de.hspf.homeimprovementfrontend.profile;

import de.hspf.homeimprovementfrontend.api.APIManager;
import de.hspf.homeimprovementfrontend.models.Account;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author marcel
 */
@Named(value = "profileBean")
@SessionScoped
public class ProfileBean implements Serializable {

  @Inject
  private APIManager apiManager;
  private static final long serialVersionUID = -3839928986748109482L;
  private Account account;

  public Account loadUserProfile() {
    setAccount(apiManager.getAccount());
    return account;
  }

  public Account updateProfile() {
    setAccount(apiManager.putAccount(this.account));
    return account;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

}
