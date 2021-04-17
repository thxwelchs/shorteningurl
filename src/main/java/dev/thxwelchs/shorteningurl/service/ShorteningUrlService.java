package dev.thxwelchs.shorteningurl.service;

import dev.thxwelchs.shorteningurl.config.ShorteningURLConfiguration;
import dev.thxwelchs.shorteningurl.domain.ShorteningURL;
import dev.thxwelchs.shorteningurl.domain.ShorteningURLEncoder;
import dev.thxwelchs.shorteningurl.domain.ShorteningUrlMap;
import dev.thxwelchs.shorteningurl.domain.ShorteningUrlHash;
import dev.thxwelchs.shorteningurl.domain.ShorteningUrlResponse;
import dev.thxwelchs.shorteningurl.domain.URLEncoder;
import dev.thxwelchs.shorteningurl.domain.UnRegistrationUrlException;
import java.net.URL;
import org.springframework.stereotype.Service;

@Service
public class ShorteningUrlService {
  private final ShorteningURLConfiguration shorteningURLConfiguration;
  private final ShorteningUrlMap shorteningUrlMap;
  private final URLEncoder shorteningURLEncoder;

  public ShorteningUrlService(
      ShorteningURLConfiguration shorteningURLConfiguration,
      URLEncoder shorteningURLEncoder,
      ShorteningUrlMap shorteningUrlMap) {
    this.shorteningURLConfiguration = shorteningURLConfiguration;
    this.shorteningURLEncoder = shorteningURLEncoder;
    this.shorteningUrlMap = shorteningUrlMap;
  }

  public URL unshorten(String shorteningUrlHash) {
    ShorteningUrlHash hash = new ShorteningUrlHash(shorteningUrlHash);
    URL unshortenUrl = shorteningUrlMap.getOriginUrl(hash);
    if(unshortenUrl == null) throw new UnRegistrationUrlException(hash);

    return unshortenUrl;
  }

  public ShorteningUrlResponse shorten(String originUrl) {
    ShorteningURL shorteningURL = ShorteningURL.of(originUrl, shorteningURLConfiguration.getFullShortenedURL(), shorteningURLEncoder);
    update(shorteningURL);
    return new ShorteningUrlResponse(shorteningURL, shorteningUrlMap.count(shorteningURL.getShorteningUrlHash()));
  }

  private void update(ShorteningURL shorteningURL) {
    shorteningUrlMap.insert(shorteningURL);
    shorteningUrlMap.update(shorteningURL.getShorteningUrlHash());
  }
}
