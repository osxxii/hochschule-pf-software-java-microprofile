/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hspf.exceptions;

import javax.json.Json;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author keanu
 */
@Provider
public class MuellDoesNotExistException extends Exception implements ExceptionMapper<MuellDoesNotExistException> {

  private static final long serialVersionUID = 1L;

  public MuellDoesNotExistException() {
    super("User does not exists");
  }

  public MuellDoesNotExistException(String string) {
    super(string);
  }

  @Override
  public Response toResponse(MuellDoesNotExistException exception) {
    String json = Json.createObjectBuilder()
            .add("message", "Diesen Müll gibt es nicht")
            .add("status", 403)
            .build()
            .toString();
    return Response.status(Status.CONFLICT).entity(json).build();
  }
}
