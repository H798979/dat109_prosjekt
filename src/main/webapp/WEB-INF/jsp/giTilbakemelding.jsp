<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="no">
<head>
    <meta charset="UTF-8">
    <title>Gi tilbakemelding – ${forelesning.navn}</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 600px; margin: 40px auto; padding: 0 20px; }
        .feil { color: red; font-weight: bold; }
        label { display: block; margin-top: 15px; font-weight: bold; }
        select, input[type="text"] { width: 100%; padding: 8px; margin-top: 4px; box-sizing: border-box; }
        button { margin-top: 20px; padding: 10px 20px; background-color: #0066cc; color: white; border: none; border-radius: 4px; cursor: pointer; }
        button:hover { background-color: #004999; }
        a { color: #0066cc; }
        .vurdering-gronn { color: #4CAF50; }
        .vurdering-gul { color: #FFC107; }
        .vurdering-rod { color: #F44336; }
    </style>
</head>
<body>
    <h1>Gi tilbakemelding</h1>
    <h2>${forelesning.navn} – ${forelesning.sted}</h2>

    <c:if test="${not empty feil}">
        <p class="feil">${feil}</p>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/tilbakemelding/send">
        <input type="hidden" name="forelesningId" value="${forelesning.id}">

        <label for="vurdering">Vurdering:</label>
        <select id="vurdering" name="vurdering" required>
            <option value="">-- Vel vurdering --</option>
            <c:forEach var="v" items="${vurderinger}">
                <c:choose>
                    <c:when test="${v == 'gronn'}">
                        <option value="${v}">Grøn (bra)</option>
                    </c:when>
                    <c:when test="${v == 'GUL'}">
                        <option value="${v}">Gul (middels)</option>
                    </c:when>
                    <c:when test="${v == 'rod'}">
                        <option value="${v}">Raud (dårleg)</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${v}">${v}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <label for="studentToken">Student-token:</label>
        <input type="text" id="studentToken" name="studentToken" required
               placeholder="Skriv inn din unike token">

        <button type="submit">Send tilbakemelding</button>
    </form>

    <p style="margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/forelesninger/vis?id=${forelesning.id}">Tilbake til forelesning</a>
        |
        <a href="${pageContext.request.contextPath}/forelesninger">Tilbake til oversikt</a>
    </p>
</body>
</html>
