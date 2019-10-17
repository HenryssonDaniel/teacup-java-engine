package io.github.henryssondaniel.teacup.engine;

import io.github.henryssondaniel.teacup.protocol.Server;

interface TestServer extends Server {
  boolean isSetUp();

  boolean isTearDown();
}
