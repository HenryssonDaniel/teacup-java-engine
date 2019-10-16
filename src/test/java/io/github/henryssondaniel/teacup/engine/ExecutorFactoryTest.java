package io.github.henryssondaniel.teacup.engine;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ExecutorFactoryTest {
  @Test
  void create() {
    assertThat(ExecutorFactory.create()).isExactlyInstanceOf(DefaultExecutor.class);
  }
}
