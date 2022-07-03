/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hspf.muell;

import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author keanu
 */
@Entity
@Table(name = "muell")
@NamedQuery(name = "Muell.findAll", query = "SELECT a FROM Muell a")
public class Muell implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private String muellart;
  @Column
  private String stadt;
  @Column
  private int plz;
  @Column
  private String strasse;
  @Column
  private int hausnummer;

  public Muell updateMuell(int id, String muellart, String stadt, int plz, String strasse, int hausnummer) {
    this.id = id;
    this.muellart = muellart;
    this.stadt = stadt;
    this.plz = plz;
    this.strasse = strasse;
    this.hausnummer = hausnummer;
    return this;
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

  public String getStadt() {
    return stadt;
  }

  public void setStadt(String stadt) {
    this.stadt = stadt;
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

}
