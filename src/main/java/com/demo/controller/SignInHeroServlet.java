package com.demo.controller;

import com.demo.model.Incident;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/SignInHeroServlet")
public class SignInHeroServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConnectionSQL conn = new ConnectionSQL();
        List<Incident> incidentList = conn.getIncident();
        request.setAttribute("incidentList", incidentList);
        request.getRequestDispatcher("/view/SignInHero.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("txtname");
        String incident1 = request.getParameter("valueincident1");
        String incident2 = request.getParameter("valueincident2");
        String incident3 = request.getParameter("valueincident3");
        String phone = request.getParameter("txtphone");
        String lat = request.getParameter("intlat");
        String lng = request.getParameter("intlng");
        String password1 = request.getParameter("txtpassword1");
        String password2 = request.getParameter("txtpassword2");
        String errorMessage = "";

        if (name == null || name.isEmpty()) {
            errorMessage += "Le login est obligatoire";
        }

        if (password1 == null || password1.isEmpty()) {
            errorMessage += "Le mot de passe est obligatoire";
        }

        if (password2 == null || password2.isEmpty()) {
            errorMessage += "La confirmation de mot de passe est obligatoire";
        } else if (!password1.equals(password2)) {
            errorMessage += "Les mots de passe sont différents";
        }

        if (incident1 == null || incident1.isEmpty()) {
            errorMessage += "Veuillez renseigner au moins un IncidentServlet.";
        }

        if (!errorMessage.isEmpty()) {
            request.setAttribute("error", true);
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher( "/view/SignInHero.jsp").forward( request, response );
        } else if (errorMessage.isEmpty()) {
            ConnectionSQL cc = new ConnectionSQL();
            cc.insertHero(name, incident1, incident2, incident3, phone, lat, lng);
            response.sendRedirect("index.jsp");
        }


    }
}