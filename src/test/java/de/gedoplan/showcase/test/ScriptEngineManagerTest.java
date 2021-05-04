package de.gedoplan.showcase.test;

import java.util.stream.Collectors;

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
    System.out.printf("""
      Script Engine: %s (%s)
        Language: %s (%s)
        Names: %s
        Extensions: %s
      """,
      sef.getEngineName(),
      sef.getEngineVersion(),
      sef.getLanguageName(),
      sef.getLanguageVersion(),
      sef.getNames().stream().collect(Collectors.joining(" ")),
      sef.getExtensions().stream().collect(Collectors.joining(" ")));
  }
}
