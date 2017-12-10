package com.wemove.scsproj.wemovenav;

/**
 * Created by rheaayungon on 13/11/2017.
 */

public class TravelData {

    private String Date;
    private String Day;
    private String Origin_Display_Name;
    private String Destination_Display_Name;
    private String TravelID;

    public Data(){

    }

    public TravelData(String date, String day, String origin_Display_Name, String destination_Display_Name, String travelID) {
        Date = date;
        Day = day;
        Origin_Display_Name = origin_Display_Name;
        Destination_Display_Name = destination_Display_Name;
        TravelID = travelID;
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

    public String getTravelID() {
        return TravelID;
    }
}