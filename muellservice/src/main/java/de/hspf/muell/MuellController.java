/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hspf.muell;

//import de.hspf.exceptions.MuellAlreadyExistsException;
import de.hspf.exceptions.MuellDoesNotExistException;
import java.util.List;
//import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
//import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author keanu
 */
@Path("/muell")
@RequestScoped
public class MuellController {

  @Inject
  MuellService muellService;

  @POST
  @Path("/create")
  @Transactional
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createMuell(Muell muell) throws MuellDoesNotExistException {
    return Response.ok(muellService.createMuell(muell)).build();
  }

  @PUT
  @Transactional
  @Path("/update")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response putMuell(Muell muell) throws MuellDoesNotExistException {
    return Response.ok(muellService.updateMuell(muell)).build();
  }

  // @PUT
  // @Transactional
  // @Path("/update")
  // @Produces(MediaType.APPLICATION_JSON)
  // @Consumes(MediaType.APPLICATION_JSON)
  // public Response putMuell(List<Muell> muelle) throws MuellDoesNotExistException {
  //     return Response.ok(muellService.updateMuell((Muell) muelle)).build();
  //     // noch nicht sicher ob richtig
  // }
}
