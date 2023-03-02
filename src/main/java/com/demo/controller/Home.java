package com.demo.controller;

import com.demo.model.Incident;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static java.lang.System.out;

public class Home extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConnectionSQL conn = new ConnectionSQL();
        List<Incident> incidentList = conn.getIncident();
        List<String> heroList = conn.getHero();
        if(incidentList==null || incidentList.isEmpty()) {
            System.out.println("c'est null ou vide !!!!!!!!!");
        }
        request.setAttribute("incidentList", incidentList);
        request.setAttribute("heroList", heroList);
        request.getRequestDispatcher("/view/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
