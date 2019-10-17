package io.github.henryssondaniel.teacup.engine;

import io.github.henryssondaniel.teacup.protocol.Server;
import java.util.logging.Level;
import java.util.logging.Logger;

@Fixture(TestSetup.class)
public class TestSetup extends DefaultSetup {
  private static final Logger LOGGER = Logger.getLogger(TestSetup.class.getName());

  private final Server server = new TestServerImpl();

  @Override
  public void initialize() {
    LOGGER.log(Level.FINE, "Initialize");
    putClient(Constants.CLIENT_NAME, Constants.CLIENT);
    putServer(Constants.SERVER_NAME, server);
  }
}
