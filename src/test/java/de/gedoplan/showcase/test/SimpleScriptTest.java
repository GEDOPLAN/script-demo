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
    System.out.printf("----- %s - testSimpleInlineScript -----\n", this.scriptFileExt);

    switch (this.scriptFileExt) {
    case "js" -> this.scriptEngine.eval("print('Hello, JavaScript!');");
    case "groovy" -> this.scriptEngine.eval("println(\"Hello, Groovy!\");");
    case "python" -> this.scriptEngine.eval("print('Hello, Python')");
    default -> assumeTrue("No inline script found for " + this.scriptFileExt, false);
    }
  }

  @Test
  public void testSimpleFileScript() throws Exception {
    System.out.printf("----- %s - testSimpleFileScript -----\n", this.scriptFileExt);

    loadScript("scripts/hello");
  }
}