package de.hspf.auth;

import de.hspf.exceptions.UserAlreadyExistsException;
import de.hspf.exceptions.UserDoesNotExistException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
@RequestScoped
public class AuthController {

  @Inject
  AuthService authService;

  @POST
  @Path("/signup")
  @Transactional
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response postSignUp(Account account) throws UserAlreadyExistsException {
    return Response.ok(this.authService.handleSignUp(account)).build();
  }

  @POST
  @Path("/login")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response postLogin(Account account) throws UserDoesNotExistException {
    return Response.ok(this.authService.handleLogin(account)).build();
  }

  @PUT
  @Transactional
  @Path("/user")
  @RolesAllowed({"user", "admin"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response putProfile(Account account) throws UserDoesNotExistException {
    return Response.ok(authService.updateAccount(account)).build();
  }

  @PUT
  @Transactional
  @Path("/users")
  @RolesAllowed({"user", "admin"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response putProfiles(List<Account> accounts) throws UserDoesNotExistException {
    return Response.ok(authService.updateAccounts(accounts)).build();
  }

}
