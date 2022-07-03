/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hspf.homeimprovementfrontend.muell;

import de.hspf.homeimprovementfrontend.api.APIManager;
import de.hspf.homeimprovementfrontend.models.Muell;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author keanu
 */
@Named(value = "muellBean")
@SessionScoped
public class MuellBean implements Serializable {

  @Inject
  private APIManager apiManager;
  private static final long serialVersionUID = -3839928986748109482L;
  private Muell muell;

  public Muell loadMuell() {
    setMuell(apiManager.getMuell());
    return muell;
  }

  public Muell updateMuell() {
    setMuell(apiManager.putMuell(this.muell));
    return muell;
  }

  public Muell getMuell() {
    return muell;
  }

  public void setMuell(Muell muell) {
    this.muell = muell;
  }
}
