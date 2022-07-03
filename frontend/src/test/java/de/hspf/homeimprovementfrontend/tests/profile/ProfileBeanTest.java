package de.hspf.homeimprovementfrontend.tests.profile;

import de.hspf.homeimprovementfrontend.api.APIManager;
import de.hspf.homeimprovementfrontend.models.Account;
import de.hspf.homeimprovementfrontend.profile.ProfileBean;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Marcel
 */
@ExtendWith(MockitoExtension.class)
public class ProfileBeanTest {

  @InjectMocks
  @Inject
  private ProfileBean profileBean;

  @Mock
  private APIManager apiManager;

  public ProfileBeanTest() {
  }

  @BeforeEach
  public void setUpClass() {
  }

  @Test
  public void whenLoadUserProfile_thenAccountBeReturned() {
    when(apiManager.getAccount()).thenReturn(new Account());
    Account account = profileBean.loadUserProfile();

    assertTrue(Account.class.isInstance(account));
    assertTrue(account != null);
    verify(apiManager).getAccount();
  }

  @Test
  public void whenUpdateProfile_thenAccountBeReturned() {
    when(apiManager.putAccount(any())).thenReturn(new Account());
    Account account = profileBean.updateProfile();

    assertTrue(Account.class.isInstance(account));
    assertTrue(account != null);
    verify(apiManager).putAccount(any());
  }
}
