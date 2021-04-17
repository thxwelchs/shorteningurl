package dev.thxwelchs.shorteningurl.domain;

public class ShorteningUrlResponse {
  private ShorteningURL url;
  private Integer count;

  public ShorteningUrlResponse(ShorteningURL url, Integer count) {
    this.url = url;
    this.count = count;
  }

  public ShorteningURL getUrl() {
    return url;
  }

  public Integer getCount() {
    return count;
  }
}
