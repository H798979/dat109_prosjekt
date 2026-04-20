<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<body>
    <h1>Gi tilbakemelding</h1>
    <p><b>${forelesning.navn}</b> – ${forelesning.sted}</p>

    <c:if test="${not empty feil}">
        <p style="color:red">${feil}</p>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/tilbakemelding/send">
        <input type="hidden" name="forelesningId" value="${forelesning.id}"/>

        <p>Vurdering:<br>
            <select name="vurdering" required>
                <option value="">-- Vel vurdering --</option>
                <c:forEach var="v" items="${vurderinger}">
                    <c:choose>
                        <c:when test="${v == 'GRONN'}">
                            <option value="${v}">Grøn (bra)</option>
                        </c:when>
                        <c:when test="${v == 'GUL'}">
                            <option value="${v}">Gul (middels)</option>
                        </c:when>
                        <c:when test="${v == 'ROD'}">
                            <option value="${v}">Raud (dårleg)</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${v}">${v}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </p>

        <p>Student-token:<br>
            <input type="text" name="studentToken" required placeholder="Skriv inn din unike token"></p>

        <p><input type="submit" value="Send tilbakemelding"></p>
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/forelesninger/vis?id=${forelesning.id}">Tilbake til forelesning</a>
        |
        <a href="${pageContext.request.contextPath}/forelesninger">Tilbake til oversikt</a>
    </p>
</body>
