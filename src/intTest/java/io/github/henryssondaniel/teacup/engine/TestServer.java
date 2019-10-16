package io.github.henryssondaniel.teacup.engine;

import io.github.henryssondaniel.teacup.core.Server;

interface TestServer extends Server {
  boolean isSetUp();

  boolean isTearDown();
}
