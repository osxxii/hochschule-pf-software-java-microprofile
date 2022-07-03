package de.hspf.profileservice;

import de.hspf.exceptions.UserDoesNotExistException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/user")
@RequestScoped
public class ProfileController {

  @Inject
  ProfileService profileService;

  @Inject
  JsonWebToken jwt;

  @GET
  @RolesAllowed({"user", "admin"})
  @Transactional
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response getProfile() throws IOException, FileNotFoundException, UserDoesNotExistException {
    return Response.ok(profileService.getAccount(jwt)).build();
  }

  @PUT
  @RolesAllowed({"user", "admin"})
  @Transactional
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response putProfile(Account account) throws UserDoesNotExistException {
    return Response.ok(profileService.updateAccount(account)).build();
  }

  @PUT
  @RolesAllowed({"user", "admin"})
  @Path("/users")
  @Transactional
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response putProfiles(List<Account> accounts) throws UserDoesNotExistException {
    return Response.ok(profileService.updateAccounts(accounts)).build();
  }

  @GET
  @RolesAllowed({"user", "admin"})
  @Path("/users")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response getAccounts(Account account) {
    return Response.ok(this.profileService.getAllAccounts()).build();
  }
}
