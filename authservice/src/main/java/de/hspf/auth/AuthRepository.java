package de.hspf.auth;

import de.hspf.exceptions.UserDoesNotExistException;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marcel
 */
@ApplicationScoped
public class AuthRepository implements Serializable {

  @PersistenceContext(unitName = "pu")
  private EntityManager entityManager;

  public Account saveUser(Account account) {
    entityManager.persist(account);
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

  public List<Account> findAccounts(String username, String email, String password) {
    return entityManager.createNamedQuery("Account.findEvent", Account.class)
            .setParameter("username", username)
            .setParameter("email", email)
            .setParameter("password", password).getResultList();
  }

  public Account updateAccount(Account accountUpdated) throws UserDoesNotExistException {
    Account account = this.getAccountByEmail(accountUpdated);
    account.updateAccount(accountUpdated.getRoles());
    entityManager.merge(account);
    return account;
  }

}
