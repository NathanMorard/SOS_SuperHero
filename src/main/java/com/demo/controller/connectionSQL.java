package com.demo.controller;

import java.sql.*;

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

    public static void getHero(String name, String incident1, String incident2, String incident3, double phone, double lat, double lng) {
        String request = "INSERT INTO superherojava.superhero (NameHero, IdIncident1, IdIncident2, IdIncident3, Phone, latitude, longtitude ) VALUES (?, ?, ?, ?, ?, ?,?)";
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, user, password);
            PreparedStatement stmhero = con.prepareStatement(request);
            stmhero.setString(1, name);
            stmhero.setString(2, incident1);
            stmhero.setString(3, incident2);
            stmhero.setString(4, incident3);
            stmhero.setDouble(5, phone);
            stmhero.setDouble(6, lat);
            stmhero.setDouble(7, lng);

            stmhero.executeUpdate();
            stmhero.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
