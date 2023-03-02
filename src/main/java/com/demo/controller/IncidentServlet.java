package com.demo.controller;

import com.demo.model.Hero;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/IncidentServlet")
public class IncidentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String latParam = request.getParameter("intlat");
        String lngParam = request.getParameter("intlng");
        Double lat = Double.parseDouble(latParam);
        Double lng = Double.parseDouble(lngParam);
        String incident = request.getParameter("valuedeclareincident");
        ConnectionSQL conn = new ConnectionSQL();

        List<Hero> heroList = conn.getHeroForIncident(incident);
        List<Hero> heroListFiltered = new ArrayList<>();
        for (int i = 0; i < heroList.size(); i++) {
            var hero = heroList.get(i);
            String heroLatString = hero.getLatitude();
            String heroLngString = hero.getLongitude();
            double heroLat = Double.parseDouble(heroLatString);
            double heroLng = Double.parseDouble(heroLngString);
            double distance = org.apache.lucene.util.SloppyMath.haversinMeters(heroLat, heroLng, lat, lng);
            if (distance <= 50000) {
                heroListFiltered.add(hero);
            }
        }

        request.setAttribute("tabheroListForIncident", heroListFiltered);
        this.getServletContext().getRequestDispatcher( "/view/incident.jsp").forward( request, response );
    }
}