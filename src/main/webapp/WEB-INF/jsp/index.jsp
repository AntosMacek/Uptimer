<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<ul>
    <c:forEach items="${itemList}" var="item">
        <li>
            <p>${item}</p>
            <p>${item.price}</p>
        </li>
    </c:forEach>
</ul>

</body>
</html>