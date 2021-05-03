package de.gedoplan.showcase.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

public class SciptEngineBenchmark {

  @Benchmark
  @Fork(1)
  @Warmup(iterations = 1, time = 5)
  @Measurement(iterations = 3, time = 5)
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public void factorial(FactorialExecutor executor, Blackhole blackhole) throws Exception {
    blackhole.consume(executor.factorial());
  }

  public static void main(String[] args) throws Exception {
    org.openjdk.jmh.Main.main(args);
  }

}
