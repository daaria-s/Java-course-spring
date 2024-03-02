package edu.java.scrapper.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.java.scrapper.dto.response.GitHubResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

public class GitHubWebClient {
    private String url = "https://api.github.com/";
    private final WebClient webClient;

    public GitHubWebClient() {
        this.webClient = WebClient.builder().baseUrl(url).build();
    }

    public GitHubWebClient(String url) {
        this.url = url;
        webClient = WebClient.builder().baseUrl(url).build();
    }

    public GitHubResponse getLastUpdate(String userName, String repoName) {
        String request = url + "repos/" + userName + "/" + repoName;
        var json = webClient.get().uri(request).retrieve().bodyToMono(String.class).block();

        return parse(json);
    }

    GitHubResponse parse(String json) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(json, GitHubResponse.class);

        } catch (Exception exception) {
            return null;
        }
    }
}
