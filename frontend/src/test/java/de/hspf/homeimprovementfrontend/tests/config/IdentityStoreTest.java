package de.hspf.homeimprovementfrontend.tests.config;

import de.hspf.homeimprovementfrontend.api.APIManager;
import de.hspf.homeimprovementfrontend.config.CustomInMemoryIdentityStore;
import de.hspf.homeimprovementfrontend.models.Account;
import javax.inject.Inject;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Marcel
 * @author thomas.schuster
 */
@ExtendWith(MockitoExtension.class)
public class IdentityStoreTest {

  @Mock
  APIManager apiManager;

  @Inject
  @InjectMocks
  CustomInMemoryIdentityStore store;

  public IdentityStoreTest() {
  }

  @Test
  public void whenCredentialsIsInvalid_thenNotValidated() {
    // JWT contains the following roles: admin, user, protected
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJncm91cHMiOiJbYWRtaW4sIHVzZXIsIHByb3RlY3RlZF0ifQ.obgaarM9XX_FOSnox9KljfGoh63mepbx2VQKx3bR-rQ";
    Account account = new Account();
    account.setEmail("mocki@mockingbird.us");
    account.setPassword("1234!");

    when(apiManager.loginAccount(ArgumentMatchers.any())).thenReturn(token);
    when(apiManager.getToken()).thenReturn(token);
    CredentialValidationResult result = store.validate(new UsernamePasswordCredential(account.getEmail(), account.getPassword()));

    assertTrue(result.getCallerGroups().contains("ADMIN"));
    verify(apiManager).loginAccount(ArgumentMatchers.any());
  }

  @Test
  public void whenStringIsJWT_thenTrueShouldBeReturned() {
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    boolean response = store.isJWT(token);

    assertTrue(response);
  }

  @Test
  public void whenStringIsNotJWT_thenFalseShouldBeReturned() {
    String token = "This is not a JWT";

    boolean response = store.isJWT(token);

    assertFalse(response);
  }

  @Test
  public void whenUserHasRole_thenTrueShouldBeReturned() {
    // JWT contains the following roles: admin, user, protected
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJncm91cHMiOiJbYWRtaW4sIHVzZXIsIHByb3RlY3RlZF0ifQ.obgaarM9XX_FOSnox9KljfGoh63mepbx2VQKx3bR-rQ";

    boolean response = store.hasRole("admin", token);

    assertTrue(response);
  }

  @Test
  public void whenUserHasNotRole_thenFalseShouldBeReturned() {
    // JWT contains the following roles: user, protected
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJncm91cHMiOiJbdXNlciwgcHJvdGVjdGVkXSJ9.0hRMUpeLQdV9kNjvcCx-rQVD7B3c9LI6jF17oTnjaDA";

    boolean response = store.hasRole("admin", token);

    assertFalse(response);
  }
}
