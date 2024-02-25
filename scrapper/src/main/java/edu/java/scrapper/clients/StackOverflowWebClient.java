package edu.java.scrapper.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.java.scrapper.dto.response.StackOverflowResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

public class StackOverflowWebClient {

    @Value("${api.stackoverflow.url}")
    String url;

    WebClient webClient;

    public StackOverflowWebClient() {
        webClient = WebClient.builder().baseUrl(url).build();
    }

    public StackOverflowWebClient(String url) {
        this.url = url;
        webClient = WebClient.builder().baseUrl(url).build();
    }

    StackOverflowResponse getRecentAnswer(Long questionId) {
        String request = url + "questions/" + questionId.toString() + "/answers";

        var x = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path(request)
                .queryParam("pagesize", 1)
                .queryParam("order", "desc")
                .queryParam("sort", "activity")
                .queryParam("site", "stackoverflow")
                .build()
            )
            .retrieve();

        var json = x
            .bodyToMono(String.class)
            .mapNotNull(this::parse)
            .block();

        return json;
    }

    StackOverflowResponse parse(String json) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(json, StackOverflowResponse.class);

        } catch (Exception exception) {
            return null;
        }
    }
}
