package dev.thxwelchs.shorteningurl.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ShorteningUrlHashTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "asldkjfalsdkfjasldkfj",
      "iewfjoqweifjqiwoef123123",
      "12i37120938129038109238120938",
      "123718923719823123123129308",
      "12931820948129hasdfe8240918240",
  })
  void 해시문자열길이는8글자를넘어가지않는다(String hash) {
    //given, when
    ShorteningUrlHash shorteningUrlHash = new ShorteningUrlHash(hash);

    //then
    assertThat(shorteningUrlHash.getHash().length()).isLessThanOrEqualTo(8);
  }
}