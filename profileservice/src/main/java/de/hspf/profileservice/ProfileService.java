package de.hspf.profileservice;

import de.hspf.exceptions.UserDoesNotExistException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class ProfileService {

  @Inject
  ProfileRepository profileRepository;
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  public Account getAccount(JsonWebToken jwt) throws FileNotFoundException, IOException, UserDoesNotExistException {
    Account account = new Account();
    account.setEmail(jwt.getClaim("sub").toString());
    account.setUsername(jwt.getClaim("username").toString());
    account.setRoles(jwt.getGroups());

    // Set Profile Pic for account
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("man.png").getFile());
    byte[] picInBytes = new byte[(int) file.length()];
    try ( FileInputStream fileInputStream = new FileInputStream(file)) {
      fileInputStream.read(picInBytes);
    }
    account.setProfilePic(picInBytes);

    // If user already exists, load object from database. Otherwise save it in
    // database
    if (!profileRepository.userExists(account)) {
      account = this.setUpDefaultAccount(account);
      profileRepository.saveUser(account);
      LOGGER.log(Level.INFO, "Save new user: {0}", account.getUsername());
    } else {
      account = profileRepository.getAccountByEmail(account);
      LOGGER.log(Level.INFO, "Load user from database: {0}", account.getUsername());
    }

    return account;
  }

  private Account setUpDefaultAccount(Account account) {
    account.setAge(0);
    account.setJob("");
    account.setLevel(ExpertLevel.BEGINNER);
    account.setDescription("");
    return account;
  }

  public Account updateAccount(Account account) throws UserDoesNotExistException {
    Account acc = this.profileRepository.updateAccount(account);
    return acc;
  }

  public List<Account> updateAccounts(List<Account> accounts) {
    accounts.forEach((account) -> {
      try {
        profileRepository.updateAccount(account);
      } catch (UserDoesNotExistException ex) {
        Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
      }
    });
    return accounts;
  }

  public List<Account> getAllAccounts() {
    return this.profileRepository.readAllAccounts();
  }
}
