<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<h1>Liste des h&eacute;ros dans un rayon de 50km :</h1>

    <ul>

        <c:forEach items="${tabheroListForIncident}" var="hero">
            <li><c:out value="${hero.getNameHero()}" />
            <c:out value="${hero.getPhone()}" /></li>
        </c:forEach>

    </ul>

</body>
</html>