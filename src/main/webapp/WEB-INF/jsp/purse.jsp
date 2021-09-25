<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 25.09.2021
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="locale" var="locale"/>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="locale.purse">User wallet</fmt:message></title>
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
        document.getElementById("purse").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/leftbar.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <h1><fmt:message key="locale.purse">User wallet</fmt:message></h1>

            <div class="col-sm-8 text-left mainContent">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="card">
                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item"><fmt:message key="locale.purseAmount">Your waller amount</fmt:message>:${purse.amount}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-sm-7">
                    <form name="toUpAPurse" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/topUpPurse">
<%--                        <input type="hidden" name="command" value="top-up-purse"/>--%>
                        <div class="toUpAPurse" >
                            <div class="form-group">
                                <span id="amount" class="red"></span>
                                <div class="col-sm-7 input-amount">
                                    <label for="purseAmount"></label>
                                    <input type="text" class="form-control" id="purseAmount" placeholder="<fmt:message key="locale.topUp">Top up</fmt:message>"
                                            name="amount" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-7 batton-submit">
                                    <button type="submit" class="btn btn-primary"><fmt:message key="locale.topUp">Top up</fmt:message></button>
                                </div>
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



