package de.gedoplan.showcase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JavaApiTest extends ScriptTestBase {

  private Gleis gleis406 = new Gleis("406");
  private Signal signalSbk2 = new Signal("Sbk2");

  public JavaApiTest() {
    super("groovy");
  }

  @Test
  public void testFrei() throws Exception {
    System.out.printf("----- %s - testFrei -----\n", this.engineName);

    doTest(false, SignalStellung.FAHRT);
  }

  @Test
  public void testBesetzt() throws Exception {
    System.out.printf("----- %s - testBesetzt -----\n", this.engineName);

    doTest(true, SignalStellung.HALT);
  }

  private void doTest(boolean besetzt, SignalStellung stellung) throws Exception {

    this.gleis406.setBesetzt(besetzt);
    this.signalSbk2.setStellung(null);

    // Skript-Variablen besetzen
    this.scriptEngine.put("gleis406", this.gleis406);
    this.scriptEngine.put("signalSbk2", this.signalSbk2);

    // Skript ausfuehren
    loadScript("scripts/blockstelle");

    System.out.println("gleis406: " + this.gleis406);
    System.out.println("signalSbk2: " + this.signalSbk2);

    assertThat("Signalstellung falsch", this.signalSbk2.getStellung(), is(stellung));
  }

  @Test
  public void testJavaClassUsage() throws Exception {
    System.out.printf("----- %s - testJavaClassUsage -----\n", this.engineName);

    // Script compilieren
    loadScript("scripts/fillList");

    Object list = this.scriptEngine.get("list");
    assertTrue("Listen-Klasse falsch", list.getClass() == ArrayList.class);

    ((List<?>) list).forEach(System.out::println);

    assertThat("Listen-Länge falsch", ((List<?>) list).size(), is(3));
  }

}