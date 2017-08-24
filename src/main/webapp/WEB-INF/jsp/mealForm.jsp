<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fn1" uri="http://topjava.javawebinar.ru/functions" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Meal</title>
    <link rel="stylesheet" href="css/style.css">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><a href="meals">Home</a></h3>
    <h2>${action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
    <hr>
    <form method="post" action="meals/save">
        <jsp:useBean id="meal" scope = "request" class="ru.javawebinar.topjava.model.Meal"/>
        <input type="hidden" type="number" name="id" value="${meal.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" value="${fn1:formatDateTime(meal.dateTime)}" name="dateTime"></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${meal.description}" size=40 name="description"></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" value="${meal.calories}" name="calories"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
