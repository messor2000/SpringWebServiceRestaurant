<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="locale" var="locale"/>

<div class="col-sm-2 sidenav">
    <c:if test='${sessionScope.get("user").role eq "ADMIN"}'>
        <p><a class="manager" href="/admin/allUsers"><fmt:message key="locale.allUsers">All users</fmt:message></a></p>
        <p><a class="manager" href="/admin/addDish"><fmt:message key="locale.add">Add dish</fmt:message></a></p>
        <p><a class="manager" href="/admin/showAllOrders"><fmt:message key="locale.orders">All orders</fmt:message></a></p>
    </c:if>
    <c:if test='${sessionScope.get("user").role eq "ADMIN" || sessionScope.get("user").role eq "USER"}'>
        <p><a href="${pageContext.request.contextPath}/user/purse"><fmt:message key="locale.purse">Purse</fmt:message></a></p>
        <p><a href="${pageContext.request.contextPath}/user/showOrder"><fmt:message key="locale.watchOrder">Watch order</fmt:message></a></p>
    </c:if>
</div>
