package com.javabatch2.Training.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
  @Bean
  public GroupedOpenApi SlateSwagger() {
    String[] paths = {"/project/**"};
    return GroupedOpenApi.builder().group("slate").pathsToMatch(paths).build();
  }
}