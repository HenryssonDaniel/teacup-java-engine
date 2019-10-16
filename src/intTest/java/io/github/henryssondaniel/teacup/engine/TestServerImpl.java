package io.github.henryssondaniel.teacup.engine;

import java.util.logging.Level;
import java.util.logging.Logger;

class TestServerImpl implements TestServer {
  private static final Logger LOGGER = Logger.getLogger(TestServerImpl.class.getName());

  private boolean setUp;
  private boolean tearDown;

  @Override
  public boolean isSetUp() {
    return setUp;
  }

  @Override
  public boolean isTearDown() {
    return tearDown;
  }

  @Override
  public void setUp() {
    LOGGER.log(Level.FINE, "Set up");
    setUp = true;
  }

  @Override
  public void tearDown() {
    LOGGER.log(Level.FINE, "Tear down");
    tearDown = true;
  }
}
