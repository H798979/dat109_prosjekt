<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<body>
    <div style="text-align:right">
        <a href="${pageContext.request.contextPath}/login">Admin</a>
    </div>

    <h1>Alle forelesningar</h1>

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
                <tr><th>ID</th><th>navn</th><th>Tidspunkt</th><th>Stad</th><th>Handlingar</th></tr>
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
            </table>
        </c:otherwise>
    </c:choose>
</body>
