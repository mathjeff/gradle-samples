package example;

import org.junit.Test;

public final class SampleTest {

  @Test
  public void testMe() throws IllegalArgumentException {
    throw new IllegalArgumentException("Test is configured to fail!");
  }

}
