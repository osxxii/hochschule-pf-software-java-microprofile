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
public class MuellAlreadyExistsException extends Exception implements ExceptionMapper<MuellAlreadyExistsException> {

  private static final long serialVersionUID = 1L;

  public MuellAlreadyExistsException() {
    super("User already exists");
  }

  public MuellAlreadyExistsException(String string) {
    super(string);
  }

  @Override
  public Response toResponse(MuellAlreadyExistsException exception) {
    String json = Json.createObjectBuilder()
            .add("message", "Dieser Müll ist schon vorhanden")
            .add("status", 403)
            .build()
            .toString();
    return Response.status(Status.CONFLICT).entity(json).build();
  }
}
