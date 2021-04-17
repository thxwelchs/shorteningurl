package dev.thxwelchs.shorteningurl.domain;

public class UnRegistrationUrlException extends RuntimeException {
  private ShorteningUrlHash hash;

  public UnRegistrationUrlException(ShorteningUrlHash hash) {
    super(String.format("%s는 아직 등록되지 않은 url hash입니다.", hash.getHash()));
    this.hash = hash;
  }
}
