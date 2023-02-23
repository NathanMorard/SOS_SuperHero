package com.demo.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;

import static java.lang.System.out;

@Controller
public class LeafletController {

    @GetMapping("/leaflet")
    @ResponseBody
    public JSONObject getLeafletMap() {
        JSONObject map = new JSONObject();
        JSONArray layers = new JSONArray();

        JSONObject osmLayer = new JSONObject();
        osmLayer.put("url", "https://tile.openstreetmap.org/{z}/{x}/{y}.png");
        osmLayer.put("maxZoom", 19);
        osmLayer.put("attribution", "&copy; <a href=\"http://www.openstreetmap.org/copyright\">OpenStreetMap</a>");
        layers.put(osmLayer);

        map.put("layers", layers);

        JSONObject view = new JSONObject();
        view.put("center", new JSONArray().put(43.529742).put(5.447427));
        view.put("zoom", 13);
        map.put("view", view);

        JSONObject geoLocation = new JSONObject();
        geoLocation.put("watchPosition", true);
        geoLocation.put("success", "onSuccess");
        geoLocation.put("error", "onError");
        map.put("geolocation", geoLocation);

        JSONObject events = new JSONObject();
        events.put("click", "onMapClick");
        map.put("events", events);

        return map;
    }

    public void onSuccess(Object position) {
        // Traiter les données de positionnement réussi
    }

    public void onError(Object error) {
        // Traiter les erreurs de géolocalisation
    }

    public void onMapClick(JSONObject event) {
        JSONArray latlng = event.getJSONArray("latlng");
        double latitude = latlng.getDouble(0);
        double longitude = latlng.getDouble(1);
        String message = "Vous avez cliqué sur les coordonnées : " + latitude + ", " + longitude;
        out.println(message);
        // afficher une alerte avec les coordonnées
        String script = "<script>alert('" + message.replaceAll("'", "\\\\'") + "');</script>";
        out.println(script);
    }



}
