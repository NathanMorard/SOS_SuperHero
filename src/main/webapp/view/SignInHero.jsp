<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../CSS/SignInStyle.css">
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
        <p class="email">Utilisez votre Adresse mail: </p>
        <div class="inputs">
            <input  name="txtname" type="text" placeholder="Name">
            <input  name="txtphone" type="number" placeholder="Phone">
            <input  name="intlat" type="text" placeholder="latitude">
            <input  name="intlng" type="text" placeholder="longitude">

            <input  name="txtpassword1" type="password" placeholder="Password">
            <input  name="txtpassword2" type="password" placeholder="Password">
        </div>
        <label for="incident-select1">Selectionnez le type d'incident:</label>
        <select name="valueincident1" id="incident-select1">
            <option value=""></option>
            <option value="Incendie">Incendie</option>
            <option value="Accident_routier">Accident routier</option>
            <option value="Accident_fluvial">Accident fluvial</option>
            <option value="Accident_aérien">Accident aérien</option>
            <option value="Eboulement">Eboulement</option>
            <option value="Invasion_serpent">Invasion de serpent</option>
            <option value="Fuite_gaz">Fuite de gaz</option>
            <option value="Manifestation">Manifestation</option>
            <option value="Braquage">Braquage</option>
            <option value="Evasion d’un prisonnier">Evasion d’un prisonnier</option>
        </select>

        <label for="incident-select2">Selectionnez le type d'incident:</label>
        <select name="Valueincident2" id="incident-select2">
            <option value=""></option>
            <option value="Incendie">Incendie</option>
            <option value="Accident_routier">Accident routier</option>
            <option value="Accident_fluvial">Accident fluvial</option>
            <option value="Accident_aérien">Accident aérien</option>
            <option value="Eboulement">Eboulement</option>
            <option value="Invasion_serpent">Invasion de serpent</option>
            <option value="Fuite_gaz">Fuite de gaz</option>
            <option value="Manifestation">Manifestation</option>
            <option value="Braquage">Braquage</option>
            <option value="Evasion d’un prisonnier">Evasion d’un prisonnier</option>
        </select>

        <label for="incident-select3">Selectionnez le type d'incident:</label>
        <select name="valueincident3" id="incident-select3">
            <option value=""></option>
            <option value="Incendie">Incendie</option>
            <option value="Accident_routier">Accident routier</option>
            <option value="Accident_fluvial">Accident fluvial</option>
            <option value="Accident_aérien">Accident aérien</option>
            <option value="Eboulement">Eboulement</option>
            <option value="Invasion_serpent">Invasion de serpent</option>
            <option value="Fuite_gaz">Fuite de gaz</option>
            <option value="Manifestation">Manifestation</option>
            <option value="Braquage">Braquage</option>
            <option value="Evasion d’un prisonnier">Evasion d’un prisonnier</option>
        </select>
        <button  class="inscription" type="submit" >S'inscrire </button>

          <div id="map"></div>
    </form>



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
