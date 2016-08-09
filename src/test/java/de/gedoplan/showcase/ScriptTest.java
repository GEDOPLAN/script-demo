package de.gedoplan.showcase;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import de.gedoplan.baselibs.utils.util.ResourceUtil;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ScriptTest
{
  @Parameters
  public static String[] getParameters()
  {
    return ENGINE_NAMES;
  }

  private String                engineName;
  private static final String[] ENGINE_NAMES = { "js", "groovy" };

  private ScriptEngine          scriptEngine;

  public ScriptTest(String engineName)
  {
    this.engineName = engineName;
  }

  @Before
  public void before()
  {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    this.scriptEngine = scriptEngineManager.getEngineByName(this.engineName);
    assertNotNull("Script engine not found", this.scriptEngine);
  }

  @Test
  public void testSimpleInlineScript() throws Exception
  {
    System.out.printf("----- %s - testSimpleInlineScript -----\n", this.engineName);

    assumeThat(this.engineName, is("js"));
    this.scriptEngine.eval("print('Hello, JavaScript!');");
  }

  @Test
  public void testSimpleFileScript() throws Exception
  {
    System.out.printf("----- %s - testSimpleFileScript -----\n", this.engineName);

    loadScript("scripts/hello");
  }

  private void loadScript(String name) throws Exception
  {
    String resourceName = name + "." + this.engineName;
    InputStream resourceAsStream = ResourceUtil.getResourceAsStream(resourceName);
    assumeFalse("Script not found: " + resourceName, resourceAsStream == null);

    this.scriptEngine.eval(new InputStreamReader(resourceAsStream));
  }

  @Test
  public void testGlobalVariables() throws Exception
  {
    System.out.printf("----- %s - testGlobalVariables -----\n", this.engineName);

    // Script-Variablen besetzen
    this.scriptEngine.put("netto", 100);
    this.scriptEngine.put("steuersatz", 0.19);

    // Script ausfuehren
    this.scriptEngine.eval("brutto = netto * (1+steuersatz)");

    // Script-Variablen auslesen
    Object brutto = this.scriptEngine.get("brutto");

    System.out.println("brutto: " + brutto);

    assertThat(brutto, is(119.0));
  }

  @Test
  public void testInvocableFunction() throws Exception
  {
    System.out.printf("----- %s - testInvocableFunction -----\n", this.engineName);

    // Script compilieren
    loadScript("scripts/factorial");

    Invocable invocable = (Invocable) this.scriptEngine;

    // Funktion aufrufen
    Object fac5 = invocable.invokeFunction("factorial", 5);

    System.out.println("factorial(5): " + fac5);

    assertTrue(fac5 instanceof Number);
    assertThat(((Number) fac5).doubleValue(), is(120.0));
  }

  @Test
  public void testJavaObjectAccess() throws Exception
  {
    System.out.printf("----- %s - testJavaObjectAccess -----\n", this.engineName);

    // Script compilieren
    loadScript("scripts/showDate");

    Invocable invocable = (Invocable) this.scriptEngine;

    // Funktion aufrufen
    invocable.invokeFunction("showDate", Calendar.getInstance());
  }
}
