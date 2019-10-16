package io.github.henryssondaniel.teacup.engine;

import java.util.logging.Level;
import java.util.logging.Logger;

@Fixture(ConstructorSetup.class)
public class ConstructorSetup extends DefaultSetup {
  private static final Logger LOGGER = Logger.getLogger(ConstructorSetup.class.getName());
  private final String message;

  public ConstructorSetup(String message) {
    this.message = message;
  }

  @Override
  public void initialize() {
    LOGGER.log(Level.FINE, message);
  }
}
