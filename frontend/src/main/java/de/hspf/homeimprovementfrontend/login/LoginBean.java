package de.hspf.homeimprovementfrontend.login;

import de.hspf.homeimprovementfrontend.profile.ProfileBean;
import de.hspf.homeimprovementfrontend.models.Account;
import de.hspf.homeimprovementfrontend.config.ViewContextUtil;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.NOT_DONE;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marcel
 */
@Named(value = "loginBean")
@ApplicationScoped
public class LoginBean implements Serializable {

  private static final long serialVersionUID = -383992898674810212L;

  private boolean loggedIn;
  private String password;
  private String username;
  private String email;
  private Account account;

  @Inject
  private SecurityContext securityContext;
  @Inject
  private ProfileBean profileBean;

  public void authenticate() throws IOException {
    switch (continueAuthentication()) {
      case SEND_CONTINUE:
        ViewContextUtil.getFacesContext().responseComplete();
        break;
      case SEND_FAILURE:
        ViewContextUtil.getFacesContext().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
        break;
      case SUCCESS:
        profileBean.loadUserProfile();
        ViewContextUtil.getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login succeed", null));
        ViewContextUtil.getFacesContext().getExternalContext().redirect(ViewContextUtil.getFacesContext().getExternalContext().getRequestContextPath() + "/app/index.xhtml");
        break;
      case NOT_DONE:
    }
  }

  private AuthenticationStatus continueAuthentication() {
    return securityContext.authenticate(
            (HttpServletRequest) ViewContextUtil.getFacesContext().getExternalContext().getRequest(),
            (HttpServletResponse) ViewContextUtil.getFacesContext().getExternalContext().getResponse(),
            AuthenticationParameters.withParams()
                    .credential(new UsernamePasswordCredential(this.email, this.password))
    );
  }

  public long getDate() {
    return System.currentTimeMillis();
  }

  public String logout() {
    ViewContextUtil.getFacesContext().getCurrentInstance().getExternalContext().invalidateSession();
    return "/login.xhtml?faces-redirect=true";
  }

  public String getUserName() {
    return username;
  }

  public void setUserName(String userName) {
    this.username = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isLoggedIn() {
    return loggedIn;
  }

  public void setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

}
