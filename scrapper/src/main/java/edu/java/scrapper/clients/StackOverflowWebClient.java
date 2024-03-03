package edu.java.scrapper.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.java.scrapper.dto.response.StackOverflowResponse;
import org.springframework.web.reactive.function.client.WebClient;

public class StackOverflowWebClient {

    private String url = "https://api.stackexchange.com/2.3/";
    WebClient webClient;

    public StackOverflowWebClient() {
        webClient = WebClient.builder().baseUrl(url).build();
    }

    public StackOverflowWebClient(String url) {
        this.url = url;
        webClient = WebClient.builder().baseUrl(url).build();
    }

    StackOverflowResponse getRecentAnswer(Long questionId) {
        String request = "questions/" + questionId + "/answers";
        var json = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path(request)
                .queryParam("pagesize", 1)
                .queryParam("order", "desc")
                .queryParam("sort", "activity")
                .queryParam("site", "stackoverflow")
                .build(questionId)
            )
            .retrieve()
            .bodyToMono(String.class)
//            .mapNotNull(this::parse)
            .block();
        return parse(json);
    }

    StackOverflowResponse parse(String json) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode ownerNode = rootNode.get("items").get(0);
            return objectMapper.treeToValue(ownerNode, StackOverflowResponse.class);

        } catch (Exception exception) {
            return null;
        }
    }
}
