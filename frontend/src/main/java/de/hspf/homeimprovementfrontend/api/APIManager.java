package de.hspf.homeimprovementfrontend.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import de.hspf.homeimprovementfrontend.models.Account;
import de.hspf.homeimprovementfrontend.registration.RegistrationBean;
import de.hspf.homeimprovementfrontend.models.Muell;
import de.hspf.homeimprovementfrontend.muellerzeugen.MuellErstellenBean;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class APIManager {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private String authURL;
  private String profileURL;
  private String muellURL;
  private String token;
  private Client client;

  @PostConstruct
  public void init() {
    this.client = ClientBuilder.newClient();
  }

  public APIManager() {
    // Load URL for Authentication and Profile Service from config.properties
    try ( InputStream input = RegistrationBean.class.getClassLoader().getResourceAsStream("config.properties")) {
      Properties prop = new Properties();
      prop.load(input);
      setAuthURL(prop.getProperty("authservice.url"));
      setMuellURL(prop.getProperty("muellservice.url"));
      setProfileURL(prop.getProperty("profileservice.url"));
    } catch (IOException ex) {
      LOGGER.throwing(authURL, authURL, ex);
    }
  }

  public String loginAccount(Account account) {
    try {
      WebTarget target = client.target(this.getAuthURL() + "/data/auth/login");
      return target.request().post(Entity.entity(account, MediaType.APPLICATION_JSON)).readEntity(String.class);
    } catch (Exception e) {
      LOGGER.info("Not able to login account");
      return null;
    }
  }

  public Account postAccount(Account account) {
    try {
      WebTarget target = client.target(this.getAuthURL() + "/data/auth/signup");
      return target.request().post(Entity.entity(account, MediaType.APPLICATION_JSON)).readEntity(Account.class);
    } catch (Exception e) {
      LOGGER.info("Not able to sign up account");
      return null;
    }
  }

  public Account putAccount(Account account) {
    try {
      WebTarget target = client.target(this.getProfileURL() + "/data/user");
      Response response = target.request().header("authorization", "Bearer " + token)
              .put(Entity.entity(account, MediaType.APPLICATION_JSON));
      Gson gson = new Gson();
      return gson.fromJson(String.format(response.readEntity(String.class)), Account.class);
    } catch (Exception e) {
      LOGGER.info((Supplier<String>) e);
      return null;
    }
  }

  public List<Account> putAccountsProfileService(List<Account> accounts) {
    try {
      WebTarget target = client.target(this.getProfileURL() + "/data/user/users");
      Response response = target.request().header("authorization", "Bearer " + token)
              .put(Entity.entity(accounts, MediaType.APPLICATION_JSON));
      return response.readEntity(new GenericType<List<Account>>() {
      });
    } catch (Exception e) {
      LOGGER.info((Supplier<String>) e);
      return null;
    }
  }

  public List<Account> putAccountsAuthService(List<Account> accounts) {
    try {
      WebTarget target = client.target(this.getAuthURL() + "/data/auth/users");
      Response response = target.request().header("authorization", "Bearer " + token)
              .put(Entity.entity(accounts, MediaType.APPLICATION_JSON));
      return response.readEntity(new GenericType<List<Account>>() {
      });
    } catch (Exception e) {
      LOGGER.info((Supplier<String>) e);
      return null;
    }
  }

  public Account putAccountAuthService(Account account) {
    try {
      WebTarget target = client.target(this.getAuthURL() + "/data/auth/user");
      Response response = target.request().header("authorization", "Bearer " + token)
              .put(Entity.entity(account, MediaType.APPLICATION_JSON));
      Gson gson = new Gson();
      return gson.fromJson(String.format(response.readEntity(String.class)), Account.class);
    } catch (Exception e) {
      LOGGER.info((Supplier<String>) e);
      return null;
    }
  }

  public Account getAccount() {
    LOGGER.info("Load user profile from profile service...");
    try {
      WebTarget target = client.target(this.getProfileURL() + "/data/user");
      Response response = target.request().header("authorization", "Bearer " + this.getToken()).buildGet()
              .invoke();
      Account acc;
      Gson gson = new Gson();
      return gson.fromJson(String.format(response.readEntity(String.class)), Account.class);
    } catch (JsonSyntaxException e) {
      LOGGER.info("Not able to access profile service");
      return null;
    }
  }

  public List<Account> getAccounts() {
    try {
      WebTarget target = ClientBuilder.newClient().target(this.getProfileURL() + "/data/user/users");
      Response response = target.request().header("authorization", "Bearer " + this.getToken()).buildGet()
              .invoke();
      return response.readEntity(new GenericType<List<Account>>() {
      });
    } catch (JsonSyntaxException e) {
      LOGGER.info("Not able to access profile service");
      return null;
    }
  }

  public Muell postMuell(Muell muell) {
    try {
      WebTarget target = client.target(this.getMuellURL() + "/data/muell/create");
      return target.request().post(Entity.entity(muell, MediaType.APPLICATION_JSON)).readEntity(Muell.class);
    } catch (Exception e) {
      LOGGER.info("Not able to create Müll");
      return null;
    }
  }

  public Muell getMuell() {
    LOGGER.info("Load Müll from müll service...");
    try {
      WebTarget target = client.target(this.getMuellURL() + "/data/muell/get");
      Response response = target.request().header("authorization", "Bearer " + this.getToken()).buildGet()
              .invoke();
      Muell muell;
      Gson gson = new Gson();
      return gson.fromJson(String.format(response.readEntity(String.class)), Muell.class);
    } catch (JsonSyntaxException e) {
      LOGGER.info("Not able to access Müll service");
      return null;
    }
  }

  public Muell putMuell(Muell muell) {
    try {
      WebTarget target = client.target(this.getMuellURL() + "/data/muell/update");
      Response response = target.request().header("authorization", "Bearer " + token)
              .put(Entity.entity(muell, MediaType.APPLICATION_JSON));
      Gson gson = new Gson();
      return gson.fromJson(String.format(response.readEntity(String.class)), Muell.class);
    } catch (Exception e) {
      LOGGER.info((Supplier<String>) e);
      return null;
    }
  }

  public String getAuthURL() {
    return authURL;
  }

  private void setAuthURL(String authURL) {
    this.authURL = authURL;
  }

  public String getProfileURL() {
    return profileURL;
  }

  private void setProfileURL(String profileURL) {
    this.profileURL = profileURL;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getMuellURL() {
    return muellURL;
  }

  public void setMuellURL(String muellURL) {
    this.muellURL = muellURL;
  }

}
