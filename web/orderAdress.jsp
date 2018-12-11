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
        New user signup
    </h2>
    <main>
            <form method="post" action="Controller?action=confirmOrder" novalidate="novalidate">
                <p><label for="adress">Your delivery adress</label>
                    <input type="text" id="adress" name="adress" required></p>
                <p><input type="submit" id="confirmOrder" value="Confirm your order!"></p>
            </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
