package guru.springframework.springaifunctions.functions;

import guru.springframework.springaifunctions.model.CityRequest;
import guru.springframework.springaifunctions.model.CityResponse;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

public class CityServiceFunction implements Function<CityRequest, CityResponse[]> {

    public static final String CITY_URL = "https://api.api-ninjas.com/v1/city";

    private final String apiNinjasKey;

    public CityServiceFunction(String apiNinjasKey) {
        this.apiNinjasKey = apiNinjasKey;
    }

    @Override
    public CityResponse[] apply(CityRequest cityRequest) {
        RestClient restClient = RestClient.builder()
                .baseUrl(CITY_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjasKey);
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                }).build();

        return restClient.get().uri(uriBuilder -> {
            System.out.println("Building URI for city request: " + cityRequest);

            uriBuilder.queryParam("name", cityRequest.name());

            return uriBuilder.build();
        }).retrieve().body(CityResponse[].class);
    }
}
