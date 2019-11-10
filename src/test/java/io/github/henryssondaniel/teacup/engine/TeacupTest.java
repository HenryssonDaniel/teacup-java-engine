package io.github.henryssondaniel.teacup.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.henryssondaniel.teacup.protocol.Server;
import io.github.henryssondaniel.teacup.protocol.server.Base;
import io.github.henryssondaniel.teacup.protocol.server.Handler;
import java.util.Collections;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeacupTest {
  private static final String CLIENT = "client";
  private static final String DIFFERENT_INSTANCE =
      "The name exists, but is of a different instance";
  private static final String NOT_EXIST = "The %s does not exist";
  private static final String NO_SETUP = "No setup exists";
  private static final String SERVER = "server";

  private final Executor executor = mock(Executor.class);
  private final Server<String, String> server = new TestServer();
  private final Setup setup = mock(Setup.class);

  @BeforeEach
  void beforeEach() {
    when(executor.getCurrentSetup()).thenReturn(Optional.of(setup));
  }

  @Test
  void getClient() throws TeacupException {
    when(setup.getClients()).thenReturn(Collections.singletonMap(CLIENT, new TeacupTest()));
    assertThat(Teacup.getClient(Object.class, executor, CLIENT))
        .isExactlyInstanceOf(TeacupTest.class);
  }

  @Test
  void getClientWhenClientNotExists() {
    assertThatExceptionOfType(TeacupException.class)
        .isThrownBy(() -> Teacup.getClient(String.class, executor, CLIENT))
        .withMessage(String.format(NOT_EXIST, "client"));
  }

  @Test
  void getClientWhenNotCorrectInstance() {
    when(setup.getClients()).thenReturn(Collections.singletonMap(CLIENT, new Object()));
    assertThatExceptionOfType(TeacupException.class)
        .isThrownBy(() -> Teacup.getClient(TeacupTest.class, executor, CLIENT))
        .withMessage(DIFFERENT_INSTANCE);
  }

  @Test
  void getClientWhenNotPresent() {
    when(executor.getCurrentSetup()).thenReturn(Optional.empty());
    assertThatExceptionOfType(TeacupException.class)
        .isThrownBy(() -> Teacup.getClient(String.class, executor, CLIENT))
        .withMessage(NO_SETUP);
  }

  @Test
  void getServer() throws TeacupException {
    when(setup.getServers()).thenReturn(Collections.singletonMap(SERVER, server));
    assertThat(Teacup.getServer(TestServer.class, executor, SERVER))
        .isExactlyInstanceOf(TestServer.class);
  }

  @Test
  void getServerWhenNotCorrectInstance() {
    when(setup.getServers()).thenReturn(Collections.singletonMap(SERVER, server));
    assertThatExceptionOfType(TeacupException.class)
        .isThrownBy(() -> Teacup.getServer(TestTestServer.class, executor, SERVER))
        .withMessage(DIFFERENT_INSTANCE);
  }

  @Test
  void getServerWhenNotPresent() {
    when(executor.getCurrentSetup()).thenReturn(Optional.empty());
    assertThatExceptionOfType(TeacupException.class)
        .isThrownBy(() -> Teacup.getServer(Server.class, executor, SERVER))
        .withMessage(NO_SETUP);
  }

  @Test
  void getServerWhenServerNotExists() {
    assertThatExceptionOfType(TeacupException.class)
        .isThrownBy(() -> Teacup.getServer(Server.class, executor, SERVER))
        .withMessage(String.format(NOT_EXIST, "server"));
  }

  private static class TestServer extends Base<String, String, String> {
    private static final Logger LOGGER = Logger.getLogger(TestServer.class.getName());

    @Override
    public void setUp() {
      LOGGER.log(Level.FINE, "Set up");
    }

    @Override
    public void tearDown() {
      LOGGER.log(Level.FINE, "Tear down");
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

  private static final class TestTestServer extends TestServer {
    // Empty
  }
}
