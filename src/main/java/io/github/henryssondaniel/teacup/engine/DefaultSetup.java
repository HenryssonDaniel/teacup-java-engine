package io.github.henryssondaniel.teacup.engine;

import io.github.henryssondaniel.teacup.core.logging.Factory;
import io.github.henryssondaniel.teacup.protocol.Server;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default implementation of {@link Setup}.
 *
 * @since 1.0
 */
public abstract class DefaultSetup implements Setup {
  private static final Logger LOGGER = Factory.getLogger(DefaultSetup.class);
  private static final String LOG_GET = "Returning the {0}";
  private static final String LOG_PUT = "Adding the {0}: {1}";

  private final Map<String, Object> clients = new HashMap<>(0);
  private final Map<String, Server> servers = new HashMap<>(0);

  @Override
  public Map<String, Object> getClients() {
    LOGGER.log(Level.FINE, LOG_GET, "clients");
    return new HashMap<>(clients);
  }

  @Override
  public Map<String, Server> getServers() {
    LOGGER.log(Level.FINE, LOG_GET, "servers");
    return new HashMap<>(servers);
  }

  @Override
  public Object putClient(String name, Object client) {
    LOGGER.log(Level.FINE, LOG_PUT, new Object[] {"client", name});
    return clients.put(name, client);
  }

  @Override
  public Server putServer(String name, Server server) {
    LOGGER.log(Level.FINE, LOG_PUT, new Object[] {"server", name});
    return servers.put(name, server);
  }
}
