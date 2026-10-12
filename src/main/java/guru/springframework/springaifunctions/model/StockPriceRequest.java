package guru.springframework.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Request object for retrieving stock price information")
public record StockPriceRequest(@JsonPropertyDescription("The ticker symbol of the company") String ticker) {
}
