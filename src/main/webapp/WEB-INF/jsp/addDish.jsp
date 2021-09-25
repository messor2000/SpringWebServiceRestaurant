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
<fmt:setBundle basename="locale" var="locale"/>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="locale.add">Add dish</fmt:message></title>
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
        document.getElementById("dish").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/leftbar.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <h1><fmt:message key="locale.add">Add dish</fmt:message></h1>

            <div class="col-sm-8 text-left mainContent">

                <div class="col-sm-7">
                    <form name="addDishForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/admin/addDish">
                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label"><fmt:message key="locale.dishName">Dish name</fmt:message></label>
                            <span id="name" class="red"></span>
                            <div class="col-sm-7">
                                <input for="dishName" type="text" class="form-control" id="dishName" placeholder="<fmt:message key="locale.dishName">Dish name</fmt:message>"
                                        name="name" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="price" class="col-sm-3 control-label"><fmt:message key="locale.dishPrice">Dish price</fmt:message></label>
                            <span id="price" class="red"></span>
                            <div class="col-sm-7">
                                <input for="dishPrice" type="text" class="form-control" id="dishPrice" placeholder="<fmt:message key="locale.dishPrice">Dish price</fmt:message>"
                                        name="price" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="category" class="col-sm-3 control-label"><fmt:message key="locale.category">Category</fmt:message></label>
                            <span id="category" class="red"></span>
                            <div class="col-sm-7">
                                <input for="dishCategory" type="text" class="form-control" id="dishCategory" placeholder="<fmt:message key="locale.category">Category</fmt:message>"
                                        name="category" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="amount" class="col-sm-3 control-label"><fmt:message key="locale.amount">Dish amount</fmt:message></label>
                            <span id="amount" class="red"></span>
                            <div class="col-sm-7">
                                <input for="dishAmount" type="text" class="form-control" id="dishAmount" placeholder="<fmt:message key="locale.amount">Dish amount</fmt:message>"
                                        name="amount" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-7">
                                <button type="submit" class="btn btn-primary"><fmt:message key="locale.add">Add dish</fmt:message></button>
                            </div>
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

