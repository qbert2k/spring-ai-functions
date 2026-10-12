package guru.springframework.springaifunctions.functions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.springaifunctions.model.StockPriceRequest;
import guru.springframework.springaifunctions.model.StockPriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@RequiredArgsConstructor
public class StockQuoteFunction implements Function<StockPriceRequest, StockPriceResponse> {

    public final String STOCK_QUOTE_URL = "https://api.api-ninjas.com/v1/stockprice";

    private final String apiNinjasKey;

    @Override
    public StockPriceResponse apply(StockPriceRequest request) {
        RestClient restClient = RestClient.builder()
                .baseUrl(STOCK_QUOTE_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjasKey);
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                }).build();

        JsonNode jsonNode = restClient.get().uri(uriBuilder -> {
            System.out.println("Building URI for stock quote request: " + request);

            uriBuilder.queryParam("ticker", request.ticker());

            return uriBuilder.build();
        }).retrieve().body(JsonNode.class);

        System.out.println("Received JSON response for stock quote: " + jsonNode);

        if (jsonNode.isEmpty()) {
            return new StockPriceResponse(
                    null, null, null,
                    null, null, null,
                    null, null, null);
        } else {
            return new ObjectMapper().convertValue(jsonNode, StockPriceResponse.class);
        }
    }
}
