package de.hspf.homeimprovementfrontend.models;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author ThomasSchuster
 */
@Named
@RequestScoped
public class SelectOneButtonView {

  private String option;

  public String getOption() {
    return option;
  }

  public void setOption(String option) {
    this.option = option;
  }
}
