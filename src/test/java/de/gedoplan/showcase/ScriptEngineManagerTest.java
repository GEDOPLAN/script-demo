package de.gedoplan.showcase;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import org.junit.Test;

public class ScriptEngineManagerTest {
  @Test
  public void showEngines() throws Exception {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    scriptEngineManager.getEngineFactories().forEach(ScriptEngineManagerTest::showEngine);
  }

  private static void showEngine(ScriptEngineFactory sef) {
    System.out.printf("Script Engine: %s (%s)\n", sef.getEngineName(), sef.getEngineVersion());
    System.out.printf("  Language: %s (%s)\n", sef.getLanguageName(), sef.getLanguageVersion());
    sef.getNames().forEach(n -> System.out.printf("  Name: %s\n", n));
  }
}
