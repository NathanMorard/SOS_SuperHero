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
    <link rel="stylesheet" href="../CSS/mapstyle.css">

    <script src="https://unpkg.com/leaflet@1.9.3/dist/leaflet.js"
    integrity="sha256-WBkoXOwTeyKclOHuWtc+i2uENFpDZ9YPdf5Hf+D7ewM="
    crossorigin=""></script>
</head>
<body>
    <div class="title">
        <h1>Veuillez indiquer sur la carte votre localisation</h1>
    </div>

    <div id="map"></div>
    <Script>
        var map = L.map('map');
        map.setView([43.529742, 5.447427], 13);

        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
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

            marker = L.marker([lat, lng]).addTo(map);
            circle = L.circle([lat, lng], 30000).addTo(map);

            if (!zoomed){
                zoomed = map.fitBounds(circle.getBounds)
            }

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
    </Script>
</body>
</html>
