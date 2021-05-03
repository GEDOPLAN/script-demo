package de.gedoplan.showcase.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeFalse;

import de.gedoplan.baselibs.utils.util.ResourceUtil;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized.Parameters;

public abstract class ScriptTestBase {
  @Parameters
  public static List<String> getParameters() {
    return List.of("js", "groovy", "python");
  }

  protected static ScriptEngineManager scriptEngineManager;
  protected String engineName;
  protected ScriptEngine scriptEngine;

  protected ScriptTestBase(String engineName) {
    this.engineName = engineName;
  }

  @BeforeClass
  public static void beforeClass() {
    scriptEngineManager = new ScriptEngineManager();
  }

  @Before
  public void before() {
    this.scriptEngine = scriptEngineManager.getEngineByName(this.engineName);
    assertNotNull("Script engine not found", this.scriptEngine);
  }

  protected void loadScript(String name) throws Exception {
    String resourceName = name + "." + this.engineName;
    InputStream resourceAsStream = ResourceUtil.getResourceAsStream(resourceName);
    assumeFalse("Script not found: " + resourceName, resourceAsStream == null);

    try (Reader reader = new InputStreamReader(resourceAsStream)) {
      this.scriptEngine.eval(reader);
    }
  }

}