<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="no">
<head>
    <meta charset="UTF-8">
    <title>Forelesning – ${forelesning.navn}</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 800px; margin: 40px auto; padding: 0 20px; }
        .melding { color: green; font-weight: bold; }
        .feil { color: red; font-weight: bold; }
        table { border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background-color: #f0f0f0; }
        .bar { height: 20px; display: inline-block; border-radius: 3px; }
        .groenn { background-color: #4CAF50; }
        .gul { background-color: #FFC107; }
        .roed { background-color: #F44336; }
        a { color: #0066cc; }
    </style>
</head>
<body>
    <h1>${forelesning.navn}</h1>

    <c:if test="${not empty melding}">
        <p class="melding">${melding}</p>
    </c:if>
    <c:if test="${not empty feil}">
        <p class="feil">${feil}</p>
    </c:if>

    <table>
        <tr><th>ID</th><td>${forelesning.id}</td></tr>
        <tr><th>Tidspunkt</th><td>${forelesning.tidspunkt}</td></tr>
        <tr><th>Stad</th><td>${forelesning.sted}</td></tr>
    </table>

    <h2>Statistikk</h2>

    <c:choose>
        <c:when test="${statistikk.totalt == 0}">
            <p>Ingen tilbakemeldingar enno.</p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th>Vurdering</th>
                        <th>Antal</th>
                        <th>Fordeling</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Grøn</td>
                        <td>${statistikk.groenn}</td>
                        <td><span class="bar groenn" style="width: ${statistikk.totalt > 0 ? (statistikk.groenn * 200 / statistikk.totalt) : 0}px;">&nbsp;</span></td>
                    </tr>
                    <tr>
                        <td>Gul</td>
                        <td>${statistikk.gul}</td>
                        <td><span class="bar gul" style="width: ${statistikk.totalt > 0 ? (statistikk.gul * 200 / statistikk.totalt) : 0}px;">&nbsp;</span></td>
                    </tr>
                    <tr>
                        <td>Raud</td>
                        <td>${statistikk.roed}</td>
                        <td><span class="bar roed" style="width: ${statistikk.totalt > 0 ? (statistikk.roed * 200 / statistikk.totalt) : 0}px;">&nbsp;</span></td>
                    </tr>
                </tbody>
            </table>
            <p><strong>Totalt:</strong> ${statistikk.totalt} tilbakemeldingar</p>
        </c:otherwise>
    </c:choose>

    <p>
        <a href="${pageContext.request.contextPath}/tilbakemelding/skjema?forelesningId=${forelesning.id}">Gi tilbakemelding</a>
        |
        <a href="${pageContext.request.contextPath}/forelesninger">Tilbake til oversikt</a>
    </p>
</body>
</html>
