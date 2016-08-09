package de.gedoplan.showcase;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import org.junit.Test;

public class ScriptEngineManagerTest {
  @Test
  public void showEngines() throws Exception {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    for (ScriptEngineFactory scriptEngineFactory : scriptEngineManager.getEngineFactories()) {

      System.out.printf("Script Engine: %s (%s)\n", scriptEngineFactory.getEngineName(), scriptEngineFactory.getEngineVersion());
      System.out.printf("  Language: %s (%s)\n", scriptEngineFactory.getLanguageName(), scriptEngineFactory.getLanguageVersion());

      for (String alias : scriptEngineFactory.getNames()) {
        System.out.printf("  Alias: %s\n", alias);
      }
    }
  }
}
