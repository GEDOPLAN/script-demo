package de.gedoplan.showcase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Gleis {

  @Getter
  private String name;

  @Getter
  @Setter
  private boolean besetzt;

  public Gleis(String name) {
    this.name = name;
  }

}
