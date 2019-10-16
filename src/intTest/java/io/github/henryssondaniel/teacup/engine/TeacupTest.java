package io.github.henryssondaniel.teacup.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import io.github.henryssondaniel.teacup.core.Server;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeacupTest {
  private final Executor executor = ExecutorFactory.create();

  @BeforeEach
  void beforeEach() {
    executor.executeFixture(TestSetup.class.getAnnotation(Fixture.class));
  }

  @Test
  void getClient() throws TeacupException {
    assertThat(Teacup.getClient(Object.class, executor, Constants.CLIENT_NAME))
        .isSameAs(Constants.CLIENT);
    assertThatExceptionOfType(TeacupException.class)
        .isThrownBy(() -> Teacup.getClient(Object.class, executor, Constants.SERVER_NAME));
    assertThatExceptionOfType(TeacupException.class)
        .isThrownBy(() -> Teacup.getClient(Integer.class, executor, Constants.CLIENT_NAME));
    assertThatExceptionOfType(TeacupException.class)
        .isThrownBy(
            () -> Teacup.getClient(Object.class, ExecutorFactory.create(), Constants.CLIENT_NAME));
  }

  @Test
  void getServer() throws TeacupException {
    assertThat(Teacup.getServer(TestServer.class, executor, Constants.SERVER_NAME))
        .isSameAs(executor.getCurrentSetup().orElseThrow().getServers().get(Constants.SERVER_NAME));
    assertThatExceptionOfType(TeacupException.class)
        .isThrownBy(() -> Teacup.getServer(TestServer.class, executor, Constants.CLIENT_NAME));
    assertThatExceptionOfType(TeacupException.class)
        .isThrownBy(() -> Teacup.getServer(ExtendedServer.class, executor, Constants.SERVER_NAME));
    assertThatExceptionOfType(TeacupException.class)
        .isThrownBy(
            () ->
                Teacup.getServer(
                    TestServer.class, ExecutorFactory.create(), Constants.SERVER_NAME));
  }

  private static final class ExtendedServer implements Server {
    private static final Logger LOGGER = Logger.getLogger(ExtendedServer.class.getName());

    @Override
    public void setUp() {
      LOGGER.log(Level.FINE, "Set up");
    }

    @Override
    public void tearDown() {
      LOGGER.log(Level.FINE, "Tear down");
    }
  }
}
