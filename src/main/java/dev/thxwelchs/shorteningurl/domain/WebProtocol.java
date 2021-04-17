package dev.thxwelchs.shorteningurl.domain;

import java.util.Arrays;

public enum WebProtocol {
  HTTP("http", 80), HTTPS("https", 443), NOT("", -1);

  private String protocol;
  private int port;

  private WebProtocol(String protocol, int port) {
    this.protocol = protocol;
    this.port = port;
  }

  public static WebProtocol select(String protocol, int port) {
    return Arrays.stream(WebProtocol.values())
        .filter((webProtocol) -> webProtocol.protocol.equals(protocol) && webProtocol.port == port)
        .findFirst().orElse(NOT);
  }
}
