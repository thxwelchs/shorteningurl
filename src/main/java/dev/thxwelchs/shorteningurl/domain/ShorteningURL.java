package dev.thxwelchs.shorteningurl.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShorteningURL {

  private static final String PATH_DELIMITER = "/";
  private static final Map<ShorteningUrlHash, ShorteningURL> cache = new ConcurrentHashMap<>();

  private URL originalURL;
  private URL shortenedURL;
  @JsonIgnore
  private transient ShorteningUrlHash shorteningUrlHash;

  private ShorteningURL(String originalURL, String shorteningUrl,
      ShorteningUrlHash shorteningUrlHash) {
    try {
      this.originalURL = new URL(originalURL);
      this.shortenedURL = new URL(createFullShortedURL(shorteningUrl, shorteningUrlHash));
      this.shorteningUrlHash = shorteningUrlHash;
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException("잘못된 URL 형식입니다.");
    }
  }

  public URL getOriginalURL() {
    return originalURL;
  }

  public URL getShortenedURL() {
    return shortenedURL;
  }

  public ShorteningUrlHash getShorteningUrlHash() {
    return shorteningUrlHash;
  }

  private String createFullShortedURL(String shorteningUrl, ShorteningUrlHash shorteningUrlHash) {
    return shorteningUrl + PATH_DELIMITER + shorteningUrlHash.getHash();
  }

  public static ShorteningURL of(String originalURL, String shorteningUrl, URLEncoder encoder) {
    ShorteningUrlHash shortenedUrlHash = new ShorteningUrlHash(encoder.encode(originalURL));
    if (cache.containsKey(shortenedUrlHash)) {
      return cache.get(shortenedUrlHash);
    }
    ShorteningURL shorteningURL = new ShorteningURL(originalURL, shorteningUrl, shortenedUrlHash);
    cache.put(shortenedUrlHash, shorteningURL);
    return shorteningURL;
  }
}
