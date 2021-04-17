package dev.thxwelchs.shorteningurl.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@EnableCaching
@Configuration
public class CacheConfiguration {

  private static final int CACHE_MAX_SIZE = 1000;
  private static final int CACHE_EXPIRES_AFTER_MINS = 10;

  @Bean
  @Primary
  public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    cacheManager.setCaffeine(Caffeine.newBuilder()
        .maximumSize(CACHE_MAX_SIZE)
        .expireAfterAccess(CACHE_EXPIRES_AFTER_MINS, TimeUnit.MINUTES));
    return cacheManager;
  }
}
