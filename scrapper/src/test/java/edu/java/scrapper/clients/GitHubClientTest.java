package edu.java.scrapper.clients;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import edu.java.scrapper.dto.response.GitHubResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.time.OffsetDateTime;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GitHubClientTest {
    private static final int port = 8080;
    private static final String userName = "JetBrains";
    private static final String repoName = "kotlin";

    private static final String responseBody = """
        {
        "id":3432266,
        "node_id":"MDEwOlJlcG9zaXRvcnkzNDMyMjY2",
        "name":"kotlin",
        "full_name":"JetBrains/kotlin",
        "private":false,
        "owner":{"login":"JetBrains"},
        "html_url":"https://github.com/JetBrains/kotlin",
        "description":"The Kotlin Programming Language. ",
        "fork":false,
        "url":"https://api.github.com/repos/JetBrains/kotlin",
        "updated_at":"2024-03-02T10:45:49Z"
        }""";


    private WireMockServer wireMockServer;

    private final GitHubWebClient client = new GitHubWebClient("http://localhost:8080/");


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
    public void getLastUpdateTest() {
        var url = "/repos/" + userName + "/" + repoName;

        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo(url))
            .willReturn(aResponse()
                .withBody(responseBody)));

        GitHubResponse actualResult =  client.getLastUpdate(userName, repoName);

        assertNotNull(actualResult);
        assertEquals(3432266, actualResult.id());
        assertEquals("JetBrains/kotlin", actualResult.name());
        assertEquals(OffsetDateTime.parse("2024-03-02T10:45:49Z"), actualResult.updatedAt());
    }

    @Test
    public void getLastUpdateInvalidTest() {
        var url = "/repos/" + userName + "/" + repoName;

        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo(url))
            .willReturn(aResponse()
                .withBody("bad body")));

        GitHubResponse actualResult =  client.getLastUpdate(userName, repoName);

        assertNull(actualResult);

    }







}
