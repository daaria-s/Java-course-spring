package edu.java.scrapper.clients;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import edu.java.scrapper.dto.response.StackOverflowResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.web.util.UriComponentsBuilder;
import java.time.OffsetDateTime;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StackOverflowClientTest {
    private static final int port = 8080;
    private static final Long questionId = 1234L;

    private static final String responseBody = """
        {
        "items":[
        {"owner":
        {
        "account_id":14688437,
        "reputation":5233,
        "user_id":10607772,
        "user_type":"registered"},
        "is_accepted":false,
        "score":0,
        "last_activity_date":1601812778,
        "creation_date":1601812778,
        "answer_id":64194327,
        "question_id":64117227
        }
        ],
        "has_more":false,
        "quota_max":300,
        "quota_remaining":297
        }
        """;


    private WireMockServer wireMockServer;

    private final StackOverflowWebClient client = new StackOverflowWebClient("http://localhost:8080/");


    @BeforeEach
    public void wireMockServerCreate() {
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", port);
        this.wireMockServer = wireMockServer;
    }


    @AfterEach
    void stop() {
        wireMockServer.stop();
    }


    @Test
    public void getRecentAnswerTest() {

        var uri = UriComponentsBuilder.fromPath("/questions/" + questionId + "/answers").queryParam("pagesize", 1)
            .queryParam("order", "desc")
            .queryParam("sort", "activity")
            .queryParam("site", "stackoverflow");

        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo(uri.toUriString()))
            .willReturn(aResponse()
                .withBody(responseBody)));

        StackOverflowResponse actualResult = client.getRecentAnswer(questionId);
        assertNotNull(actualResult);
        assertEquals(64194327, actualResult.answerId());
        assertEquals(64117227, actualResult.questionId());
        assertEquals(OffsetDateTime.parse("2020-10-04T11:59:38Z"), actualResult.lastActivityDate());
    }

    @Test
    public void getRecentAnswerInvalidTest() {
        var uri = UriComponentsBuilder.fromPath("/questions/" + questionId + "/answers").queryParam("pagesize", 1)
            .queryParam("order", "desc")
            .queryParam("sort", "activity")
            .queryParam("site", "stackoverflow");

        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo(uri.toUriString()))
            .willReturn(aResponse()
                .withBody("bad body")));

        StackOverflowResponse actualResult = client.getRecentAnswer(questionId);

        assertNull(actualResult);
    }

}
