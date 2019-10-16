package io.github.henryssondaniel.teacup.engine;

/**
 * Checked exception used in {@link Teacup}.
 *
 * @since 1.0
 */
public class TeacupException extends Exception {
  private static final long serialVersionUID = 1270522151807014116L;

  TeacupException(String message) {
    super(message);
  }
}
