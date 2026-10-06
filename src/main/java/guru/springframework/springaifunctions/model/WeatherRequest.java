package guru.springframework.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * Created by jt, Spring Framework Guru.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Weather API request")
public record WeatherRequest(
        @JsonProperty(required = true) @JsonPropertyDescription("Latitude of the location") String lat,
        @JsonProperty(required = true) @JsonPropertyDescription("Longitude of the location") String lon) {
}
