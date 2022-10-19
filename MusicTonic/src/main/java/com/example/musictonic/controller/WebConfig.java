package com.example.musictonic.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 *
 * We need to enable Cross Origin Resources to allow our DB to work with the deployed Heroku application
 * Src for the below: https://www.baeldung.com/spring-cors
 *
 *
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}