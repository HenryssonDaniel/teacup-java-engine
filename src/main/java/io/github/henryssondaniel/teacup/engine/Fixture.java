package io.github.henryssondaniel.teacup.engine;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A fixture is used to set up the system for the testing process.
 *
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface Fixture {
  /**
   * Sets the {@link Setup} class belonging to this fixture.
   *
   * @return the value
   * @since 1.0
   */
  Class<? extends Setup> value();
}
