package io.github.henryssondaniel.teacup.engine;

import io.github.henryssondaniel.teacup.core.logging.Factory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

class DefaultExecutor implements Executor {
  private static final Logger LOGGER = Factory.getLogger(DefaultExecutor.class);

  private Setup currentSetup;

  DefaultExecutor(Setup currentSetup) {
    this.currentSetup = currentSetup;
  }

  @Override
  public void executeFixture(Fixture fixture) {
    if (fixture == null) {
      LOGGER.log(Level.FINE, "No new fixture, the old one will be torn down");
      tearDownFixture();
    } else execute(fixture);
  }

  @Override
  public Optional<Setup> getCurrentSetup() {
    LOGGER.log(Level.FINE, "Returns the current setup: {0}", currentSetup);
    return Optional.ofNullable(currentSetup);
  }

  private void execute(Fixture fixture) {
    LOGGER.log(Level.FINE, "Checking if the fixture should be executed");
    var value = fixture.value();

    if (currentSetup == null || value != currentSetup.getClass())
      executeSetup(value.getConstructors());
  }

  private void executeSetup(Constructor<?>... constructors) {
    LOGGER.log(Level.FINE, "Executing fixture");
    tearDownFixture();

    if (constructors.length == 0)
      LOGGER.log(Level.SEVERE, "The setup class has no public constructor");
    else setUpFixture(constructors[0]);
  }

  private void setUpFixture(Constructor<?> constructor) {
    try {
      currentSetup = (Setup) constructor.newInstance();
      currentSetup.initialize();
      currentSetup.getServers().forEach((name, server) -> server.setUp());
    } catch (IllegalAccessException
        | IllegalArgumentException
        | InstantiationException
        | InvocationTargetException
        | SecurityException e) {
      LOGGER.log(Level.SEVERE, "Could not instantiate the setup class", e);
    }
  }

  private void tearDownFixture() {
    if (currentSetup != null) {
      currentSetup.getServers().forEach((name, server) -> server.tearDown());
      currentSetup = null;
    }
  }
}
