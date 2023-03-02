package com.demo.controller;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import com.demo.model.Hero;
import com.demo.model.Incident;


public class ConnectionSQL {
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/superherojava";
    private static String user = "root";
    private static String password = "root";
    private Connection connection;

    public static void insertHero(String name, String incident1, String incident2, String incident3, String phone, String lat, String lng) {
        String request = "INSERT INTO superherojava.superhero (NameHero, Incident1, Incident2, Incident3, Phone, latitude, longitude ) VALUES (?,?,?,?,?,?,?)";
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, user, password);
            PreparedStatement stmhero = con.prepareStatement(request);
            stmhero.setString(1, name);
            stmhero.setString(2, incident1);
            stmhero.setString(3, incident2);
            stmhero.setString(4, incident3);
            stmhero.setString(5, phone.substring(0, Math.min(phone.length(), 20))); // limiter la longueur du champ Phone à 20 caractères
            stmhero.setString(6, lat);
            stmhero.setString(7, lng);

            stmhero.executeUpdate();
            stmhero.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getHero() {
        List<String> heroList = new ArrayList<>();
        String request = "SELECT latitude, longitude FROM superherojava.superhero";
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, user, password);
            PreparedStatement stmhero = con.prepareStatement(request);

            ResultSet rs = stmhero.executeQuery();
            while (rs.next()) {
                Double latitude = rs.getDouble("latitude");
                Double longitude = rs.getDouble("longitude");
                //double latitude = Double.parseDouble(latitudeStr);
                //double longitude = Double.parseDouble(longitudeStr);
                heroList.add(latitude + "," + longitude);
            }


            rs.close();
            stmhero.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (heroList.isEmpty()) {
            // La liste est vide, faire quelque chose
            System.out.println("La liste est vide !");
        }

        return heroList;
    }



    public List<Incident> getIncident() {
        List<Incident> incidentList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM superherojava.incidents")) {

            while (rs.next()) {
                int idIncidents = Integer.parseInt(rs.getString("idIncidents"));
                String nom_incident = rs.getString("nom_incident");
                var incident = new Incident(idIncidents, nom_incident);
                incidentList.add(incident);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidentList;
    }


    public List<Hero> getHeroForIncident(String incident) {
        List<Hero> heroList = new ArrayList<>();
        String request = "SELECT * FROM superherojava.superhero WHERE Incident1=? OR Incident2=? OR Incident3=?;";
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, user, password);
            PreparedStatement stmheroIncident = con.prepareStatement(request);
            stmheroIncident.setString(1, incident);
            stmheroIncident.setString(2, incident);
            stmheroIncident.setString(3, incident);

            ResultSet result = stmheroIncident.executeQuery();
            while (result.next()) {
                int heroId = Integer.parseInt(result.getString("idHero"));
                String heroName = result.getString("NameHero");
                String heroinc1 = result.getString("Incident1");
                String heroinc2 = result.getString("Incident2");
                String heroinc3 = result.getString("Incident3");
                String heroPhone = result.getString("Phone");
                String heroLat = result.getString("latitude");
                String heroLng = result.getString("longitude");

                var hero = new Hero(heroId, heroName, heroinc1, heroinc2, heroinc3, heroPhone,heroLat, heroLng);
                heroList.add(hero);
            }

            stmheroIncident.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return heroList;
    }

}
