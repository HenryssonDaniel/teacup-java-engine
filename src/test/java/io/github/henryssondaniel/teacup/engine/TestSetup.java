package io.github.henryssondaniel.teacup.engine;

import java.util.logging.Level;
import java.util.logging.Logger;

/** Test setup class to be used as an test implementation of {@link DefaultSetup}. */
public class TestSetup extends DefaultSetup {
  private static final Logger LOGGER = Logger.getLogger(TestSetup.class.getName());

  @Override
  public void initialize() {
    LOGGER.log(Level.FINE, "initialize");
  }
}
