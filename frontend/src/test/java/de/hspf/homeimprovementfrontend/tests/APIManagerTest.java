package de.hspf.homeimprovementfrontend.tests;

import de.hspf.homeimprovementfrontend.api.APIManager;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
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
public class APIManagerTest {

  @Inject
  @InjectMocks
  private APIManager apiManager;

  public APIManagerTest() {
  }

  @BeforeEach
  public void setUpClass() {
  }

  @Test
  public void whenGetAuthURL_thenConnectionStringBeReturned() {
    String text = apiManager.getAuthURL();

    assertTrue(String.class.isInstance(text));
    assertTrue(!text.isEmpty());
    assertTrue(text.startsWith("http"));
  }

  @Test
  public void whenGetProfileURL_thenConnectionStringBeReturned() {
    String text = apiManager.getProfileURL();

    assertTrue(String.class.isInstance(text));
    assertTrue(!text.isEmpty());
    assertTrue(text.startsWith("http"));
  }

}
