<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/mealDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <div class="jumbotron">
        <div class="container">
            <div class="shadow">
                <h3><spring:message code="meal.title"/></h3>

                <form method="post" action="meals/filter">
                    <dl>
                        <dt><spring:message code="meal.startDate"/>:</dt>
                        <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
                    </dl>
                    <dl>
                        <dt><spring:message code="meal.endDate"/>:</dt>
                        <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
                    </dl>
                    <dl>
                        <dt><spring:message code="meal.startTime"/>:</dt>
                        <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
                    </dl>
                    <dl>
                        <dt><spring:message code="meal.endTime"/>:</dt>
                        <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
                    </dl>
                    <button type="submit"><spring:message code="meal.filter"/></button>
                </form>
                <hr>
                <a href="meals/create"><spring:message code="meal.add"/></a>
                <hr>
                <table border="1" cellpadding="8" cellspacing="0">
                    <thead>
                    <tr>
                        <th><spring:message code="meal.dateTime"/></th>
                        <th><spring:message code="meal.description"/></th>
                        <th><spring:message code="meal.calories"/></th>
                        <th colspan="2"><spring:message code="common.actions"/></th>
                    </tr>
                    </thead>
                    <c:forEach items="${meals}" var="meal">
                        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                        <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                            <td>
                                    <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                                    <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                                    <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                                    ${fn:formatDateTime(meal.dateTime)}
                            </td>
                            <td>${meal.description}</td>
                            <td>${meal.calories}</td>
                            <td><a href="meals/update?id=${meal.id}"><spring:message code="common.update"/></a></td>
                            <td><a href="meals/delete?id=${meal.id}"><spring:message code="common.delete"/></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>
<%--<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><spring:message code="meal.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message code="user.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="user.name"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><spring:message code="user.email"/></label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="<spring:message code="user.email"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3"><spring:message code="user.password"/></label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password" placeholder="<spring:message code="user.password"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>--%>
</body>
</html>