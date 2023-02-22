package com.demo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import java.io.IOException;

@WebServlet("/SignInHeroServlet")
public class SignInHeroServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/view/SignInHero.jsp");;
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("txtname");
        String incident1 = request.getParameter("valueincident1");
        String incident2 = request.getParameter("valueincident2");
        String incident3 = request.getParameter("valueincident3");
        double  phone = Integer.parseInt(request.getParameter("txtphone"));
        double  lat = Integer.parseInt(request.getParameter("intlat"));
        double  lng = Integer.parseInt(request.getParameter("intlng"));
        String password1 = request.getParameter("txtpassword1");
        String password2 = request.getParameter("txtpassword2");

        if (name == null || name.isEmpty()) {

            System.out.println("Le login n'est pas renseigné'");
            request.setAttribute("error", true);
            doGet(request, response);
        }
        if (password1 == null || password1.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le mot de passe est obligatoire", "Erreur", JOptionPane.WARNING_MESSAGE);
            System.out.println("Le mot de passe est obligatoire");
        }
        if (password2 == null || password2.isEmpty()) {
            System.out.println("La confirmation de mot de passe est obligatoire");
        } else if (!password1.equals(password2)) {
            System.out.println("Les mots de passe sont différents");
        }

        connectionSQL cc =  new connectionSQL();
        cc.getHero(name, incident1, incident2, incident3, phone, lat, lng);
        doGet(request, response);
    }
}
