<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
<%--suppress CssUnresolvedCustomProperty --%>
    <style type="text/css">
        tr.exceed td{
            color: red;
        }
        tr.notExceed td{
            color: green;
        }
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<c:if test="${!empty mealsWithExceed}">
<table class="tg">
    <tr>
        <th width="40">N</th>
        <th width="40">ID</th>
        <th width="120">DESCRIPTION</th>
        <th width="100">DATE</th>
        <th width="60">CALORIES</th>
        <th width="60"></th>
        <th width="60"></th>

    </tr>
    <c:forEach items="${mealsWithExceed}" var="meal">
        <c:if test="${meal.exceed}"><tr class="exceed"></c:if> <%--fix to ternar--%>
        <c:if test="${!meal.exceed}"><tr class="notExceed"></c:if>
            <td>${mealsWithExceed.indexOf(meal) + 1}</td>
            <td>${meal.id}</td>
            <td>${meal.description}</td>
            <td>${meal.dateTime}</td> <%-- where this to be formatted???--%>
            <td>${meal.calories}</td>
            <td><a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Edit</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</c:if>

<h3> Add new meal </h3>

<form method="POST" action='meals' name="formAddMeal">
    Date and Time (YYYY-MM-DD HH:MM): <input
        type="text" name="dateTime"
        value="<c:out value="${meal.dateTime}" />" /> <br />
    Description : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />" /> <br />
    Calories : <input
        type="text" name="calories"
        value="<c:out value="${meal.calories}" />" /> <br />
        <input type="submit" value="Add meal" />
</form>
</body>
</html>