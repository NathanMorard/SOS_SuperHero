<%@ page import="com.demo.controller.connectionSQL" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>

<%
connectionSQL conn = new connectionSQL();
List<String> heroList = conn.getHero();
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SOS SuperHero</title>

    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.3/dist/leaflet.css"
    integrity="sha256-kLaT2GOSpHechhsozzB+flnD+zUyjE2LlfWPgU04xyI="
    crossorigin=""/>
    <link rel="stylesheet" href="CSS/mapstyle.css">

    <script src="https://unpkg.com/leaflet@1.9.3/dist/leaflet.js"
    integrity="sha256-WBkoXOwTeyKclOHuWtc+i2uENFpDZ9YPdf5Hf+D7ewM="
    crossorigin=""></script>
</head>
<body>
    <div class="btntitle">
        <button class="btninscription"><a href="SignInHeroServlet">S'inscire</a> </button>
    </div>

    <div id="title">
        <div>
            <h1 class="title">D&eacute;clarer un incident</h1>
        </div>
    </div>

    <div id="formMap">
        <div class="select">
            <label class="textselect" for="incident-select1">Selectionnez le type d'incident:</label>
            <select name="valueincident1">
                <option value=""></option>
                <% for (String value : conn.getIncident()) { %>
                    <option value="<%= value %>"><%= value %></option>
                <% } %>
            </select>
        </div>
        <h4>Cliquez sur la carte pour indiquer votre position</h4>
        <button>D&eacute;clarer l'incident</button>
    </div>
    <div id="map"></div>

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
        }
        map.on('click', onMapClick);

         function printHero(heroList) {
           // Parcourir la liste de coordonnées et ajouter des marqueurs sur la carte
           for (var i = 0; i < heroList.length; i++) {
             var coords = heroList[i].split(",");
             var lat = parseFloat(coords[0]);
             var lon = parseFloat(coords[1]);
             var redIcon = L.icon({
                 iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png',
                 shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
                 iconSize: [25, 41],
                 iconAnchor: [12, 41],
                 popupAnchor: [1, -34],
                 shadowSize: [41, 41]
             });
             L.marker([lat, lon], {icon: redIcon}).addTo(map);
           }
         }
         printHero(<%= new Gson().toJson(heroList) %>);
    </Script>
</body>
</html>
