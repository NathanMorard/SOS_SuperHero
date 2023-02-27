package com.demo.model;

import jakarta.servlet.http.HttpServlet;

public class Hero {

    private int IdHero;
    private String NameHero;
    private String Incident1;
    private String Incident2;
    private String Incident3;
    private String Phone;
    private String Latitude;
    private String Longitude;

    public Hero(int IdHero, String NameHero, String Incident1, String Incident2, String Incident3, String Phone, String Latitude, String Longitude) {
        this.IdHero = IdHero;
        this.NameHero = NameHero;
        this.Incident1 = Incident1;
        this.Incident2 = Incident2;
        this.Incident3 = Incident3;
        this.Phone = Phone;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    public int getIdHero() {
        return IdHero;
    }

    public void setIdHero(int idHero) {
        IdHero = idHero;
    }

    public String getNameHero() {
        return NameHero;
    }

    public void setNameHero(String nameHero) {
        NameHero = nameHero;
    }

    public String getIncident1() {
        return Incident1;
    }

    public void setIncident1(String incident1) {
        Incident1 = incident1;
    }

    public String getIncident2() {
        return Incident2;
    }

    public void setIncident2(String incident2) {
        Incident2 = incident2;
    }

    public String getIncident3() {
        return Incident3;
    }

    public void setIncident3(String incident3) {
        Incident3 = incident3;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }
}
