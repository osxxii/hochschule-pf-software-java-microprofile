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
public class UserAlreadyExistsException extends Exception implements ExceptionMapper<UserAlreadyExistsException> {

  private static final long serialVersionUID = 1L;

  public UserAlreadyExistsException() {
    super("User already exists");
  }

  public UserAlreadyExistsException(String string) {
    super(string);
  }

  @Override
  public Response toResponse(UserAlreadyExistsException exception) {
    String json = Json.createObjectBuilder()
            .add("message", "User already exists")
            .add("status", 403)
            .build()
            .toString();
    return Response.status(Status.CONFLICT).entity(json).build();
  }
}
