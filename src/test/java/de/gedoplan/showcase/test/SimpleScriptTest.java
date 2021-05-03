package de.gedoplan.showcase.test;

import static org.junit.Assume.assumeTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class SimpleScriptTest extends ScriptTestBase {

  public SimpleScriptTest(String engineName) {
    super(engineName);
  }

  @Test
  public void testSimpleInlineScript() throws Exception {
    System.out.printf("----- %s - testSimpleInlineScript -----\n", this.engineName);

    switch (this.engineName) {
    case "js" -> this.scriptEngine.eval("print('Hello, JavaScript!');");
    case "groovy" -> this.scriptEngine.eval("println(\"Hello, Groovy!\");");
    case "python" -> this.scriptEngine.eval("print('Hello, Python')");
    default -> assumeTrue("No inline script found for " + this.engineName, false);
    }
  }

  @Test
  public void testSimpleFileScript() throws Exception {
    System.out.printf("----- %s - testSimpleFileScript -----\n", this.engineName);

    loadScript("scripts/hello");
  }
}