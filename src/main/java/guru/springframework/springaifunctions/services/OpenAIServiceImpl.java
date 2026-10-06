package guru.springframework.springaifunctions.services;

import guru.springframework.springaifunctions.functions.CityServiceFunction;
import guru.springframework.springaifunctions.functions.WeatherServiceFunction;
import guru.springframework.springaifunctions.model.Answer;
import guru.springframework.springaifunctions.model.CityRequest;
import guru.springframework.springaifunctions.model.Question;
import guru.springframework.springaifunctions.model.WeatherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

    @Value("${sfg.aiapp.apiNinjasKey}")
    private String apiNinjasKey;

    private final OpenAiChatModel openAiChatModel;

    @Override
    public Answer getAnswer(Question question) {
        var promptOptions = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(
                        FunctionCallback.builder()
                                .function("CurrentWeather", new WeatherServiceFunction(apiNinjasKey))
                                .description("Get the current weather for a location")
                                .inputType(WeatherRequest.class)
                                .build(),
                        FunctionCallback.builder()
                                .function("CityInfo", new CityServiceFunction(apiNinjasKey))
                                .description("Get information about a city")
                                .inputType(CityRequest.class)
                                .build()))
                .build();

        Message userMessage = new PromptTemplate(question.question()).createMessage();

        Message systemMessage = new SystemPromptTemplate(
                """
                You are a helpful location and weather assistant.
                Use CityInfo when the user asks about a city's details, such as its country, region, coordinates, population, or whether it is a capital.
                Use CurrentWeather when the user asks about weather conditions.
                When the user asks for weather by city name and coordinates are not provided, call CityInfo first, then use the returned latitude and longitude as the inputs to CurrentWeather.
                If CityInfo returns multiple possible cities, ask the user to clarify before requesting weather.
                Weather data is returned in metric units; convert temperatures to Fahrenheit and wind speeds to miles per hour when appropriate for the user's location or request.
                """)
                .createMessage();

        var response = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));

        return new Answer(response.getResult().getOutput().getContent());
    }
}
