package edu.java.scrapper.configuration;

import edu.java.scrapper.clients.StackOverflowWebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StackOverflowWebClientConfig {

    @Bean
    StackOverflowWebClient stackOverflowWebClient() {
        return new StackOverflowWebClient();
    }
}
