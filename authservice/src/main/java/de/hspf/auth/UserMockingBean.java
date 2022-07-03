package de.hspf.auth;

/**
 *
 * @author Marcel
 * @author thomas.schuster
 */
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
@Startup
public class UserMockingBean {

  @Inject
  private AuthRepository authRepository;
  private static final Logger LOGGER = Logger.getLogger(UserMockingBean.class.getName());

  /**
   * *
   * This method will create and setup initial user data, add or modify user
   * data as needed for your application.
   */
  @PostConstruct
  public void init() {
    Account account = new Account();
    account.setEmail("admin@hs-pforzheim.de");
    account.setPassword("admin");
    account.setUsername("Admin");
    account.addRole("admin");
    authRepository.saveUser(account);
    LOGGER.info("Created admin user: {email: admin@hs-pforzheim.de, password: admin}");
  }

}
