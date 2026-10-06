package guru.springframework.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CityResponse(
        @JsonPropertyDescription("City name") String name,
        @JsonPropertyDescription("Latitude of the city") BigDecimal latitude,
        @JsonPropertyDescription("Longitude of the city") BigDecimal longitude,
        @JsonPropertyDescription("Country code") String country,
        @JsonPropertyDescription("City population") Long population,
        @JsonPropertyDescription("State, province, or administrative region") String region,
        @JsonPropertyDescription("Whether the city is the country's capital") Boolean isCapital) {
}
