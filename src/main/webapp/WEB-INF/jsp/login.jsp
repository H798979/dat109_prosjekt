<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<body>
    <h1>Admin innlogging</h1>

    <c:if test="${not empty feil}">
        <p style="color:red">${feil}</p>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/login">
        <p>Brukarnavn:<br>
            <input type="text" name="brukarnavn" required></p>
        <p>Passord:<br>
            <input type="password" name="passord" required></p>
        <p><input type="submit" value="Logg inn"></p>
    </form>

    <p><a href="${pageContext.request.contextPath}/forelesninger">Tilbake til oversikt</a></p>
</body>
