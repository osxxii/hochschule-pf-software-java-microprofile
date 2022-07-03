package de.hspf.homeimprovementfrontend.tests.login;

import de.hspf.homeimprovementfrontend.login.LoginBean;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Marcel
 */
@ExtendWith(MockitoExtension.class)
public class LoginBeanTest {

  @Inject
  @InjectMocks
  private LoginBean loginBean;

  public LoginBeanTest() {
  }

  @Test
  public void whenGetDate_thenLongShouldBeReturned() {
    long date = loginBean.getDate();

    assertTrue(Long.class.isInstance(date));
  }

}
