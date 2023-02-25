<%@ page import="com.demo.controller.connectionSQL" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>


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
    <div id="map"></div>

<script type="text/javascript">


 var map = L.map('map').setView([43.529742, 5.447427], 13);
 L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
   attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
     '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
     'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
   maxZoom: 18,
 }).addTo(map);


 let marker, circle, zoomed;

 // Récupération de la liste des coordonnées des héros depuis la base de données
 <% connectionSQL conn = new connectionSQL();
    List<String> heroList = conn.getHero();
 %>

 function printHero(heroList) {
   // Parcourir la liste de coordonnées et ajouter des marqueurs sur la carte
   console.log('Avant boucle');
   for (var i = 0; i < heroList.length; i++) {
     var coords = heroList[i].split(",");
     console.log(heroList);
     console.log("dans boucle");
     var lat = parseFloat(coords[0]);
     var lon = parseFloat(coords[1]);
     alert(coords);
     L.marker([lat, lon], {icon: redIcon).addTo(map);
   }
 }

 // Appel de la fonction avec la liste des héros récupérée précédemment
 console.log('Appel fonction');
 console.log("heroList:", <%= new Gson().toJson(heroList) %>);
 printHero(<%= new Gson().toJson(heroList) %>);

</script>

</body>
</html>
