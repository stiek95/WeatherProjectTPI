/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weather;

import Map.Place;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Andrea
 */
public class WeatherURL {
    private static final String URL_STD= "http://api.openweathermap.org/data/2.5/weather?";
    private static final String LATITUDE = "lat=";
    private static final String LONGITUDE = "&lon=";
    private static final String M_UNITS = "&units=metric";
    private static final String WEATHER_ID ="&appid=9389bf90e470f9c9ce8f0aa8a943500a";
    private static final String XML_MODE = "&mode=xml";
    private static URL URL;
    
    

   /**
    * 
    * @param location
    * @return URL object
    */
    public static URL generateURL(Place location) {
        //creazione stringa URL
        StringBuilder URLBuilder = new StringBuilder(URL_STD);
        URLBuilder.append(LATITUDE);
        URLBuilder.append(location.getLatitude());
        URLBuilder.append(LONGITUDE);
        URLBuilder.append(location.getLongitude());
        URLBuilder.append(M_UNITS);
        URLBuilder.append(WEATHER_ID);
        URLBuilder.append(XML_MODE);
        
        try {
            //conversione in URL
            URL = new URL(URLBuilder.toString());
        } catch (MalformedURLException ex) {
            System.out.println("Error during conversion");
            return null;
        }       
        return URL;
    }
}
