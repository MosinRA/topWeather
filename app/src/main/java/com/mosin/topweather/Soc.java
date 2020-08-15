package com.mosin.topweather;

public class Soc {

    private String description;
    private int picture;
    private String temperature;

    public Soc(String description, int picture, String temperature){
        this.description=description;
        this.picture=picture;
        this.temperature=temperature;
    }

    public String getDescription(){
        return description;
    }

    public int getPicture(){
        return picture;
    }

    public String getTemperature(){
        return temperature;
    }
}
