<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>
<fmt:message bundle="${locale}" key="locale.name" var="name"/>
<fmt:message bundle="${locale}" key="locale.category" var="category"/>
<fmt:message bundle="${locale}" key="locale.amount" var="amount"/>
<fmt:message bundle="${locale}" key="locale.add" var="add"/>
<fmt:message bundle="${locale}" key="locale.purse" var="purse"/>
<fmt:message bundle="${locale}" key="locale.allUsers" var="allUsers"/>
<fmt:message bundle="${locale}" key="locale.add" var="add"/>
<fmt:message bundle="${locale}" key="locale.dishPrice" var="price"/>
<fmt:message bundle="${locale}" key="locale.add" var="addDish"/>
<fmt:message bundle="${locale}" key="locale.orders" var="orders"/>
<fmt:message bundle="${locale}" key="locale.watchOrder" var="watchOrder"/>

<div class="col-sm-2 sidenav">
    <c:if test='${sessionScope.get("user").role eq "manager"}'>
        <p><a class="manager" href="FrontController?command=show-all-users">${allUsers}</a></p>
        <p><a class="manager" href="FrontController?command=add-dish">${addDish}</a></p>
        <p><a class="manager" href="FrontController?command=show-all-orders">${orders}</a></p>
    </c:if>
    <c:if test='${sessionScope.get("user").role eq "manager" || sessionScope.get("user").role eq "customer"}'>
        <p><a href="FrontController?command=show-purse">${purse}</a></p>
        <p><a href="FrontController?command=show-order">${watchOrder}</a></p>
    </c:if>
</div>
