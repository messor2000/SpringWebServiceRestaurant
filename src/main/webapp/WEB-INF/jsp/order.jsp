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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="locale" var="locale"/>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="locale.order">Order</fmt:message></title>
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
            <h1><fmt:message key="locale.order">Order</fmt:message></h1>

            <div class="col-sm-8 text-left mainContent">
                <div class="col-sm-6">
                    <form method="post" path="name" action="${pageContext.request.contextPath}/user/pay"
                          class="form-horizontal">
                    <c:forEach var="dish" items="${dish}">
                        <c:when test="${fn:length(dish) > 0}">
                            <h1>Empty order</h1>
                            <div class="col-sm-6">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title card-Title-Found">${dish.name}</h5>
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item"><fmt:message key="locale.dishPrice">Price</fmt:message>:${dish.price}</li>
                                            <li class="list-group-item"><fmt:message key="locale.category">Category</fmt:message>:${dish.category}</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h1>Empty order</h1>
                        </c:otherwise>
                    </c:forEach>
                        <div class="col-sm-7">
                            <button type="submit" class="btn btn-default"
                                    data-dismiss="modal"><fmt:message key="locale.pay">Pay</fmt:message>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="template/footer.jsp"/>
</body>
</html>



