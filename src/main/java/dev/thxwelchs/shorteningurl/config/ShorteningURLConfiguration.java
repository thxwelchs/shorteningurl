package dev.thxwelchs.shorteningurl.config;

import dev.thxwelchs.shorteningurl.domain.WebProtocol;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "shortened-url")
public class ShorteningURLConfiguration {
  private static final String PROTOCOL_AND_HOST_DELIMITER = "://";
  private static final String HOST_AND_PORT_DELIMITER = ":";
  private String protocol;
  private String host;
  private int port;

  public String getFullShortenedURL() {
    return createFullShortenedURL();
  }

  private String createFullShortenedURL() {
    return protocol + PROTOCOL_AND_HOST_DELIMITER + host + getUrlFormatPort(protocol, port);
  }

  private String getUrlFormatPort(String protocol, int port) {
    if(isDefaultPort(protocol, port)) return "";

    return HOST_AND_PORT_DELIMITER + port;
  }

  private boolean isDefaultPort(String protocol, int port) {
    return WebProtocol.select(protocol, port) != WebProtocol.NOT;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setPort(int port) {
    this.port = port;
  }
}
