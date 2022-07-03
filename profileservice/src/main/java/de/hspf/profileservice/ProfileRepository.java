package de.hspf.profileservice;

import de.hspf.exceptions.UserDoesNotExistException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marcel
 */
@ApplicationScoped
public class ProfileRepository implements Serializable {

  private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  @PersistenceContext(unitName = "pu")
  private EntityManager entityManager;

  public Account saveUser(Account account) {
    logger.log(Level.INFO, "Save user: {0}", account.getUsername());
    entityManager.persist(account);
    return account;
  }

  public Account updateAccount(Account accountUpdated) throws UserDoesNotExistException {
    Account account = this.getAccountByEmail(accountUpdated);
    account.updateAccount(accountUpdated.getJob(), accountUpdated.getAge(), accountUpdated.getLevel(), accountUpdated.getTopics(), accountUpdated.getDescription(), accountUpdated.getRoles());
    entityManager.merge(account);
    return account;
  }

  public boolean userExists(Account signUpAccount) {
    return this.readAllAccounts().stream().anyMatch((account) -> (signUpAccount.getEmail().equals(account.getEmail())));
  }

  public Account getAccountByEmail(Account loginAccount) throws UserDoesNotExistException {
    for (Account account : this.readAllAccounts()) {
      if (loginAccount.getEmail().equals(account.getEmail())) {
        return account;
      }
    }
    throw new UserDoesNotExistException();
  }

  public List<Account> readAllAccounts() {
    return entityManager.createNamedQuery("Account.findAll", Account.class).getResultList();
  }

  public List<Account> findEvent(String username, String email, String password) {
    return entityManager.createNamedQuery("Account.findEvent", Account.class)
            .setParameter("username", username)
            .setParameter("email", email)
            .setParameter("password", password).getResultList();
  }

}
