package fr.eni.masia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AIGatewayConfig {

    @Bean
    public RestClient aiGatewayRestClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8081")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

}
