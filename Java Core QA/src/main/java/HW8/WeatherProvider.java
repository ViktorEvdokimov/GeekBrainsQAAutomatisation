package HW8;


import HW8.entity.WeatherData;
import HW8.enums.Periods;

import java.io.IOException;

public interface WeatherProvider {

    WeatherData getWeather(Periods periods) throws IOException;

    WeatherData getAllFromDb() throws IOException;
}
