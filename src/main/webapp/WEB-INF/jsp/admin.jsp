<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<body>
    <h1>Administrer forelesningar</h1>

    <c:if test="${not empty melding}">
        <p style="color:green">${melding}</p>
    </c:if>
    <c:if test="${not empty feil}">
        <p style="color:red">${feil}</p>
    </c:if>

    <c:choose>
        <c:when test="${empty forelesninger}">
            <p>Ingen forelesningar registrerte.</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr><th>ID</th><th>Navn</th><th>Tidspunkt</th><th>Stad</th><th>Handlingar</th></tr>
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
                            |
                            <form method="post" action="${pageContext.request.contextPath}/forelesninger/slett" style="display:inline">
                                <input type="hidden" name="id" value="${f.id}"/>
                                <input type="submit" value="Slett" onclick="return confirm('Er du sikker på at du vil slette denne forelesningen?')"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

    <h2>Opprett ny forelesning</h2>
    <form method="post" action="${pageContext.request.contextPath}/forelesninger/opprett">
        <p>navn:<br>
            <input type="text" name="navn" required></p>
        <p>Tidspunkt:<br>
            <input type="datetime-local" name="tidspunkt" required></p>
        <p>Stad:<br>
            <input type="text" name="sted" required></p>
        <p><input type="submit" value="Opprett"></p>
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/forelesninger">Tilbake til oversikt</a>
        |
        <a href="${pageContext.request.contextPath}/loggut">Logg ut</a>
    </p>
</body>
