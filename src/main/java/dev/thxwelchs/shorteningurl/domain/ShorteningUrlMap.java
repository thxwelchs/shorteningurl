package dev.thxwelchs.shorteningurl.domain;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class ShorteningUrlMap {
  private Map<ShorteningUrlHash, Integer> countMap = new ConcurrentHashMap<>();
  private Map<ShorteningUrlHash, URL> originMap = new ConcurrentHashMap<>();


  public int count(ShorteningUrlHash shorteningUrlHash) {
    return countMap.getOrDefault(shorteningUrlHash, 0);
  }

  public URL getOriginUrl(ShorteningUrlHash shorteningUrlHash) {
    return originMap.get(shorteningUrlHash);
  }

  public void insert(ShorteningURL shorteningURL) {
    if(isExist(shorteningURL.getShorteningUrlHash())) return;
    originMap.put(shorteningURL.getShorteningUrlHash(), shorteningURL.getOriginalURL());
  }

  public void update(ShorteningUrlHash shorteningUrlHash) {
    countMap.put(shorteningUrlHash, count(shorteningUrlHash) + 1);
  }

  private boolean isExist(ShorteningUrlHash shorteningUrlHash) {
    return originMap.containsKey(shorteningUrlHash);
  }
}
