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
        <th width="120">DESCRIPTION</th>
        <th width="100">DATE</th>
        <th width="60">CALORIES</th>
        <th width="60"></th>
        <th width="60"></th>

    </tr>
    <c:forEach items="${mealsWithExceed}" var="meal">
        <tr class="${meal.isExceed()?'exceed':'notExceed'}">
            <td>${mealsWithExceed.indexOf(meal) + 1}</td>
            <td>${meal.description}</td>
            <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td> <%-- fix to normal formatter--%>
            <td>${meal.calories}</td>
            <td><a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Edit</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</c:if>
<h3> Add/edit meal </h3>

<form method="POST" action='meals' name="formAddMeal">
    <c:if test="${!empty mealForEdit}">
        <input style="display: none" type="text" name="id" readonly="true" size="10" value="<c:out value="${mealForEdit.id}" />" />
    </c:if>
    <input type="text" name="dateTime" value="<c:out value="${mealForEdit.dateTime.toLocalDate()} ${mealForEdit.dateTime.toLocalTime()}" />" />  Date and Time (YYYY-MM-DD HH:MM)<br />
    <input type="text" name="description" value="<c:out value="${mealForEdit.description}" />" />  Description <br />
    <input type="text" name="calories" value="<c:out value="${mealForEdit.calories}" />" /> Calories <br />
    <input type="submit" value="${!empty mealForEdit?'Edit meal':'Add meal'}" />
</form>
</body>
</html>