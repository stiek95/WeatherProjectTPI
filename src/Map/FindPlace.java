/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;


import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrea
 */
public class FindPlace {
    private static final String F_ADDRESS = "/GeocodeResponse/result/formatted_address/text()";
    private static final String LATITUDE = "/GeocodeResponse/result/geometry/location/lat/text()";
    private static final String LONGITUDE = "/GeocodeResponse/result/geometry/location/lng/text()";
    
    public FindPlace(){}
    /**
     * 
     * @param location String of the place to search
     * @return Place object (double,double,String)
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws XPathExpressionException 
     */
    public Place getPlace (String location) throws ParserConfigurationException, SAXException, XPathExpressionException {
        try {
            //stabilisco la connessione con openWeather
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(location, "UTF-8"));
            
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.0.1", 8080));
            URLConnection urlConnection = url.openConnection(proxy);
            //URLConnection urlConnection = url.openConnection();
            InputStream in = urlConnection.getInputStream();
            
            //creo il documento XML
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xml = builder.parse(in);
            
            
            
            //creo un istanza xpath per eseguire il compile
            XPath xpath = XPathFactory.newInstance().newXPath();
            
            //ricavo la latitudine con xpath
            XPathExpression latitudeExpression = (XPathExpression) xpath.compile(LATITUDE);
            NodeList latitudes = (NodeList) latitudeExpression.evaluate(xml, XPathConstants.NODESET);
            double latitude = Double.parseDouble(latitudes.item(0).getNodeValue());
            
            //ricavo la longitudine con xpath
            XPathExpression longitudeExpression = (XPathExpression) xpath.compile(LONGITUDE);
            NodeList longitudes = (NodeList) longitudeExpression.evaluate(xml, XPathConstants.NODESET);
            double longitude = Double.parseDouble(longitudes.item(0).getNodeValue());
            //ricavo la stringa formattata con xpath
            XPathExpression addressExpression= (XPathExpression) xpath.compile(F_ADDRESS);
            NodeList Laddress = (NodeList) addressExpression.evaluate(xml, XPathConstants.NODESET);
            String address = Laddress.item(0).getNodeValue();
            
            return new Place(latitude, longitude, address);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FindPlace.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
         System.out.println("Error during extraction p");   
        }
        return null;
    }
    
}
