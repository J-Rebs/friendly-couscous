package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class for web config.
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  /**
   * Function to add a mapping to CorsRegistry.
   *
   * @param registry - the registration
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("/**").allowedMethods("GET", "POST", "PUT", "DELETE");
  }
}
