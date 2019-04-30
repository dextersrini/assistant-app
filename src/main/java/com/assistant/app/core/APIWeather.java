package com.assistant.app.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assistant.app.model.WeatherLocation;
import com.google.gson.Gson;

/**
 * Get Weather Data. MetaWeather.com is used here.
 * 
 * @author 607940969
 *
 */

@RestController
@RequestMapping(path = "/weatherapi")
public class APIWeather {
	@GetMapping("/{city}")
	public String getCurrentWeather(@PathVariable(name = "city") String city) {

		return getCurrentWeatherByCity(city);
	}

	private String getCurrentWeatherByCity(String city) {

		/**
		 * Get WOEID first before weather
		 */
		String locUrl = "https://www.metaweather.com/api/location/search/?query=" + city;
		String locRes;
		StringBuilder response = new StringBuilder();
		Gson parser = new Gson();
		try {
			URL locURL = new URL(locUrl);
			HttpURLConnection conn = (HttpURLConnection) locURL.openConnection();
			conn.setRequestMethod(HttpMethod.GET.toString());
			int responsecode = conn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responsecode) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((locRes = in.readLine()) != null) {
					response.append(locRes);
				}
				in.close();
			}
		} catch (Exception er) {

		}
		WeatherLocation[] locEnum = parser.fromJson(response.toString(), WeatherLocation[].class);
		locRes = locEnum[0].getWoeid();//to fix this later

		/**
		 * Get Weather Data from location WOEID
		 */
		String weatherurl = "https://www.metaweather.com/api/location/" + locRes;
		String weatherOut;
		response = new StringBuilder();
		try {
			URL weatherURL = new URL(weatherurl);
			HttpURLConnection conn = (HttpURLConnection) weatherURL.openConnection();
			conn.setRequestMethod("GET");
			int responsecode = conn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responsecode) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((weatherOut = in.readLine()) != null) {
					response.append(weatherOut);
				}
				in.close();
			}
		} catch (Exception er) {
			System.err.println(er.getMessage());
		}
		return response.toString();
	}
}
