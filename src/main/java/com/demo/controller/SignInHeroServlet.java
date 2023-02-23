package com.demo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/SignInHeroServlet")
public class SignInHeroServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/view/SignInHero.jsp");
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

        if (name == null || name.isEmpty()) {
            System.out.println("Le login n'est pas renseigné");
            request.setAttribute("error", true);
            doGet(request, response);
            return;
        }

        if (password1 == null || password1.isEmpty()) {
            System.out.println("Le mot de passe est obligatoire");
            request.setAttribute("error", true);
            request.setAttribute("errorMessage", "Le mot de passe est obligatoire");
            doGet(request, response);
            return;
        }

        if (password2 == null || password2.isEmpty()) {
            System.out.println("La confirmation de mot de passe est obligatoire");
            request.setAttribute("error", true);
            request.setAttribute("errorMessage", "La confirmation de mot de passe est obligatoire");
            doGet(request, response);
            return;
        } else if (!password1.equals(password2)) {
            System.out.println("Les mots de passe sont différents");
            request.setAttribute("error", true);
            request.setAttribute("errorMessage", "Les mots de passe sont différents");
            doGet(request, response);
            return;
        }

        connectionSQL cc = new connectionSQL();
        cc.getHero(name, incident1, incident2, incident3, phone, lat, lng);
        response.sendRedirect("/view/map.jsp");
    }
}
