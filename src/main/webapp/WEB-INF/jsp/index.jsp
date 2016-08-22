<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
    Currency:
    <select id="selectCurrency">
    </select>
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
                <p class="price">${item.price}</p>
            </li>
        </c:forEach>
    </ul>

    <p>Pages: </p>
    <c:forEach begin="1" end="${pagesAmount}" var="page">
        <a href="/${page}"><c:out value="${page}"/></a>
    </c:forEach>

</c:if>

<script>
    $(document).ready(function () {
        var select = document.getElementById("selectCurrency");
        var options = ["AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "DKK", "EUR", "GBP", "HKD", "HRK", "HUD", "IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "ZAR"];

        for (var i = 0; i < options.length; i++) {
            var opt = options[i];
            var el = document.createElement("option");
            el.textContent = opt;
            el.value = opt;
            select.appendChild(el);
        }
    });

    $("#selectCurrency").on('change', function() {

        var selectedCurrency = this.value;

        $(".price").html(selectedCurrency);

//        var demo = function(data) {
//            fx.rates = data.rates;
//            var rate = fx(1).from("USD").to(selectedCurrency);
//            console.log("$1 = " + selectedCurrency + rate.toFixed(4));
//        };
//
//        $.getJSON("http://api.fixer.io/latest?base=USD", demo);
    })
</script>

</body>
</html>