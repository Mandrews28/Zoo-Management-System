package APAssignment;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;

abstract class Utility extends Thread {

    protected static String getWeather() {
        HttpURLConnection connect = setUpWeatherConnection();
        StringBuffer weatherData = getWeatherData(connect);

        if (weatherData != null) {
            JSONDeserializer<HashMap> der = new JSONDeserializer<>();
            JSONSerializer ser = new JSONSerializer();

            Object mainGroup = der.deserialize(new String(weatherData)).get("main");
            String temp = String.valueOf(der.deserialize(ser.deepSerialize(mainGroup)).get("temp"));

            Object weatherGroup = der.deserialize(new String(weatherData)).get("weather");
            String weather = (String) ((ArrayList<HashMap>) weatherGroup).get(0).get("main");
            return "London: T = " + temp + "°C"
                    + " || Weather: " + weather
                    + " || Updated: " + currentDateAndTime();
        } else {
            return "Error loading weather";
        }
    }

    private static HttpURLConnection setUpWeatherConnection() {
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=London&appid=33189d433fa2aa345651658488dd1f96&units=metric");
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");

            if (connect.getResponseCode() != 200) {
                throw new Exception();
            }

            return connect;

        } catch (Exception e) {
            return null;
        }
    }

    private static StringBuffer getWeatherData(HttpURLConnection connect) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            String inputLine;
            StringBuffer weatherData = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                weatherData.append(inputLine);
            }
            in.close();
            return weatherData;
        } catch (Exception e) {
            return null;
        }
    }

    private static String currentDateAndTime() {
        String[] dateAndTime = ZonedDateTime.now().toString().split("T|\\.");
        return dateAndTime[0] + " " + dateAndTime[1];
    }

    @Override
    public void run() {
        getWeather();
    }
}

