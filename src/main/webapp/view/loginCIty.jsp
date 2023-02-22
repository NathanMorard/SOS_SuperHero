<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion city</title>
    <link rel="stylesheet" href="styleLog.css">
</head>
    <form>
        <h1>Se connecter</h1>
        <p class="email">Utilisez votre Adresse mail: </p>
        <div class="inputs">
            <input type="email" placeholder="Email">
            <input type="password" placeholder="Password">
        </div>

        <p class="Inscription">Je n'ai pas de compte , je <a href="SigInCityServlet"> m'inscris </a> </p>
        <div align="center">
            <button  class="connexion" type="submit">Se connecter </button>
        </div>
    </form>
</body>
</html>