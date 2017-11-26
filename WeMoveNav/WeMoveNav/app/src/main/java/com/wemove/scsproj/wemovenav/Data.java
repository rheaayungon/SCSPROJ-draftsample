package com.wemove.scsproj.wemovenav;

/**
 * Created by rheaayungon on 13/11/2017.
 */

public class Data {

    private String Date;
    private String Day;
    private String Origin_Display_Name;
    private String Destination_Display_Name;

    public Data(){

    }

    public Data(String Date, String Day, String Origin_Display_Name, String Destination_Display_Name){
        this.Date = Date;
        this.Day = Day;
        this.Origin_Display_Name = Origin_Display_Name;
        this.Destination_Display_Name = Destination_Display_Name;
    }

    public String getDate() {
        return Date;
    }

    public String getDay() {
        return Day;
    }

    public String getOrigin_Display_Name() {
        return Origin_Display_Name;
    }

    public String getDestination_Display_Name() {
        return Destination_Display_Name;
    }

}