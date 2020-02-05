/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weather;

import Map.Place;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Andrea
 */
public class FindWeather {
    private static final String CURRENT_T = "/current/temperature/@value";
    private static final String MIN_T= "/current/temperature/@min";
    private static final String MAX_T = "/current/temperature/@max";
     /**
      * 
      * @param place Place object where we search weather information
      * @return WeatherOBJ(String,String,String)
      */
     public WeatherOBJ getWeather(Place place) {
        try {
            //stabilisco la connessione con openWeather
            URL url = WeatherURL.generateURL(place);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.0.1", 8080));
            URLConnection urlConnection = url.openConnection(proxy);
            //URLConnection urlConnection = url.openConnection();
            InputStream in = urlConnection.getInputStream();
            
            //creo il documento XML
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document docXML = builder.parse(in);
            
            //creo un istanza xpath per eseguire il compile
            XPath xpath = XPathFactory.newInstance().newXPath();
            
            //ricavo la temperatura attuale con xpath
            XPathExpression CtempExpression= (XPathExpression) xpath.compile(CURRENT_T);
            NodeList CStemp = (NodeList) CtempExpression.evaluate(docXML, XPathConstants.NODESET);
            String Ctemp = CStemp.item(0).getNodeValue();
            
            //ricavo la temperatura massima con xpath
            XPathExpression TmaxExpression = (XPathExpression) xpath.compile(MIN_T);
            NodeList TSmax = (NodeList) TmaxExpression.evaluate(docXML, XPathConstants.NODESET);
            String Tmax = TSmax.item(0).getNodeValue();
            
            //ricavo la temperatura minima con xpath
            XPathExpression TminExpression = (XPathExpression) xpath.compile(MAX_T);
            NodeList TSmin = (NodeList) TminExpression.evaluate(docXML, XPathConstants.NODESET);
            String Tmin = TSmin.item(0).getNodeValue();
            
            
            
            
            return new WeatherOBJ(Ctemp, Tmin, Tmax);
        } catch (Exception ex) {
        System.out.println("Error during extraction W");
        }
        return null;
    }
    
}
