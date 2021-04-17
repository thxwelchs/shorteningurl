package dev.thxwelchs.shorteningurl.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ShorteningURLTest {

  @Test
  void 생성된_두_축약URL은_같다() {
    //given
    String localUrl = "http://localhost";
    //when
    ShorteningURL shorteningURL1 = ShorteningURL.of("http://google.com", localUrl, new ShorteningURLEncoder());
    ShorteningURL shorteningURL2 = ShorteningURL.of("http://google.com", localUrl, new ShorteningURLEncoder());
    //then
    assertThat(shorteningURL1).isEqualTo(shorteningURL2);
  }

  @ParameterizedTest
  @CsvSource({
      "'httpss://google.com', 'http://localhost/12341234'",
      "'httpss://naver.com', 'http://localhost/abcdefg'",
      "'URL아님', 'http://localhost/abcdefg'",
      "'httpss://naver.com', 'URL아님'",
      "'123123://naver.com', 'tdd://localhost/abcdefg'",
  })
  void 축약전_또는_축약후URL이_URL형식에_맞지_않다면_예외가_발생한다(String originalURL, String shorteningURL) {
    //given, when, then
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> ShorteningURL.of(originalURL, shorteningURL, new ShorteningURLEncoder()));
    assertThat(exception.getMessage()).isEqualTo("잘못된 URL 형식입니다.");
  }
}