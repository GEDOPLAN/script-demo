package de.gedoplan.showcase.benchmark;

import de.gedoplan.baselibs.utils.util.ResourceUtil;
import de.gedoplan.showcase.Factorial;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Random;
import java.util.function.Supplier;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class FactorialExecutor {
  @Param({ "js", "groovy", "python", "none" })
  public String engineName;

  private ScriptEngine scriptEngine;

  private Supplier<Number> factorialSupplier;

  private Random random;

  @Setup(Level.Trial)
  public void setup() throws Exception {
    System.out.println("\nSetting up benchmark for " + this.engineName);

    if (this.engineName.equals("none")) {
      this.factorialSupplier = this::execJavaFunction;

    } else {
      this.scriptEngine = new ScriptEngineManager().getEngineByName(this.engineName);

      String resourceName = "scripts/factorial." + this.engineName;
      try (
          InputStream resourceAsStream = ResourceUtil.getResourceAsStream(resourceName);
          Reader reader = new InputStreamReader(resourceAsStream);) {
        this.scriptEngine.eval(reader);
      }

      this.factorialSupplier = this::execScriptFunction;
    }

    this.random = new Random(0);
  }

  public Number factorial() {
    return this.factorialSupplier.get();
  }

  private int getNextN() {
    return this.random.nextInt(50) + 100;
  }

  private Number execJavaFunction() {
    return Factorial.factorial(getNextN());
  }

  private Number execScriptFunction() {
    try {
      return (Number) ((Invocable) this.scriptEngine).invokeFunction("factorial", getNextN());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
