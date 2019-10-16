package io.github.henryssondaniel.teacup.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class DefaultExecutorTest {
  private final Fixture fixture = mock(Fixture.class);

  @Test
  void executeClassWithFixtureAndNoPublicClass()
      throws IllegalAccessException, NoSuchFieldException {
    doReturn(Setup.class).when(fixture).value();

    Executor executor = new DefaultExecutor(null);
    executor.executeFixture(fixture);

    assertThat(getCurrentSetup(executor)).isNull();
  }

  @Test
  void executeClassWithFixtureAndNoPublicConstructor()
      throws IllegalAccessException, NoSuchFieldException {
    doReturn(DefaultSetup.class).when(fixture).value();

    Executor executor = new DefaultExecutor(null);
    executor.executeFixture(fixture);

    assertThat(getCurrentSetup(executor)).isNull();
  }

  @Test
  void executeClassWithFixtureDifferentAsCurrent()
      throws IllegalAccessException, NoSuchFieldException {
    doReturn(TestSetup.class).when(fixture).value();

    Executor executor = new DefaultExecutor(new TestSubSetup());
    executor.executeFixture(fixture);

    assertThat(getCurrentSetup(executor)).isExactlyInstanceOf(TestSetup.class);
  }

  @Test
  void executeClassWithFixtureSameAsCurrent() throws IllegalAccessException, NoSuchFieldException {
    doReturn(TestSetup.class).when(fixture).value();

    Executor executor = new DefaultExecutor(new TestSetup());
    executor.executeFixture(fixture);

    assertThat(getCurrentSetup(executor)).isExactlyInstanceOf(TestSetup.class);
  }

  @Test
  void executeClassWithoutFixtureWhenCurrent() throws IllegalAccessException, NoSuchFieldException {
    var setup = mock(Setup.class);

    Executor executor = new DefaultExecutor(setup);
    executor.executeFixture(null);

    verify(setup).getServers();
    assertThat(getCurrentSetup(executor)).isNull();
  }

  @Test
  void executeFixtureWithoutFixture() throws IllegalAccessException, NoSuchFieldException {
    Executor executor = new DefaultExecutor(null);
    executor.executeFixture(null);

    assertThat(getCurrentSetup(executor)).isNull();
  }

  @Test
  void getCurrentSetupWhenEmpty() {
    assertThat(new DefaultExecutor(null).getCurrentSetup()).isEmpty();
  }

  private static Object getCurrentSetup(Executor executor)
      throws IllegalAccessException, NoSuchFieldException {
    var field = DefaultExecutor.class.getDeclaredField("currentSetup");
    field.setAccessible(true);

    return field.get(executor);
  }

  private static final class TestSubSetup extends TestSetup {
    // Empty
  }
}
