package de.hspf.exceptions;

import javax.json.Json;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Marcel
 */
@Provider
public class UserDoesNotExistException extends Exception implements ExceptionMapper<UserDoesNotExistException> {

  private static final long serialVersionUID = 1L;

  public UserDoesNotExistException() {
    super("User does not exists");
  }

  public UserDoesNotExistException(String string) {
    super(string);
  }

  @Override
  public Response toResponse(UserDoesNotExistException exception) {
    String json = Json.createObjectBuilder()
            .add("message", "User does not exist")
            .add("status", 403)
            .build()
            .toString();
    return Response.status(Status.CONFLICT).entity(json).build();
  }
}
