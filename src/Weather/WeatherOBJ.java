/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weather;

/**
 *
 * @author Andrea
 */
public class WeatherOBJ {
    private String min,max,ATemp;

    public WeatherOBJ(String min, String max, String ATemp) {
        this.min = min;
        this.max = max;
        this.ATemp = ATemp;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getATemp() {
        return ATemp;
    }

    public void setATemp(String ATemp) {
        this.ATemp = ATemp;
    }

    @Override
    public String toString() {
        return "Weather:" + "min=" + min +"\n"+ "max=" + max +"\n"+ "Actual Temperature=" + ATemp;
    }
    
}
