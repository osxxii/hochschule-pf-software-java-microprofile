package de.hspf.homeimprovementfrontend.config;

import de.hspf.homeimprovementfrontend.api.APIManager;
import de.hspf.homeimprovementfrontend.models.Account;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import org.primefaces.shaded.json.JSONException;
import org.primefaces.shaded.json.JSONObject;

/**
 *
 * @author Marcel
 */
@ApplicationScoped
public class CustomInMemoryIdentityStore implements IdentityStore {

  @Inject
  private APIManager apiManager;

  @Override
  public CredentialValidationResult validate(Credential credential) {

    UsernamePasswordCredential login = (UsernamePasswordCredential) credential;

    Account account = new Account();
    account.setEmail(login.getCaller());
    account.setPassword(login.getPasswordAsString());

    apiManager.setToken(apiManager.loginAccount(account));

    if (!apiManager.getToken().isEmpty() && isJWT(apiManager.getToken()) && hasRole("admin", apiManager.getToken())) {
      return new CredentialValidationResult("admin", new HashSet<>(Arrays.asList("ADMIN")));
    } else if (!apiManager.getToken().isEmpty() && isJWT(apiManager.getToken())) {
      return new CredentialValidationResult("user", new HashSet<>(Arrays.asList("USER")));
    } else {
      return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }
  }

  public boolean isJWT(String jwt) {
    String[] jwtSplitted = jwt.split("\\.");
    if (jwtSplitted.length != 3) {
      return false;
    }
    try {
      String jsonFirstPart = new String(Base64.getDecoder().decode(jwtSplitted[0]));
      JSONObject firstPart = new JSONObject(jsonFirstPart);
      if (!firstPart.has("alg")) {
        return false;
      }
      String jsonSecondPart = new String(Base64.getDecoder().decode(jwtSplitted[1]));
      JSONObject secondPart = new JSONObject(jsonSecondPart);
    } catch (JSONException err) {
      return false;
    }
    return true;
  }

  public boolean hasRole(String role, String jwt) {
    String[] jwtSplitted = jwt.split("\\.");
    try {
      String jsonSecondPart = new String(Base64.getDecoder().decode(jwtSplitted[1]));
      JSONObject secondPart = new JSONObject(jsonSecondPart);
      return secondPart.get("groups").toString().contains("admin");
    } catch (JSONException err) {
      return false;
    }
  }
}
