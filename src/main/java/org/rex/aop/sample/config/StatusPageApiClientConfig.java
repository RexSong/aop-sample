package org.rex.aop.sample.config;

import org.openapitools.client.api.ComponentsApi;
import org.openapitools.client.api.IncidentsApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatusPageApiClientConfig {

  @Bean
  public IncidentsApi getIncidentsApi() {
    return new IncidentsApi();
  }

  @Bean
  public ComponentsApi getComponentsApi() {return new ComponentsApi();}
}
