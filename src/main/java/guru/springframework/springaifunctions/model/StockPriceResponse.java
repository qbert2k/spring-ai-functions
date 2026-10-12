package guru.springframework.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;

@JsonClassDescription("Response object for retrieving stock price information")
public record StockPriceResponse(
        @JsonPropertyDescription("The stock ticker symbol") String ticker,
        @JsonPropertyDescription("The name of the company") String name,
        @JsonPropertyDescription("The current stock price") BigDecimal price,
        @JsonPropertyDescription("The stock exchange where the stock is listed") String exchange,
        @JsonPropertyDescription("The timestamp of the last update") Long updated,
        @JsonPropertyDescription("The currency of the stock price") String currency,
        @JsonPropertyDescription("The trading volume of the stock") Long volume,
        @JsonPropertyDescription("The previous closing price of the stock") BigDecimal previous_close,
        @JsonPropertyDescription("The change in the stock price") BigDecimal change) {
}
