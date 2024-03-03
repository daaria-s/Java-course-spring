package edu.java.scrapper.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StackOverflowResponse(

    @JsonProperty("answer_id")
    Long answerId,

    @JsonProperty("question_id")
    Long questionId,

    @JsonProperty("last_activity_date")
    OffsetDateTime lastActivityDate
) {
}
