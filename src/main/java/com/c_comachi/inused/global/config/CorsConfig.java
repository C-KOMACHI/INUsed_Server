package com.c_comachi.inused.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedOrigin("http://127.0.0.1:5173");
        config.addAllowedOrigin("https://inused.store");
        config.addAllowedOrigin("https://www.inused.store");
        config.addAllowedOrigin("https://api.inused.store");
        config.addAllowedHeader("*");
        config.addExposedHeader("*");
        config.addExposedHeader("Authorization");
        config.addExposedHeader("Refresh");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
