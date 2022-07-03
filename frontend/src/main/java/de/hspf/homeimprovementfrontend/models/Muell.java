/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hspf.homeimprovementfrontend.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 *
 * @author keanu
 */
public class Muell {

  private int id;
  private String muellart;
  private int plz;
  private String strasse;
  private int hausnummer;
  private String stadt;
  private int verschmutzungsgrad; // Verschmutzungsgrad in einer Skale von x bis y

  public Muell() {
  }

  public Muell(int id, String muellart, int plz, String strasse, int hausnummer, String stadt,
          int verschmutzungsgrad) {
    this.id = id;
    this.muellart = muellart;
    this.plz = plz;
    this.strasse = strasse;
    this.hausnummer = hausnummer;
    this.stadt = stadt;
    this.verschmutzungsgrad = verschmutzungsgrad;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMuellart() {
    return muellart;
  }

  public void setMuellart(String muellart) {
    this.muellart = muellart;
  }

  public int getPlz() {
    return plz;
  }

  public void setPlz(int plz) {
    this.plz = plz;
  }

  public String getStrasse() {
    return strasse;
  }

  public void setStrasse(String strasse) {
    this.strasse = strasse;
  }

  public int getHausnummer() {
    return hausnummer;
  }

  public void setHausnummer(int hausnummer) {
    this.hausnummer = hausnummer;
  }

  public String getStadt() {
    return stadt;
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
