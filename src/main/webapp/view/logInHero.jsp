<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="../CSS/LogInStyle.css">
</head>
<body>
    <form>
        <h1>Se connecter</h1>
        <p class="email">Utilisez votre Adresse mail: </p>
        <div class="inputs">
            <input type="email" placeholder="Email">
            <input type="password" placeholder="Password">
        </div>

        <p class="Inscription">Je nai pas de comptes, je <a href="SignInHeroServlet">minscris</a></p>
        <div align="center">
            <button  class="connexion" type="submit">Se connecter </button>
        </div>
    </form>
</body>
</html>