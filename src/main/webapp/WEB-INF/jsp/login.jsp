<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 22.09.2021
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:set scope="session" var="previousQuery" value="index.jsp"/>--%>
<fmt:setBundle basename="locale" var="locale"/>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="locale.signIn">Sing In</fmt:message></title>
    <meta charset="utf-8">
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
        document.getElementById("index-page").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/leftbar.jsp"/>

        <div class="col-sm-8 text-left mainContent">

            <div class="modal-dialog">
                <div class="modal-content">
                    <c:if test="${requestScope.get('errorMessage')!=null}">
                        <h4 class="red text-center"><c:out value="${requestScope.get('errorMessage')}"/></h4>
                        <c:remove var="errorMessage" scope="request"/>
                    </c:if>
                    <div class="modal-body text-center">
                        <%--                        <form name="loginForm" class="form-horizontal" method="post" action="<c:url value="/sign/up"/>"--%>
                        <%--                              onsubmit="return validateForm();">--%>
                        <%--                            <input type="hidden" name="command" value="login"/>--%>
                        <%--                            <div class="form-group">--%>
                        <%--                                <label for="username" class="col-sm-3 control-label">${username} </label>--%>
                        <%--                                <span id="unameDemo" class="red"></span>--%>
                        <%--                                <div class="col-sm-7">--%>
                        <%--                                    <input type="text" class="form-control" id="username" placeholder="${username}"--%>
                        <%--                                           name="username" required>--%>
                        <%--                                </div>--%>
                        <%--                            </div>--%>
                        <%--                            <div class="form-group">--%>
                        <%--                                <label for="password3" class="col-sm-3 control-label">${password}</label>--%>
                        <%--                                <span id="pswDemo" class="red"></span>--%>
                        <%--                                <div class="col-sm-7">--%>
                        <%--                                    <input type="password" class="form-control" id="password3" placeholder="${password}"--%>
                        <%--                                           name="password" required>--%>
                        <%--                                </div>--%>
                        <%--                            </div>--%>
                        <%--                            <div class="form-group">--%>
                        <%--                                <div class="col-sm-offset-3 col-sm-7">--%>
                        <%--                                    <button type="submit" class="btn btn-primary">${signIn}</button>--%>
                        <%--                                </div>--%>
                        <%--                            </div>--%>
                        <%--                        </form>--%>
                        <form name="loginForm" class="form-horizontal" method="post"
                              action="<c:url value="/sign/in"/>" onsubmit="return validateForm();">
                            <input type="hidden" name="command" value="login"/>
                            <span id="usernameDemo" class="red"></span>
                            <div class="form-group">
                                <label for="login" class="col-sm-3 control-label"><fmt:message
                                        key="locale.username">Username</fmt:message></label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="login"
                                           placeholder="<fmt:message key="locale.username">Username</fmt:message>"
                                           name="username" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-3 control-label"><fmt:message
                                        key="locale.password">Password</fmt:message></label>
                                <span id="passwordDemo" class="red"></span>
                                <div class="col-sm-7">
                                    <input type="password" class="form-control" id="password"
                                           placeholder="<fmt:message key="locale.password">Password</fmt:message>"
                                           name="password" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-7">
                                    <button type="submit" class="btn btn-primary"><fmt:message
                                            key="locale.signIn">Sing in</fmt:message></button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <a href="<c:url value="/"/>">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message
                                    key="locale.cancel">Cancel</fmt:message></button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<c:import url="template/footer.jsp"/>

<script language="javascript">
    function validateForm() {
        let uname, psw;
        let unameText, pswText;
        let result = true;
        uname = document.forms["loginForm"]["username"].value;
        psw = document.forms["loginForm"]["password"].value;
        const unamePattern = /[a-zA-Z_0-9]{3,16}/;
        if (!unamePattern.test(uname)) {
            unameText = "Username should contain only latin symbols, digits and _";
            document.getElementById("unameDemo").innerHTML = unameText;
            result = false;
        } else if (uname.length < 3) {
            unameText = "Username should be at least 3 symbols.";
            document.getElementById("unameDemo").innerHTML = unameText;
            result = false;
        } else if (uname.length > 16) {
            unameText = "Username should be less then 17 symbols.";
            document.getElementById("unameDemo").innerHTML = unameText;
            result = false;
        } else {
            unameText = "";
            document.getElementById("unameDemo").innerHTML = unameText;
        }
        const passPattern = /[a-zA-Z0-9_]{6,32}/;
        if (psw.length < 6) {
            pswText = "Password should be at least 6 symbols";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        } else if (!passPattern.test(psw)) {
            pswText = "Password should contain only latin symbols, digits and _";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        } else if (psw.length > 32) {
            pswText = "Password should be less then 32 symbols";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        } else {
            unameText = "";
            document.getElementById("pswDemo").innerHTML = unameText;
        }
        return result;
    }
</script>
</body>
</html>

