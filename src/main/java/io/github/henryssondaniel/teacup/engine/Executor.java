package io.github.henryssondaniel.teacup.engine;

import java.util.Optional;

/**
 * Interface to handle execution.
 *
 * @since 1.0
 */
public interface Executor {
  /**
   * The method executes a fixture. The logic for when the fixture should be tear down or set up is
   * decided here. It is also decided how the current fixture/fixtures are going to be tear down and
   * set up.
   *
   * @param fixture the fixture
   * @since 1.0
   */
  void executeFixture(Fixture fixture);

  /**
   * Returns the current setup, if any.
   *
   * @return the current {@link Setup}
   * @since 1.0
   */
  Optional<Setup> getCurrentSetup();
}
