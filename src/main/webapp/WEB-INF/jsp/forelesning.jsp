<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<body>
    <h1>${forelesning.navn}</h1>

    <c:if test="${not empty melding}">
        <p style="color:green">${melding}</p>
    </c:if>
    <c:if test="${not empty feil}">
        <p style="color:red">${feil}</p>
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
                <tr><th>Vurdering</th><th>Antal</th><th>Fordeling</th></tr>
                <c:set var="breiddegronn" value="${statistikk.gronn * 200 / statistikk.totalt}" />
                <c:set var="breiddeGul" value="${statistikk.gul * 200 / statistikk.totalt}" />
                <c:set var="breidderod" value="${statistikk.rod * 200 / statistikk.totalt}" />
                <tr>
                    <td>Grøn</td>
                    <td>${statistikk.gronn}</td>
                    <td><span class="bar gronn" data-width="${breiddegronn}" style="height:20px;display:inline-block;background-color:#4CAF50">&nbsp;</span></td>
                </tr>
                <tr>
                    <td>Gul</td>
                    <td>${statistikk.gul}</td>
                    <td><span class="bar gul" data-width="${breiddeGul}" style="height:20px;display:inline-block;background-color:#FFC107">&nbsp;</span></td>
                </tr>
                <tr>
                    <td>Raud</td>
                    <td>${statistikk.rod}</td>
                    <td><span class="bar rod" data-width="${breidderod}" style="height:20px;display:inline-block;background-color:#F44336">&nbsp;</span></td>
                </tr>
            </table>
            <p><b>Totalt:</b> ${statistikk.totalt} tilbakemeldingar</p>
        </c:otherwise>
    </c:choose>

    <p>
        <a href="${pageContext.request.contextPath}/tilbakemelding/skjema?forelesningId=${forelesning.id}">Gi tilbakemelding</a>
        |
        <a href="${pageContext.request.contextPath}/forelesninger">Tilbake til oversikt</a>
    </p>

    <script>
        document.querySelectorAll('.bar[data-width]').forEach(function(el) {
            el.style.width = el.getAttribute('data-width') + 'px';
        });
    </script>
</body>
