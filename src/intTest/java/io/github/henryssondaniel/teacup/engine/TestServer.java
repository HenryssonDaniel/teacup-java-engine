package io.github.henryssondaniel.teacup.engine;

import io.github.henryssondaniel.teacup.protocol.Server;

interface TestServer extends Server<String, String> {
  boolean isSetUp();

  boolean isTearDown();
}
