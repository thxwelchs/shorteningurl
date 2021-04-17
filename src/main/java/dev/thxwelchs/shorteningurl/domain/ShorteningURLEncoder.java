package dev.thxwelchs.shorteningurl.domain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class ShorteningURLEncoder implements URLEncoder {

  private static final String HASH_ALGORITHM = "SHA-256";

  private byte[] hashURL(String url) {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance(HASH_ALGORITHM);
    } catch (NoSuchAlgorithmException e) {}

    md.update(url.getBytes(StandardCharsets.UTF_8));

    return md.digest();
  }

  @Override
  public String encode(String url) {
    StringBuilder sb = new StringBuilder();
    byte[] hashedBytes = hashURL(url);
    for (byte hashedByte : hashedBytes) {
      String hex = Integer.toHexString(0xff & hashedByte);
      sb.append(hex);
    }
    return sb.toString();
  }
}
