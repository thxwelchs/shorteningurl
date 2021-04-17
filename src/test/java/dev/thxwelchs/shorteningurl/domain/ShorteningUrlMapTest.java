package dev.thxwelchs.shorteningurl.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ShorteningUrlMapTest {

  @ParameterizedTest
  @CsvSource({
      "'12341234', 5000",
      "'ABCDEFGH', 9999",
      "'eifjoqjfioq', 1000"
  })
  void 축약된_URLHash기준으로_업데이트한만큼_카운팅된다(String hash, int count) {
    // given
    ShorteningUrlMap shorteningUrlMap = new ShorteningUrlMap();
    ShorteningUrlHash shorteningUrlHash = new ShorteningUrlHash(hash);

    //when then
    assertThat(shorteningUrlMap.count(shorteningUrlHash)).isEqualTo(0);
    for(int i = 0; i < count; i++)
      shorteningUrlMap.update(shorteningUrlHash);

    assertThat(shorteningUrlMap.count(shorteningUrlHash)).isEqualTo(count);
  }

  @ParameterizedTest
  @CsvSource({
      "'https://google.com', 'http://localhost/12341234'",
      "'http://naver.com', 'http://localhost/abcdefg'",
      "'http://localhost.com', 'http://localhost/abcdefg'",
  })
  void 축약된_URLHash기준으로_오리지널URL을_가진다(String originalURL, String shorteningURL)
      throws MalformedURLException {
    ShorteningUrlMap shorteningUrlMap = new ShorteningUrlMap();
    ShorteningURL shorteningURL1 = ShorteningURL.of(originalURL, shorteningURL, new ShorteningURLEncoder());

    shorteningUrlMap.insert(shorteningURL1);

    assertThat(shorteningUrlMap.getOriginUrl(shorteningURL1.getShorteningUrlHash())).isEqualTo(new URL(originalURL));
  }

}