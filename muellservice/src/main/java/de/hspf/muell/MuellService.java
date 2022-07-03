/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hspf.muell;

/**
 *
 * @author keanu
 */
import de.hspf.exceptions.MuellDoesNotExistException;
import java.util.List;
import javax.inject.Inject;

public class MuellService {

  @Inject
  MuellRepository muellRepository;

  public Muell createMuell(Muell muell) throws MuellDoesNotExistException {
    muellRepository.createMuell(muell);
    return muell;
  }

  public Muell updateMuell(Muell muell) throws MuellDoesNotExistException {
    this.muellRepository.updateMuell(muell);
    return muell;
  }

  public List<Muell> updateMuelle(List<Muell> muell) throws MuellDoesNotExistException {
    return muell;
  }
}
