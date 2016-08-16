<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Amazoner</title>
</head>
<body>

<div align="center">
    <h1>Search in amazon</h1>
    <form:form method="post" action="/" commandName="queryForm">
        <table>
            <tr>
                <td><form:input path="query" placeholder="Product name"/></td>
                <td><form:select path="category" items="${categoryList}"/></td>
                <td colspan="2" align="center"><input type="submit" value="Search"/></td>
            </tr>
        </table>
    </form:form>
</div>

</body>
</html>