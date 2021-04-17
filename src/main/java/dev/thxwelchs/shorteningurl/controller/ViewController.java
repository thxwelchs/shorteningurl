package dev.thxwelchs.shorteningurl.controller;

import dev.thxwelchs.shorteningurl.service.ShorteningUrlService;
import java.net.URL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ViewController {
  private final ShorteningUrlService shorteningUrlService;

  public ViewController(ShorteningUrlService shorteningUrlService) {
    this.shorteningUrlService = shorteningUrlService;
  }

  @GetMapping("{shorteningUrlHash}")
  public ModelAndView main(@PathVariable String shorteningUrlHash) {
    URL originUrl = shorteningUrlService.unshorten(shorteningUrlHash);
    return new ModelAndView("redirect:" + originUrl.toString(), HttpStatus.FOUND);
  }
}
