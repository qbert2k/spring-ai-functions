# Spring AI Functions

This is a small Spring Boot application that demonstrates how to expose a Java
function to an OpenAI chat model with Spring AI. The model can call a weather
function backed by [API Ninjas](https://api-ninjas.com/api/weather), then use
the returned data to answer a natural-language question.

## How it works

1. The application accepts a question at `POST /weather`.
2. Spring AI sends the question and the `CurrentWeather` function definition to
   OpenAI.
3. When weather data is needed, the model calls the Java
   `WeatherServiceFunction`.
4. The function calls API Ninjas using the latitude and longitude supplied by
   the model.
5. The model turns the weather response into a useful answer.

The function input is a latitude and longitude:

```json
{
  "lat": "53.350140",
  "lon": "-6.266155"
}
```

The response model includes the current condition, temperatures, humidity,
cloud coverage, wind, sunrise and sunset times, and the available weather
icons.

## Requirements

- Java 21
- Maven 3.9+ (or the included Maven Wrapper)
- An [OpenAI API key](https://platform.openai.com/api-keys)
- An [API Ninjas API key](https://api-ninjas.com/)

## Configuration

Set both API keys in the environment before starting the application. The
application reads them from `OPENAI_API_KEY` and `API_NINJAS_KEY`; keys are
never intended to be committed to the repository.

```bash
export OPENAI_API_KEY="your-openai-api-key"
export API_NINJAS_KEY="your-api-ninjas-key"
```

On Windows PowerShell:

```powershell
$env:OPENAI_API_KEY = "your-openai-api-key"
$env:API_NINJAS_KEY = "your-api-ninjas-key"
```

## Run the application

Start the application with the Maven Wrapper:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The server listens on `http://localhost:8080`.

## Try it with curl

These examples can be pasted directly into a terminal while the application
is running.

### Dublin: outdoor clothes recommendation

```bash
curl --location 'http://localhost:8080/weather' \
  --header 'Content-Type: application/json' \
  --data '{
    "question": "What is the current weather at latitude 53.350140 and longitude -6.266155 in Dublin? Use metric units. Is it a good day to dry clothes outside? Explain your recommendation using the rain, humidity, wind, and temperature."
  }'
```

Example response:

```json
{
  "answer": "The current weather in Dublin at latitude 53.350140 and longitude -6.266155 is 11°C with moderate rain. The humidity is quite high at 92%, and the wind is blowing at a speed of 3.6 km/h.\n\nGiven these conditions, it would not be a good day to dry clothes outside. The rain and high humidity mean that the clothes would not dry properly and may even get wetter if left outside. The wind, while not particularly strong, would not be enough to counteract the effects of the rain and humidity. Furthermore, the temperature is relatively low, which means the evaporation rate would be slow, making the drying process much longer."
}
```

### New York: commute planning

```bash
curl --location 'http://localhost:8080/weather' \
  --header 'Content-Type: application/json' \
  --data '{
    "question": "Check the weather at latitude 40.7128 and longitude -74.0060 in New York City. Should I bring an umbrella and a jacket for a 30-minute walk? Give a concise answer in Fahrenheit and miles per hour."
  }'
```

Example response:

```json
{
  "answer": "The current weather in New York City is clear with a temperature of 62.6°F (converted from 17°C). The wind speed is approximately 11.5 miles per hour (converted from 5.14 m/s). Given these conditions, you won't need an umbrella. As for the jacket, it depends on your personal comfort level with this temperature. If you feel cold at around 63°F, you might want to bring a jacket for your 30-minute walk."
}
```

### Sydney: compare conditions with a specific activity

```bash
curl --location 'http://localhost:8080/weather' \
  --header 'Content-Type: application/json' \
  --data '{
    "question": "What is the weather at latitude -33.8688 and longitude 151.2093 in Sydney? Is it suitable for an outdoor lunch right now? Mention the temperature, cloud cover, wind, and whether rain is expected."
  }'
```

Example response:

```json
{
  "answer": "The current weather in Sydney is overcast with a cloud cover of 99%. The temperature is 15°C (59°F), with a wind speed of 12.35 km/h (7.67 mph) coming from the south. The humidity is at 62%. There's no mention of rain, but with such heavy cloud cover, you might want to keep an eye on the weather updates before deciding on an outdoor lunch."
}
```

The request body contains one natural-language question. Include the location's
latitude and longitude in the question so the model can populate the weather
function arguments reliably. The model can convert units and tailor the answer,
but the weather service itself returns metric values.

## Build and test

Compile and package without running tests:

```bash
./mvnw -DskipTests package
```

Run the test suite:

```bash
./mvnw test
```

Tests that load the Spring context require `OPENAI_API_KEY` and
`API_NINJAS_KEY` to be configured.

## Project structure

| Path                                  | Purpose                                               |
|---------------------------------------|-------------------------------------------------------|
| `services/OpenAIServiceImpl`          | Builds the prompt and registers the function callback |
| `functions/WeatherServiceFunction`    | Calls API Ninjas with latitude and longitude          |
| `model/WeatherRequest`                | Schema supplied to the model for function arguments   |
| `model/WeatherResponse`               | Maps the API Ninjas weather response                  |
| `controllers/QuestionController`      | Exposes `POST /weather`                               |
| `src/main/resources/application.yaml` | Spring AI and environment-variable configuration      |

## Related Spring Framework Guru courses

- [Spring Framework 6 - Beginner to Guru](https://www.udemy.com/course/spring-framework-6-beginner-to-guru/)
- [API First Engineering with Spring Boot](https://www.udemy.com/course/api-first-engineering-with-spring-boot/)
- [Introduction to Kafka with Spring Boot](https://www.udemy.com/course/introduction-to-kafka-with-spring-boot/)
- [Spring Framework Guru blog](https://springframework.guru/)
