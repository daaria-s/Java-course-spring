package edu.java.scrapper.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.java.scrapper.dto.response.StackOverflowResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println(url);
        String request = url + "questions/" + questionId + "/answers?site=stackoverflow";

        String req = "https://api.stackexchange.com/2.3/" + "questions/" + questionId + "/answers?site=stackoverflow&pagesize=1&order=desc&sort=activity";


        var json = webClient.get()
//            .uri(uriBuilder -> uriBuilder
//                .path(request)
//                .queryParam("pagesize", 1)
//                .queryParam("order", "desc")
//                .queryParam("sort", "activity")
//                .queryParam("site", "stackoverflow")
//                .build(questionId)
//            )
            .uri(request)
            .retrieve()
            .bodyToMono(String.class)
//            .mapNotNull(this::parse)
            .block();
        System.out.println(json);
        return null;
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
//
//
//class Main {
//    public static void main(String[] args) {
//        var cl = new StackOverflowWebClient();
//        cl.getRecentAnswer(184618L);
//    }
//}
