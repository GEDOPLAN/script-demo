package de.gedoplan.showcase.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

public class BindingTest extends ScriptTestBase {

  public BindingTest() {
    super("groovy");
  }

  @Test
  public void testEngineScope() throws Exception {
    System.out.printf("----- %s - testEngineScope -----\n", this.engineName);

    // Skript-Variablen besetzen
    this.scriptEngine.put("netto", 100);
    this.scriptEngine.put("steuersatz", 0.19);

    // Skript ausfuehren
    this.scriptEngine.eval("brutto = netto * (1+steuersatz)");

    // Skript-Variablen auslesen
    double brutto = (Double) this.scriptEngine.get("brutto");

    System.out.println("brutto: " + brutto);

    assertThat(brutto, is(119.0));
  }

  @BeforeClass
  public static void setGlobalBinding() {
    scriptEngineManager.getBindings().put("steuersatz", 0.07);
  }

  @Test
  public void testManagerScope() throws Exception {
    System.out.printf("----- %s - testManagerScope -----\n", this.engineName);

    // Skript-Variablen besetzen
    this.scriptEngine.put("netto", 100);
    // steuersatz ist bereits im Engine Scope

    // Skript ausfuehren
    this.scriptEngine.eval("brutto = netto * (1+steuersatz)");

    // Skript-Variablen auslesen
    double brutto = (Double) this.scriptEngine.get("brutto");

    System.out.println("brutto: " + brutto);

    assertThat(brutto, is(107.0));
  }

}