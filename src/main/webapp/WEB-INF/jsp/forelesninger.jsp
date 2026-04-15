<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="no">
<head>
    <meta charset="UTF-8">
    <title>Alle forelesningar</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 800px; margin: 40px auto; padding: 0 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background-color: #f0f0f0; }
        .melding { color: green; font-weight: bold; }
        .feil { color: red; font-weight: bold; }
        a { color: #0066cc; }
        form { margin-top: 30px; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input[type="text"] { width: 100%; padding: 8px; margin-top: 4px; box-sizing: border-box; }
        button { margin-top: 15px; padding: 10px 20px; background-color: #0066cc; color: white; border: none; border-radius: 4px; cursor: pointer; }
        button:hover { background-color: #004999; }
    </style>
</head>
<body>
    <h1>Alle forelesningar</h1>

    <c:if test="${not empty melding}">
        <p class="melding">${melding}</p>
    </c:if>
    <c:if test="${not empty feil}">
        <p class="feil">${feil}</p>
    </c:if>

    <c:choose>
        <c:when test="${empty forelesninger}">
            <p>Ingen forelesningar registrerte.</p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Namn</th>
                        <th>Tidspunkt</th>
                        <th>Stad</th>
                        <th>Handlingar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="f" items="${forelesninger}">
                        <tr>
                            <td>${f.id}</td>
                            <td>${f.navn}</td>
                            <td>${f.tidspunkt}</td>
                            <td>${f.sted}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/forelesninger/vis?id=${f.id}">Vis detaljar</a>
                                |
                                <a href="${pageContext.request.contextPath}/tilbakemelding/skjema?forelesningId=${f.id}">Gi tilbakemelding</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <h2>Opprett ny forelesning</h2>
    <form method="post" action="${pageContext.request.contextPath}/forelesninger/opprett">
        <label for="namn">Namn:</label>
        <input type="text" id="namn" name="namn" required>

        <label for="sted">Stad:</label>
        <input type="text" id="sted" name="sted" required>

        <button type="submit">Opprett</button>
    </form>
</body>
</html>
