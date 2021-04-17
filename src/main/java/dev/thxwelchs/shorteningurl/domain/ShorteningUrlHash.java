package dev.thxwelchs.shorteningurl.domain;

import java.util.Objects;

public class ShorteningUrlHash {
  private static final int MAXIMUM_HASH_LENGTH = 8;
  private String hash;

  public ShorteningUrlHash(String hash) {
    this.hash = format(hash);
  }

  private String format(String hash) {
    if(isLessThanMaximumHashLength(hash)) return hash;

    return hash.substring(0, MAXIMUM_HASH_LENGTH);
  }

  private boolean isLessThanMaximumHashLength(String hash) {
    return hash.length() <= MAXIMUM_HASH_LENGTH;
  }

  public String getHash() {
    return hash;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShorteningUrlHash that = (ShorteningUrlHash) o;
    return Objects.equals(hash, that.hash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hash);
  }
}
