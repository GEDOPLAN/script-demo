package de.gedoplan.showcase.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.script.Invocable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class InvocableTest extends ScriptTestBase {

  public InvocableTest(String engineName) {
    super(engineName);
  }

  @Test
  public void testInvocable() throws Exception {
    System.out.printf("----- %s - testInvocable -----\n", this.scriptFileExt);

    // Script compilieren
    loadScript("scripts/factorial");

    Invocable invocable = (Invocable) this.scriptEngine;

    // Funktion aufrufen
    Object fac5 = invocable.invokeFunction("factorial", 5);

    System.out.println("factorial(5): " + fac5);

    assertTrue(fac5 instanceof Number);
    assertThat(((Number) fac5).doubleValue(), is(120.0));
  }

}