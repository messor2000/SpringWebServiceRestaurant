<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<fmt:setLocale value="${sessionScope.language}"/>--%>
<fmt:setBundle basename="locale" var="locale"/>
<%--<fmt:message bundle="${locale}" key="locale.footer" var="footer"/>--%>
<footer class="container-fluid text-right footer">
    <p id="child"><fmt:message key="locale.footer">Footer</fmt:message></p>
</footer>