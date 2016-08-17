<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Amazoner</title>
</head>
<body>

<h2>Results:</h2>
<c:if test="${empty itemsInfo}">
    <p>There is no searching results.</p>
</c:if>
<c:if test="${not empty itemsInfo}">
    <ul>
        <c:forEach items="${itemsInfo}" var="item">
            <li>
                <p>${item.title}</p>
                <p>${item.price}</p>
            </li>
        </c:forEach>
    </ul>
</c:if>

<p>Pages: </p>
<c:forEach begin="1" end="${pagesAmount}" var="page">
    <a href="/result/${page}"><c:out value="${page}"/></a>
</c:forEach>

</body>
</html>