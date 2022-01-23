package HW8;


import HW8.entity.WeatherData;
import HW8.enums.Functionality;
import HW8.enums.Periods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    WeatherProvider weatherProvider = new AccuWeatherProvider();
    Map<Integer, Functionality> variantResult = new HashMap();
    DatabaseRepository databaseRepository = new DatabaseRepositorySQLiteImpl();

    public Controller() {
        variantResult.put(1, Functionality.GET_CURRENT_WEATHER);
        variantResult.put(2, Functionality.GET_WEATHER_IN_NEXT_5_DAYS);
        variantResult.put(3, Functionality.GET_WEATHER_HISTORY);
    }

    public void onUserInput(String input) throws IOException, SQLException {
        int command = Integer.parseInt(input);
        if (!variantResult.containsKey(command)) {
            throw new IOException("There is no command for command-key " + command);
        }

        switch (variantResult.get(command)) {
            case GET_CURRENT_WEATHER:
                getCurrentWeather();
                break;
            case GET_WEATHER_IN_NEXT_5_DAYS:
                getWeatherIn5Days();
                break;
            case GET_WEATHER_HISTORY:
                getWeatherHistory();
                break;
        }
    }

    public void getCurrentWeather() throws IOException, SQLException {
        WeatherData weatherData = weatherProvider.getWeather(Periods.NOW);
        databaseRepository.saveWeatherData(weatherData);

    }

    public void getWeatherIn5Days() throws IOException {
        WeatherData weatherData = weatherProvider.getWeather(Periods.FIVE_DAYS);
    }

    public void getWeatherHistory() throws IOException, SQLException {
        databaseRepository.getAllSavedData();
    }
}
