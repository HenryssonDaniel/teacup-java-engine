package io.github.henryssondaniel.teacup.engine;

import io.github.henryssondaniel.teacup.core.logging.Factory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory class to create an instance of {@link Executor}.
 *
 * @since 1.0
 */
public enum ExecutorFactory {
  ;

  private static final Logger LOGGER = Factory.getLogger(ExecutorFactory.class);

  /**
   * Creates an instance of {@link Executor}. This executor has support for one active fixture at
   * the time. If the fixture is null, the current fixture, if any, will be torn down. If the
   * fixture is the same as the current fixture, nothing will happen and in all other cases the
   * current fixture, if any, will be torn down and the new fixture will be set up.
   *
   * @return the executor
   * @since 1.0
   */
  public static Executor create() {
    LOGGER.log(Level.FINE, "Creating a new default executor.");
    return new DefaultExecutor(null);
  }
}
