package io.github.henryssondaniel.teacup.engine;

import io.github.henryssondaniel.teacup.protocol.server.Base;
import io.github.henryssondaniel.teacup.protocol.server.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestServerImpl extends Base<String, String, String> implements TestServer {
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

  @Override
  protected String createProtocolContext(String context, Handler<String> handler) {
    return "protocolContext";
  }

  @Override
  protected String getKey(String context) {
    return "key";
  }

  @Override
  protected boolean isEquals(String context, String protocolContext) {
    return false;
  }

  @Override
  protected void serverCleanup(String protocolContext) {}
}
