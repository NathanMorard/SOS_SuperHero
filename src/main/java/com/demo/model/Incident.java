package com.demo.model;

public class Incident {

    private int idIncidents;
    private String nom_incident;

    public Incident(int idIncidents, String nom_incident) {
        this.idIncidents = idIncidents;
        this.nom_incident = nom_incident;
    }

    public int getIdIncidents() {
        return idIncidents;
    }

    public String getNom_incident() {
        return nom_incident;
    }

    public void setIdIncidents(int idIncidents) {
        this.idIncidents = idIncidents;
    }

    public void setNom_incident(String nom_incident) {
        this.nom_incident = nom_incident;
    }
}
