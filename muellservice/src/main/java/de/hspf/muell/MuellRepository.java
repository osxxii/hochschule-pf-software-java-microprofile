/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hspf.muell;

import de.hspf.exceptions.MuellDoesNotExistException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author keanu
 */
// @ApplicationScoped
public class MuellRepository implements Serializable {

  @PersistenceContext(unitName = "pu")
  private EntityManager entityManager;

  public Muell createMuell(Muell muell) {
    entityManager.persist(muell);
    return muell;
  }

  public boolean muellExists(Muell muell) {
    // schauen ob der Müll schon sxistiert dann false und die exception raushauen
    return false;
  }

  public List<Muell> readAllMuelle() {
    return entityManager.createNamedQuery("Muell.findAll", Muell.class).getResultList();
  }

  public Muell updateMuell(Muell muellUpdated) throws MuellDoesNotExistException {
    return null;
  }

}
