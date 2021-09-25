<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 22.09.2021
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="locale" var="locale"/>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="locale.menu">Menu</fmt:message></title>
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
        document.getElementById("menu").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/leftbar.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <h1><fmt:message key="locale.menu">Menu</fmt:message></h1>
            <div class="selectboxes">
                <br>
                <form method="get" action="${pageContext.request.contextPath}/menu/byName" class="form-horizontal">
                    <input type="hidden" name="command" value="find-dish"/>
                    <div class="form-group">
                        <label for="search" class="col-sm-2 control-label"><fmt:message
                                key="locale.searchLabel">Search Label</fmt:message></label>
                        <div class="col-sm-8" onchange="MakeSort(this);">
                            <input id="search" title="<fmt:message key="locale.searchTitle">Search Title</fmt:message>"
                                   type="text" class="form-control"
                                   placeholder="<fmt:message key="locale.menu">Menu</fmt:message>" name="dishName"/>
                            <br/>
                        </div>
                        <div class="col-sm-2">
                            <button type="submit" class="btn btn-success"><fmt:message
                                    key="locale.search">Search</fmt:message></button>
                        </div>
                    </div>

                </form>
                <hr>

                <div class="sortAwaits" style="width: 391px; float: left; margin: 0">
                    <span><fmt:message key="locale.byPrice">By Price</fmt:message></span>
                    <label>
                        <select class="sortStat" onchange="MakeSort(this);">
                            <option value="/menu"><fmt:message key="locale.allMenu">All menu</fmt:message></option>

                            <option value="/menu/fromHighToLow"><fmt:message
                                    key="locale.highPrice">High price</fmt:message></option>

                            <option value="/menu/fromLowToHigh"><fmt:message
                                    key="locale.lowPrice">Low price</fmt:message></option>
                        </select>
                    </label>
                    <br>
                    <span><fmt:message key="locale.byCategory">By Category</fmt:message></span>
                    <label>
                        <select class="sortStat" name="y" onchange="MakeSort(this);">
                            <option value="/menu"><fmt:message key="locale.allMenu">All menu</fmt:message></option>
                            <option value="/menu/fastFood"><fmt:message
                                    key="locale.fastFood">Fast food</fmt:message></option>
                            <option value="/menu/healthyFood"><fmt:message
                                    key="locale.healthyFood">Healthy food</fmt:message></option>
                            <option value="/menu/desert"><fmt:message
                                    key="locale.desert">Desert</fmt:message></option>
                        </select>
                    </label>
                    <br>

                    <c:if test='${sessionScope.get("user").role eq "manager"}'>
                        <ol><a data-toggle="modal" data-target="#replenish-stock" href="#">
                            <span class="glyphicon"></span><fmt:message
                                key="locale.replenishStock">Replenish stock</fmt:message></a>
                        </ol>
                    </c:if>
                </div>
                <div class="clear"></div>
            </div>

            <script type="text/javascript">
                function MakeSort(element) {
                    const selected = $('option:selected', element),
                        href = selected.val();
                    if (!href) {
                        return false;
                    }
                    document.location = href;
                }
            </script>

            <br>
            <div class="col-sm-8 text-left mainContent">
                <div class="row">
                    <c:forEach var="dish" items="${dish}">
                        <div class="col-sm-6">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title card-Title-Found">${dish.name}</h5>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item"><fmt:message
                                                key="locale.dishPrice">Price</fmt:message>:${dish.price}</li>
                                        <li class="list-group-item"><fmt:message
                                                key="locale.category">Category</fmt:message>:${dish.category}</li>
                                        <c:if test='${sessionScope.get("user").role eq "manager"}'>
                                            <li class="list-group-item"><fmt:message
                                                    key="locale.amount">Amount</fmt:message>:${dish.amount}</li>
                                        </c:if>
                                    </ul>
                                    <c:if test='${sessionScope.get("user").role eq "USER" || sessionScope.get("user").role eq "ADMIN"}'>
                                        <div class="col-sm-7">
                                            <a href="${pageContext.request.contextPath}/user/putInBucket">
<%--                                            <a href="FrontController?command=make-an-order&dishName=${dish.name}">--%>
                                                <button type="button" class="btn btn-default"
                                                        data-dismiss="modal"><fmt:message
                                                        key="locale.putInBucket">Put in bucket</fmt:message>
                                                </button>
                                            </a>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div id="replenish-stock" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Replenish stock</h4>
                        </div>
                        <div class="modal-body text-center">
                            <form name="replenishStock" class="form-horizontal" method="post" action="FrontController">
                                <input type="hidden" name="command" value="replenish-stock"/>
                                <span id="dishAmount" class="red"></span>
                                <div class="form-group">
                                    <div class="col-sm-7">
                                        <label>
                                            <select class="sortStat" name="dishName">
                                                <c:forEach items="${requestScope.menu}" var="dish">
                                                    <option value="${dish.name}">${dish.name}</option>
                                                </c:forEach>
                                            </select>
                                        </label>
                                    </div>
                                    <div class="col-sm-7">
                                        <label for="amount"></label>
                                        <input type="text" class="form-control" id="amount"
                                               placeholder="<fmt:message key="locale.amount">Dish amount</fmt:message>"
                                               name="amount" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-7">
                                        <button type="submit" class="btn btn-primary"><fmt:message
                                                key="locale.replenishStock">Replenish stock</fmt:message></button>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message
                                    key="locale.cancel">Cancel</fmt:message></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="template/footer.jsp"/>
</body>
</html>

