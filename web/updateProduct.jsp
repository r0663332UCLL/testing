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
        Add product
    </h2>
    <main>
        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form method="post" action="Controller?action=updateProductPost&productToUpdate=<c:out value='${param.productToUpdate}'/>" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="name">Name</label>
                <input type="text" id="name" name="name" value="${productObject.name}" required value=""></p>
            <p><label for="description">Description</label>
                <input type="text" id="description" name="description" value="${productObject.description}" required value=""></p>
            <p><label for="price">Price</label>
                <input type="text" id="price" name="price" value="${productObject.price}" required value=""></p>
            <p><input type="submit" id="update" value="update"></p>

        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
