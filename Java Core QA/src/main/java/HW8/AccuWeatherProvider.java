package HW8;

import HW8.entity.WeatherData;
import HW8.enums.Periods;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Iterator;

public class AccuWeatherProvider implements WeatherProvider {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST_ENDPOINT = "forecasts";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "currentconditions";
    private static final String DAILY_CONDITIONS_ENDPOINT = "daily";
    private static final String API_VERSION = "v1";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public WeatherData getWeather(Periods periods) throws IOException {
        String cityKey = detectCityKey();
        if (periods.equals(Periods.NOW)) {
            HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment(CURRENT_CONDITIONS_ENDPOINT)
                .addPathSegment(API_VERSION)
                .addPathSegment(cityKey)
                .addQueryParameter("apikey", API_KEY)
                .build();

            Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();

            Response response = client.newCall(request).execute();
            printFormattedWeatherByDay(response.body().string());
        } else if(periods.equals(Periods.FIVE_DAYS)) {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(FORECAST_ENDPOINT)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(DAILY_CONDITIONS_ENDPOINT)
                    .addPathSegment("5day")
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", "ru-ru")
                    .addQueryParameter("metric", "true")
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
//            System.out.println(response.body().string());
            printFormattedWeatherByFiveDays(response.body().string());
        }
        return null;
    }

    @Override
    public WeatherData getAllFromDb() throws IOException {
        return null;
    }

    public String detectCityKey() throws IOException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();

        HttpUrl detectLocationURL = new HttpUrl.Builder()
            .scheme("http")
            .host(BASE_HOST)
            .addPathSegment("locations")
            .addPathSegment(API_VERSION)
            .addPathSegment("cities")
            .addPathSegment("autocomplete")
            .addQueryParameter("apikey", API_KEY)
            .addQueryParameter("q", selectedCity)
            .build();

        Request request = new Request.Builder()
            .addHeader("accept", "application/json")
            .url(detectLocationURL)
            .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Невозможно прочесть информацию о городе. " +
                "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        System.out.println("Произвожу поиск города " + selectedCity);

        if (objectMapper.readTree(jsonResponse).size() > 0) {
            String cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
            String countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizedName").asText();
            System.out.println("Найден город " + cityName + " в стране " + countryName);
        } else throw new IOException("Server returns 0 cities");

        return objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
    }

    private void printFormattedWeatherByDay(String message){
        try {
            JsonNode parentNode = objectMapper.readTree(message);
            for (Iterator<JsonNode> it = parentNode.elements(); it.hasNext(); ) {
                JsonNode element = it.next();
                if (element.at("/IsDayTime").asBoolean()){
                    System.out.print("Weather in day of ");
                } else System.out.print("Weather in night of ");
                System.out.println(element.at("/LocalObservationDateTime").asText().substring(0,10));
                System.out.print(element.at("/WeatherText").asText());
                if (element.at("/HasPrecipitation").asBoolean()){
                    System.out.print(", possible precipitation in the form ");
                    System.out.println(element.at("/PrecipitationType").asText());
                } else System.out.println(" without precipitation");
                System.out.print("Temperature: ");
                System.out.println(element.at("/Temperature/Metric/Value").asText() + "°C");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("SWW when parse message", e);
        }
    }

    private void printFormattedWeatherByFiveDays (String message){
        try {
            JsonNode parentNode = objectMapper.readTree(message).at("/DailyForecasts");

            for (Iterator<JsonNode> it = parentNode.elements(); it.hasNext(); ) {
                JsonNode element = it.next();
                System.out.print("Weather in day of ");
                System.out.println(element.at("/Date").asText().substring(0,10));
                System.out.println(element.at("/Day/IconPhrase").asText());
                System.out.print("Weather in night of ");
                System.out.println(element.at("/Date").asText().substring(0,10));
                System.out.println(element.at("/Night/IconPhrase").asText());
                System.out.print("Temperature: ");
                System.out.println(element.at("/Temperature/Minimum/Value").asText() + " - " + element.at("/Temperature/Maximum/Value").asText() + "°C");
                System.out.println();
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("SWW when parse message", e);
        }
    }
}
