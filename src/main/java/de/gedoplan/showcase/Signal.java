package de.gedoplan.showcase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Signal {

  @Getter
  private String name;

  @Getter
  @Setter
  private SignalStellung stellung;

  public Signal(String name) {
    this.name = name;
  }

}
