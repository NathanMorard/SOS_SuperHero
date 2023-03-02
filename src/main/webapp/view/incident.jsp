<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css"><%@include file="/CSS/IncidentStyle.css"%></style>
    <title>Liste h&eacute;ro</title>
</head>
<body>
<h1>Liste des h&eacute;ros dans un rayon de 50km :</h1>

    <c:if test="${empty tabheroListForIncident}">
        <h3>Aucun h&eacute;ros n'est disponible pour cet incident.</h3>
        <p>Fuyez</p>
    </c:if>
    <ul>
        <c:forEach items="${tabheroListForIncident}" var="hero">
            <li><c:out value="${hero.getNameHero()}" />
            <c:out value="${hero.getPhone()}" /></li>
        </c:forEach>
    </ul>


</body>
</html>