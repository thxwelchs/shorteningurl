package dev.thxwelchs.shorteningurl.controller;

import dev.thxwelchs.shorteningurl.domain.ShorteningUrlResponse;
import dev.thxwelchs.shorteningurl.service.ShorteningUrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shortening-urls")
public class ShorteningUrlController {
  private ShorteningUrlService shorteningUrlService;

  public ShorteningUrlController(ShorteningUrlService shorteningUrlService) {
    this.shorteningUrlService = shorteningUrlService;
  }

  @PostMapping
  public ResponseEntity<ShorteningUrlResponse> createShorteningURL(@RequestBody String url) {
    return ResponseEntity.ok(shorteningUrlService.shorten(url));
  }
}
