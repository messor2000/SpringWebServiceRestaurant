<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 25.09.2021
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<fmt:setBundle basename="locale" var="locale"/>


<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="locale.orders">All Orders</fmt:message></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="shortcut icon" href="../../static/image/logo.ico"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../static/css/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../static/js/jssor.slider.mini.js"></script>
</head>
<body onload="active()">

<c:import url="template/navbar.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("user").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/leftbar.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <h1><fmt:message key="locale.orders">All Orders</fmt:message></h1>

            <div class="col-sm-8 text-left mainContent">
                <div class="row">
                        <jsp:useBean id="pagedListHolder" scope="request"
                                     type="org.springframework.beans.support.PagedListHolder"/>
                        <c:url value="/admin/showAllOrders" var="pagedLink">
                            <c:param name="p" value="~"/>
                        </c:url>

                        <tg:paging pagedListHolder="${pagedListHolder}"
                                   pagedLink="${pagedLink}"/>
                        <table class="table table-bordered">
                            <tr>
                                <th scope="col" width="20px"><fmt:message
                                        key="locale.orderNumber">Order number</fmt:message></th>
                                <th scope="col"><fmt:message key="locale.status">Status</fmt:message></th>
                                <th scope="col"><fmt:message key="locale.username">User username</fmt:message></th>
                                <th scope="col"><fmt:message key="locale.approve">Approve</fmt:message></th>
                            </tr>
                            <c:forEach items="${pagedListHolder.pageList}" var="item">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.status}</td>
                                    <td>${item.user.username}</td>
                                    <td>
                                        <div class="form-group">
                                            <div class="col-sm-offset-3 col-sm-7">
                                                <a href="/admin/approveOrder">
                                                    <button type="button" class="btn btn-default"
                                                            data-dismiss="modal"><fmt:message
                                                            key="locale.approve">Approve</fmt:message>
                                                    </button>
                                                </a>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <tg:paging pagedListHolder="${pagedListHolder}"
                                   pagedLink="${pagedLink}"/>
                    </div>
            </div>
        </div>
    </div>
</div>
<c:import url="template/footer.jsp"/>
</body>
</html>


