/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

/**
 *
 * @author Andrea
 */
public class Place {
    private double latitude, longitude;
    private String FAddress;

    public Place(double latitude, double longitude, String FAddress) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.FAddress = FAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getFAddress() {
        return FAddress;
    }

    @Override
    public String toString() {
        return "Place:" + "latitude=" + latitude +"\n"+ "longitude=" + longitude +"\n"+ "FAddress=" + FAddress + '}';
    }
   
    

}
