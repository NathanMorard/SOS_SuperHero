<%@ page import="java.util.List" %>
<%@ page import="com.demo.model.Incident" %>

<%
    List<Incident> incidentList = (List<Incident>) request.getAttribute("incidentList");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css"><%@include file="/CSS/SignInStyle.css"%></style>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.3/dist/leaflet.css"
    integrity="sha256-kLaT2GOSpHechhsozzB+flnD+zUyjE2LlfWPgU04xyI="
    crossorigin=""/>
    <title>S'incrire</title>

    <script src="https://unpkg.com/leaflet@1.9.3/dist/leaflet.js"
    integrity="sha256-WBkoXOwTeyKclOHuWtc+i2uENFpDZ9YPdf5Hf+D7ewM="
    crossorigin=""></script>
</head>
<body>
    <form method="post" action="/SignInHeroServlet">
        <h1>S'inscrire</h1>
        <% if (request.getAttribute("error") != null && (Boolean)request.getAttribute("error")) { %>
            <div class="error-message">
                <span><%= request.getAttribute("errorMessage") %></span>
            </div>
        <% } %>
        <p class="email">Utilisez votre Adresse mail: </p>
        <div class="inputs">
            <input  name="txtname" type="text" placeholder="Name">
            <input  name="txtphone" type="text" placeholder="Phone">
            <input  name="intlat" type="text" placeholder="latitude">
            <input  name="intlng" type="text" placeholder="longitude">

            <input  name="txtpassword1" type="password" placeholder="Password">
            <input  name="txtpassword2" type="password" placeholder="Password">
        </div>

        <label for="incident-select1">Selectionnez le type d'incident:</label>
        <select name="valueincident1">
            <option value=""></option>
                <% for (Incident incident : incidentList) {
                    int id = incident.getIdIncidents();
                    String name = incident.getNom_incident();%>
                    <option value="<%= id%>"><%= name %></option>
                <% } %>
        </select>

        <label for="incident-select2">Selectionnez le type d'incident:</label>
        <select name="valueincident2">
            <option value=""></option>
                <% for (Incident incident : incidentList) {
                    int id = incident.getIdIncidents();
                    String name = incident.getNom_incident();%>
                    <option value="<%= id%>"><%= name %></option>
                <% } %>
        </select>

        <label for="incident-select3">Selectionnez le type d'incident:</label>
        <select name="valueincident3">
            <option value=""></option>
                <% for (Incident incident : incidentList) {
                    int id = incident.getIdIncidents();
                    String name = incident.getNom_incident();%>
                    <option value="<%= id%>"><%= name %></option>
                <% } %>
        </select>


        <button  class="inscription" type="submit" >S'inscrire </button>

          <div id="map"></div>
    </form>



    <Script>
        var map = L.map('map');
        map.setView([43.529742, 5.447427], 13);

        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 18,
            attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        }).addTo(map);


        navigator.geolocation.watchPosition(success, error);

        let marker, circle, zoomed;

        function success(pos){
            const lat = pos.coords.latitude
            const lng = pos.coords.longitude
            const accuracy = pos.coords.accuracy

            if (marker){
                map.removeLayer(marker);
                map.removeLayer(circle);
            }
            map.setView([lat, lng], 19);
            marker = L.marker([lat, lng]).addTo(map);
            circle = L.circle([lat, lng], 30000).addTo(map);
            document.getElementsByName("intlat")[0].value = lat;
            document.getElementsByName("intlng")[0].value = lng;


            map.setView([lat, lng]);

        }

        function error(){
            if(er.code === 1){
                alert("Veuillez autorisez l'accès à la géolocalisation")
            }else{
                alert("Géolocalisation inconnu")
            }

        }

        function onMapClick(event) {
          var latlng = event.latlng;
          var latitude = latlng.lat;
          var longitude = latlng.lng;
          var message = "Vous avez cliqué sur les coordonnées : " + latitude + ", " + longitude;
          if(marker){
            map.removeLayer(marker);
            map.removeLayer(circle);
          }
          marker = L.marker([latitude, longitude]).addTo(map);
          circle = L.circle([latitude, longitude], 30000).addTo(map);
          document.getElementsByName("intlat")[0].value = latitude;
          document.getElementsByName("intlng")[0].value = longitude;
        }
        map.on('click', onMapClick);
    </Script>


</body>
</html>
