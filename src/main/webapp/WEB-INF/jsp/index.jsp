<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Amazoner</title>
</head>
<body>

<div align="center">
    <form:form action="/" method="post" commandName="queryForm">
        <table>
            <tr>
                <td colspan="2" align="center"><h2>Spring MVC Amazoner</h2></td>
            </tr>
            <tr>
                <td><form:input path="query" placeholder="Product name"/></td>
                <td><form:select path="category" items="${categoryList}"/></td>
                <td colspan="2" align="center"><input type="submit" value="Search"/></td>
            </tr>
        </table>
    </form:form>
</div>

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

    <p>Pages: </p>
    <c:forEach begin="1" end="${pagesAmount}" var="page">
        <a href="/${page}"><c:out value="${page}"/></a>
    </c:forEach>

</c:if>



</body>
</html>