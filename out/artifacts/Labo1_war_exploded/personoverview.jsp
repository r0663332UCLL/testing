<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp"/>
    <h2>
        Users Overview
    </h2>
    <main>
        <table>
            <tr>
                <th>E-mail</th>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
            <c:forEach var="persoon" items="${personen}">
                <tr>
                    <td>${persoon.email}</td>
                    <td>${persoon.firstName}</td>
                    <td>${persoon.lastName}</td>
                    <td><a href="Controller?action=removePerson&personToRemove=${persoon.userid}">remove</a></td>
                    <td><a href="Controller?action=checkPasswordInput&personToCheck=${persoon.userid}">check password</a></td>
                </tr>
            </c:forEach>
            <caption>Users Overview</caption>
        </table>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>