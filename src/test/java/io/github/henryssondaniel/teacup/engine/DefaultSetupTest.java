package io.github.henryssondaniel.teacup.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import io.github.henryssondaniel.teacup.protocol.Server;
import org.junit.jupiter.api.Test;

class DefaultSetupTest {
  private static final String NAME = "name";

  private final Setup setup = new TestSetup();

  @Test
  void getClients() {
    assertThat(setup.getClients()).isEmpty();
  }

  @Test
  void getServers() {
    assertThat(setup.getServers()).isEmpty();
  }

  @Test
  void putAndGetClient() {
    var client = "";
    assertThat(setup.putClient(NAME, client)).isNull();

    var clients = setup.getClients();

    assertThat(clients).containsOnlyKeys(NAME);
    assertThat(clients).containsValue(client);
  }

  @Test
  void putAndGetServer() {
    var server = mock(Server.class);
    assertThat(setup.putServer(NAME, server)).isNull();

    var servers = setup.getServers();

    assertThat(servers).containsOnlyKeys(NAME);
    assertThat(servers).containsValue(server);
  }
}
