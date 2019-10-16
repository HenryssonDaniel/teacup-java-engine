package io.github.henryssondaniel.teacup.engine;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

class ExecutorTest {
  @Test
  void executeFixture() {
    var executor = ExecutorFactory.create();
    executor.executeFixture(TestSetup.class.getAnnotation(Fixture.class));

    var setup = executor.getCurrentSetup().orElseThrow();

    var clients = setup.getClients();
    assertThat(clients).containsKey(Constants.CLIENT_NAME);
    assertThat(clients).containsValues(Constants.CLIENT);
    assertThat(clients).hasSize(1);

    var servers = setup.getServers();
    var testServer = (TestServer) servers.get(Constants.SERVER_NAME);

    assertThat(servers).containsKey(Constants.SERVER_NAME);
    assertThat(servers).containsValue(testServer);
    assertThat(servers).hasSize(1);

    assertThat(testServer.isSetUp()).isTrue();
    assertThat(testServer.isTearDown()).isFalse();

    executor.executeFixture(null);
    assertThat(executor.getCurrentSetup()).isEmpty();
    assertThat(testServer.isTearDown()).isTrue();

    executor.executeFixture(PrivateSetup.class.getAnnotation(Fixture.class));
    assertThat(executor.getCurrentSetup()).isEmpty();

    executor.executeFixture(ConstructorSetup.class.getAnnotation(Fixture.class));
    assertThat(executor.getCurrentSetup()).isEmpty();
  }

  @Fixture(PrivateSetup.class)
  private static final class PrivateSetup extends DefaultSetup {
    private static final Logger LOGGER = Logger.getLogger(PrivateSetup.class.getName());

    @Override
    public void initialize() {
      LOGGER.log(Level.FINE, "Initialize");
    }
  }
}
