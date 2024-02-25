package edu.java.scrapper.configuration;

import edu.java.scrapper.clients.GitHubWebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GitHubWebClientConfig {

    @Bean
    GitHubWebClient gitHubWebClient() {
        return new GitHubWebClient();
    }
}
