package de.gedoplan.showcase;

import java.util.List;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import org.junit.Test;

public class ScriptEngineManagerTest
{
  @Test
  public void showEngines() throws Exception
  {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    List<ScriptEngineFactory> engineFactories = scriptEngineManager.getEngineFactories();
    for (ScriptEngineFactory scriptEngineFactory : engineFactories)
    {
      System.out.printf("Script Engine: %s (%s)\n",
          scriptEngineFactory.getEngineName(),
          scriptEngineFactory.getEngineVersion());

      System.out.printf("  Language: %s (%s)\n",
          scriptEngineFactory.getLanguageName(),
          scriptEngineFactory.getLanguageVersion());

      for (String alias : scriptEngineFactory.getNames())
      {
        System.out.printf("  Alias: %s\n", alias);
      }
    }
  }
}
