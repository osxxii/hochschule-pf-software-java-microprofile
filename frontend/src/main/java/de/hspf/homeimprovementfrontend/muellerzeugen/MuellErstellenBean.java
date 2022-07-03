/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hspf.homeimprovementfrontend.muellerzeugen;

import de.hspf.homeimprovementfrontend.api.APIManager;
import de.hspf.homeimprovementfrontend.config.ViewContextUtil;
import de.hspf.homeimprovementfrontend.models.Muell;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 *
 * @author keanu
 */
@Named(value = "muellErstellenBean")
@ApplicationScoped
public class MuellErstellenBean implements Serializable {

  @Inject
  private APIManager apiManager;

  private String muellart;
  private int plz;
  private String strasse;
  private int hausnummer;
  private String stadt;
  private int verschmutzungsgrad;// ist eine Skale von x bis y
  private Muell muell;

  @Inject
  private MuellErstellenBean muellErstellenBean;

  public String muellErstellen() throws IOException, Exception {
    Muell muellResponse = apiManager.postMuell(createMuell());
    if (muellResponse == null) {
      throw new Exception(); // if müll exists, muellservice returns null
    }
    ViewContextUtil.getFacesContext().addMessage(null,
            new FacesMessage("Müll wurde erstellt"));
    return "login";
  }

  public Muell createMuell() {
    Muell muell = new Muell();
    muell.setMuellart(muellart);
    muell.setPlz(plz);
    muell.setStrasse(strasse);
    muell.setStadt(stadt);
    muell.setHausnummer(hausnummer);
    muell.setVerschmutzungsgrad(verschmutzungsgrad);
    return muell;
  }

  public Muell getMuell() {
    return muell;
  }

  public void setMuell(Muell muell) {
    this.muell = muell;
  }

  public String getMuellart() {
    return muellart;
  }

  public int getPlz() {
    return plz;
  }

  public String getStrasse() {
    return strasse;
  }

  public int getHausnummer() {
    return hausnummer;
  }

  public String getStadt() {
    return stadt;
  }

  public void setMuellart(String muellart) {
    this.muellart = muellart;
  }

  public void setPlz(int plz) {
    this.plz = plz;
  }

  public void setStrasse(String strasse) {
    this.strasse = strasse;
  }

  public void setHausnummer(int hausnummer) {
    this.hausnummer = hausnummer;
  }

  public void setStadt(String stadt) {
    this.stadt = stadt;
  }

  public int getVerschmutzungsgrad() {
    return verschmutzungsgrad;
  }

  public void setVerschmutzungsgrad(int verschmutzungsgrad) {
    this.verschmutzungsgrad = verschmutzungsgrad;
  }

}
