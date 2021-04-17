package dev.thxwelchs.shorteningurl.service;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import dev.thxwelchs.shorteningurl.config.ShorteningURLConfiguration;
import dev.thxwelchs.shorteningurl.domain.ShorteningURLEncoder;
import dev.thxwelchs.shorteningurl.domain.ShorteningUrlHash;
import dev.thxwelchs.shorteningurl.domain.ShorteningUrlMap;
import dev.thxwelchs.shorteningurl.domain.ShorteningUrlResponse;
import dev.thxwelchs.shorteningurl.domain.URLEncoder;
import dev.thxwelchs.shorteningurl.domain.UnRegistrationUrlException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ShorteningUrlServiceTest {
  @Mock
  private ShorteningURLConfiguration shorteningURLConfiguration;
  @Spy
  private ShorteningUrlMap shorteningUrlMap = new ShorteningUrlMap();
  @Spy
  private URLEncoder shorteningURLEncoder = new ShorteningURLEncoder();

  @InjectMocks
  private ShorteningUrlService shorteningUrlService;

  @BeforeEach
  void setDummy() {
    shorteningUrlMap = new ShorteningUrlMap();
    when(shorteningURLConfiguration.getFullShortenedURL())
        .thenReturn("http://localhost");
    List<String> originalUrls = asList("https://a.com", "https://b.com", "https://c.com");
    originalUrls.forEach(shorteningUrlService::shorten);
  }

  @ParameterizedTest
  @ValueSource(ints = {
      1, 10, 50, 999, 1000, 2000, 3000
  })
  void 축약된URL과요청카운트를반환한다(int count) throws MalformedURLException {
    String originalUrl = "https://www.google.com";
    for(int i = 1; i <= count; i++) {
      ShorteningUrlResponse shorteningUrlResponse = shorteningUrlService.shorten(originalUrl);
      assertThat(shorteningUrlResponse.getCount()).isEqualTo(i);
      assertThat(shorteningUrlResponse.getUrl().getOriginalURL()).isEqualTo(new URL(originalUrl));
    }
  }

  @ParameterizedTest
  @CsvSource({
      "'d6fd2a8e', 'https://b.com'",
      "'d149ea8c', 'https://c.com'",
      "'4b59642f', 'https://a.com'",
  })
  void 등록했던_URL_의_Hash로_조회하면_오리지널URL을반환한다(String hash, String expectedUrl) throws MalformedURLException {
    assertThat(shorteningUrlService.unshorten(hash)).isEqualTo(new URL(expectedUrl));
  }

  @Test
  void 한번도등록되지않은_URL을_조회하면_예외가발생한다() {
    String originalUrl = "https://d.com";
    ShorteningUrlHash shorteningUrlHash = new ShorteningUrlHash(shorteningURLEncoder.encode(originalUrl));
    assertThrows(UnRegistrationUrlException.class, () -> {
      shorteningUrlService.unshorten(shorteningUrlHash.getHash());
    });
  }
}