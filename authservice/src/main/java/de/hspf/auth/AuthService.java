package de.hspf.auth;

import de.hspf.exceptions.UserAlreadyExistsException;
import de.hspf.jwt.JWTTokenProvider;
import de.hspf.exceptions.UserDoesNotExistException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Marcel
 */
@ApplicationScoped
public class AuthService {

  @Inject
  JWTTokenProvider tokenProvider;
  @Inject
  AuthRepository authRepository;

  public Account handleSignUp(Account account) throws UserAlreadyExistsException {
    if (authRepository.userExists(account)) {
      throw new UserAlreadyExistsException();
    }
    account.addRole("user");
    return this.authRepository.saveUser(account);
  }

  public String handleLogin(Account account) throws UserDoesNotExistException {
    if (account.getPassword().equals(this.authRepository.getAccountByEmail(account).getPassword())) {
      return this.tokenProvider.generateJWT(this.authRepository.getAccountByEmail(account));
    }
    return null;
  }

  public Account updateAccount(Account account) throws UserDoesNotExistException {
    Account accountUpdated = this.authRepository.updateAccount(account);
    return accountUpdated;
  }

  public List<Account> updateAccounts(List<Account> accounts) throws UserDoesNotExistException {
    accounts.forEach(account -> {
      try {
        this.authRepository.updateAccount(account);
      } catch (UserDoesNotExistException ex) {
        Logger.getLogger(AuthService.class.getName()).log(Level.SEVERE, null, ex);
      }
    });
    return accounts;
  }

}
