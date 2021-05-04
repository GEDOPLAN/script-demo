package de.gedoplan.showcase.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import de.gedoplan.showcase.Gleis;
import de.gedoplan.showcase.Signal;
import de.gedoplan.showcase.SignalStellung;

import java.util.ArrayList;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptException;

import org.junit.Test;

public class JavaApiTest extends ScriptTestBase {

  private Gleis gleis406 = new Gleis("406");
  private Signal signalSbk2 = new Signal("Sbk2");

  public JavaApiTest() {
    super("groovy");
  }

  @Test
  public void testFrei() throws Exception {
    System.out.printf("----- %s - testFrei -----\n", this.scriptFileExt);

    doTest(false, SignalStellung.FAHRT);
  }

  @Test
  public void testBesetzt() throws Exception {
    System.out.printf("----- %s - testBesetzt -----\n", this.scriptFileExt);

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
    System.out.printf("----- %s - testJavaClassUsage -----\n", this.scriptFileExt);

    // Script compilieren
    loadScript("scripts/fillList");

    Object list = this.scriptEngine.get("list");
    assertTrue("Listen-Klasse falsch", list.getClass() == ArrayList.class);

    ((List<?>) list).forEach(System.out::println);

    assertThat("Listen-Länge falsch", ((List<?>) list).size(), is(3));
  }

  @Test
  public void testThrowException() throws Exception {
    System.out.printf("----- %s - testThrowException -----\n", this.scriptFileExt);

    // Script compilieren
    loadScript("scripts/factorial");

    Invocable invocable = (Invocable) this.scriptEngine;
    try {
      invocable.invokeFunction("factorial", 0);
    } catch (IllegalArgumentException e) {
      return;
    } catch (ScriptException e) {
      if (e.getCause() instanceof IllegalArgumentException) {
        return;
      }
    }

    fail("Invocation should throw an IllegalArgumentException");
  }

}