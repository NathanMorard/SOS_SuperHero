package com.demo.controller;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;


public class connectionSQL {
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/superherojava";
    private static String user = "root";
    private static String password = "root";
    private Connection connection;

    public static void getincident() {
        String request = "SELECT * FROM superherojava.incidents;";
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, user, password);
            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery(request);
            while (rs.next()) {
                System.out.println(rs.getString("Incident"));
            }
            rs.close();
            stm.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertHero(String name, String incident1, String incident2, String incident3, String phone, String lat, String lng) {
        String request = "INSERT INTO superherojava.superhero (NameHero, Incident1, Incident2, Incident3, Phone, latitude, longtitude ) VALUES (?,?,?,?,?,?,?)";
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
                String latitudeStr = rs.getString("latitude");
                String longitudeStr = rs.getString("longitude");
                double latitude = Double.parseDouble(latitudeStr);
                double longitude = Double.parseDouble(longitudeStr);
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



    public List<String> getIncident() {
        List<String> values = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT Incident FROM superherojava.incidents")) {

            while (rs.next()) {
                values.add(rs.getString("Incident"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }

}
