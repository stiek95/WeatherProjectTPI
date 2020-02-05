/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherim;

import Map.FindPlace;
import Map.Place;
import Weather.FindWeather;
import Weather.WeatherOBJ;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrea
 */
public class WeatherIM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, XPathExpressionException {
        // TODO code application logic here
         FindPlace plc= new FindPlace();
         FindWeather wth= new FindWeather();
            
            Place place=plc.getPlace("Brescia");
            WeatherOBJ weather=wth.getWeather(place);
            System.out.println(weather);
            System.out.println(place);
    }
    
}
