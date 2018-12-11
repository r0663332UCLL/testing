<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Your cart</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp"/>
    <h2>
        The contents of your cart
    </h2>
    <main>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
            </tr>
            <c:forEach var="product" items="${cart}">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td><a href="Controller?action=removeFromCart&productToRemove=<c:out value='${product.productId}'/>">remove from cart</a></td>
                </tr>
            </c:forEach>
            <caption>Product Overview</caption>
        </table>
        <h3>The subtotal of your order: <c:out value='${total}'/></h3>
        <a href="Controller?action=placeOrder"><h3>Place order</h3></a>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>