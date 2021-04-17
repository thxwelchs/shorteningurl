package dev.thxwelchs.shorteningurl.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ShorteningURLEncoderTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "http://www.google.com",
      "URL이라고_가정하는URL",
      "이게URL인지아닌지는중요하지않고해시되는값이같은지보자"
  })
  void 인코딩된_해시값은_인풋이_같다면_언제나_같다(String url) {
    //given
    URLEncoder urlEncoder = new ShorteningURLEncoder();

    //when
    String hash1 = urlEncoder.encode(url);
    String hash2 = urlEncoder.encode(url);

    //then
    assertThat(hash1).isEqualTo(hash2);
  }
}