<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp"/>
    <h2>
        Check Password
    </h2>
    <main>
            <ul>
                <li>${resultString}</li>
            </ul>
        <form method="post" action="Controller?action=checkPassword&personToCheck=<c:out value='${param.personToCheck}'/>" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="password">Password</label>
                <input type="text" id="password" name="password" required value=""></p>
            <p><input type="submit" id="check password" value="check password"></p>

        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
