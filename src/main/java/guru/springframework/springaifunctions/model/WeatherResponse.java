package guru.springframework.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record WeatherResponse(
        @JsonPropertyDescription("Cloud coverage percentage") Integer cloudPct,
        @JsonPropertyDescription("Current weather condition") String weather,
        @JsonPropertyDescription("Weather condition code") Integer weatherCode,
        @JsonPropertyDescription("Human-readable weather description") String weatherDescription,
        @JsonPropertyDescription("Whether it is currently daytime") Boolean isDay,
        @JsonPropertyDescription("Weather icon identifier") String weatherIcon,
        @JsonPropertyDescription("Weather icon SVG URL") String weatherIconUrl,
        @JsonPropertyDescription("Dark-theme weather icon SVG URL") String weatherIconUrlDark,
        @JsonPropertyDescription("Weather icon PNG URL") String weatherIconPngUrl,
        @JsonPropertyDescription("Dark-theme weather icon PNG URL") String weatherIconPngUrlDark,
        @JsonPropertyDescription("Weather conditions") List<WeatherCondition> weatherConditions,
        @JsonPropertyDescription("Current temperature in Celsius") Integer temp,
        @JsonPropertyDescription("Feels-like temperature in Celsius") Integer feelsLike,
        @JsonPropertyDescription("Relative humidity percentage") Integer humidity,
        @JsonPropertyDescription("Minimum temperature in Celsius") Integer minTemp,
        @JsonPropertyDescription("Maximum temperature in Celsius") Integer maxTemp,
        @JsonPropertyDescription("Wind speed in kilometers per hour") BigDecimal windSpeed,
        @JsonPropertyDescription("Wind direction in degrees") Integer windDegrees,
        @JsonPropertyDescription("Epoch time of sunrise in GMT") Long sunrise,
        @JsonPropertyDescription("Epoch time of sunset in GMT") Long sunset) {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record WeatherCondition(
            @JsonPropertyDescription("Weather condition") String weather,
            @JsonPropertyDescription("Weather condition code") Integer weatherCode,
            @JsonPropertyDescription("Human-readable weather description") String weatherDescription,
            @JsonPropertyDescription("Whether it is currently daytime") Boolean isDay,
            @JsonPropertyDescription("Weather icon identifier") String weatherIcon,
            @JsonPropertyDescription("Weather icon SVG URL") String weatherIconUrl,
            @JsonPropertyDescription("Dark-theme weather icon SVG URL") String weatherIconUrlDark,
            @JsonPropertyDescription("Weather icon PNG URL") String weatherIconPngUrl,
            @JsonPropertyDescription("Dark-theme weather icon PNG URL") String weatherIconPngUrlDark) {
    }
}
