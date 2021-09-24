<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 21.09.2021
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="locale" var="locale"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><fmt:message key="locale.siteName">Site name</fmt:message></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li id="index-page"><a href="${pageContext.request.contextPath}/"><fmt:message
                        key="locale.home">Home</fmt:message></a></li>
                <li id="menu"><a href="${pageContext.request.contextPath}/menu"><fmt:message
                        key="locale.menu">Menu</fmt:message></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.get('user') == null}">
                    <li class="sign-up">
                        <a data-toggle="modal" data-target="#register" href="#">
                            <span class="glyphicon glyphicon-user">
                            </span><fmt:message key="locale.signUp">Sing up</fmt:message></a>
                    </li>
                    <li><a data-toggle="modal" data-target="#login" href="#">
                        <span class="glyphicon glyphicon-log-in"></span>
                        <fmt:message key="locale.signIn">Sing in</fmt:message></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.get('user') != null}">
                    <li class="sign-up">
                        <a href="FrontController?command=view-user&username=${sessionScope.get('user').username}">
                            <span class="glyphicon glyphicon-user"></span>${sessionScope.get('user').username}</a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/sign/out">
                        <span class="glyphicon glyphicon-log-out"></span><fmt:message
                            key="locale.logOut">Logout</fmt:message></a>
                    </li>
                </c:if>
                <li><a href="?lang=en_US">English</a></li>
                <li><a href="?lang=ru_RU">Русский</a></li>
            </ul>
        </div>
    </div>
</nav>

<div id="login" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><fmt:message key="locale.loginName">Login name</fmt:message></h4>
            </div>
            <div class="modal-body text-center">

                <form name="loginForm" class="form-horizontal" method="post"
                      action="<c:url value="/"/>" onsubmit="return validateForm();">
<%--                    <input type="hidden" name="command" value="login"/>--%>
                    <span id="usernameDemo" class="red"></span>
                    <div class="form-group">
                        <label for="username" class="col-sm-3 control-label"><fmt:message
                                key="locale.username">Username</fmt:message></label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="username"
                                   placeholder="<fmt:message key="locale.username">Username</fmt:message>"
                                   name="username" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password3" class="col-sm-3 control-label"><fmt:message
                                key="locale.password">Password</fmt:message></label>
                        <span id="passwordDemo" class="red"></span>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="password3"
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
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message
                        key="locale.cancel">Cancel</fmt:message></button>
            </div>
        </div>
    </div>
</div>

<script language="javascript">
    function validateForm() {
        let username, password;
        let usernameText, passwordText;
        let result = true;
        username = document.forms["loginForm"]["username"].value;
        password = document.forms["loginForm"]["password"].value;
        const unamePattern = /[a-zA-Z_0-9]{3,16}/;
        if (!unamePattern.test(username)) {
            usernameText = "Username should contain only latin symbols, digits and _";
            document.getElementById("usernameDemo").innerHTML = usernameText;
            result = false;
        } else if (username.length < 3) {
            usernameText = "Username should be at least 3 symbols.";
            document.getElementById("usernameDemo").innerHTML = usernameText;
            result = false;
        } else if (username.length > 16) {
            usernameText = "Username should be less then 17 symbols.";
            document.getElementById("usernameDemo").innerHTML = usernameText;
            result = false;
        } else {
            usernameText = "";
            document.getElementById("usernameDemo").innerHTML = usernameText;
        }
        const passPattern = /[a-zA-Z0-9_]{6,32}/;
        if (password.length < 6) {
            passwordText = "Password should be at least 6 symbols";
            document.getElementById("passwordDemo").innerHTML = passwordText;
            result = false;
        } else if (!passPattern.test(password)) {
            passwordText = "Password should contain only latin symbols, digits and _";
            document.getElementById("passwordDemo").innerHTML = passwordText;
            result = false;
        } else if (password.length > 32) {
            passwordText = "Password should be less then 32 symbols";
            document.getElementById("passwordDemo").innerHTML = passwordText;
            result = false;
        }
        return result;
    }
</script>

<div id="register" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><fmt:message
                        key="locale.registerName">Register to Restaurant</fmt:message></h4>
            </div>
            <div class="modal-body text-center">
                <form name="registerForm" class="form-horizontal" method="post" action="<c:url value="/sign/up"/>"
                      onsubmit="return validateRegistration();">
<%--                    <input type="hidden" name="command" value="register"/>--%>
                    <div class="form-group">
                        <label for="inputUsername" class="col-sm-3 control-label"><fmt:message
                                key="locale.username">Username</fmt:message></label>
                        <span id="usernameDemo2" class="red"></span>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputUsername"
                                   placeholder="<fmt:message key="locale.username">Username</fmt:message>"
                                   name="username" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-3 control-label"><fmt:message
                                key="locale.email">Email</fmt:message></label>
                        <span id="emailDemo2" class="red"></span>
                        <div class="col-sm-7">
                            <input type="email" class="form-control" id="email"
                                   placeholder="<fmt:message key="locale.email">Email</fmt:message>"
                                   name="email" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-3 control-label"><fmt:message
                                key="locale.password">Password</fmt:message></label>
                        <span id="passwordDemo2" class="red"></span>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="inputPassword3"
                                   placeholder="<fmt:message key="locale.password">Password</fmt:message>"
                                   name="password" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword4" class="col-sm-3 control-label"><fmt:message
                                key="locale.repeatPassword">Repeat password</fmt:message></label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="inputPassword4"
                                   placeholder="<fmt:message key="locale.password">Repeat password</fmt:message>"
                                   name="password2" required>
                        </div>
                    </div>
                    <input type="hidden" name="role" value="VISITOR">
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-7">
                            <button type="submit" class="btn btn-primary"><fmt:message
                                    key="locale.register">Register</fmt:message></button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message
                        key="locale.cancel">Cancel</fmt:message></button>
            </div>

<%--            <form method="post" action="${pageContext.request.contextPath}/signup" role="form" class="form-horizontal">--%>
<%--                <div class="form-group">--%>
<%--                    <label for="InputEmail" class="control-label"><fmt:message key="form.email"/></label>--%>
<%--                    <input type="email" name="email" class="form-control" id="InputEmail"--%>
<%--                           placeholder="<fmt:message key="placeholder.email"/>">--%>
<%--                </div>--%>
<%--                <div class="form-group">--%>
<%--                    <label for="InputPassword" class="control-label"><fmt:message key="form.password"/></label>--%>
<%--                    <input type="password" name="password" class="form-control" id="InputPassword"--%>
<%--                           placeholder="<fmt:message key="placeholder.password"/>">--%>
<%--                </div>--%>
<%--                <div class="form-group">--%>
<%--                    <label for="InputFirstName" class="control-label"><fmt:message key="form.firstName"/></label>--%>
<%--                    <input type="text" name="first-name" class="form-control" id="InputFirstName"--%>
<%--                           placeholder="<fmt:message key="placeholder.firstName"/>">--%>
<%--                </div>--%>
<%--                <div class="form-group">--%>
<%--                    <label for="InputLastName" class="control-label"><fmt:message key="form.lastName"/></label>--%>
<%--                    <input type="text" name="last-name" class="form-control" id="InputLastName"--%>
<%--                           placeholder="<fmt:message key="placeholder.lastName"/>">--%>
<%--                </div>--%>
<%--                <div class="form-group">--%>
<%--                    <label for="InputPhone" class="control-label"><fmt:message key="form.phone"/></label>--%>
<%--                    <input type="text" name="phone" class="form-control" id="InputPhone"--%>
<%--                           placeholder="<fmt:message key="placeholder.phone"/>">--%>
<%--                </div>--%>
<%--                <input type="hidden" name="role" value="VISITOR">--%>
<%--                <button type="submit" class="btn btn-primary"><fmt:message key="ref.submit"/></button>--%>
<%--            </form>--%>
        </div>
    </div>
</div>
<script language="javascript">
    function validateRegistration() {
        let username, email, password, password2;
        let usernameText, emailText, passwordText;
        let result = true;
        username = document.forms["registerForm"]["nickname"].value;
        email = document.forms["registerForm"]["email"].value;
        password = document.forms["registerForm"]["pass"].value;
        password2 = document.forms["registerForm"]["pass2"].value;
        const unamePattern = /[a-zA-Z_0-9]{3,16}/;
        if (!unamePattern.test(username)) {
            usernameText = "Nickname should contain only latin symbols, digits and _";
            document.getElementById("usernameDemo2").innerHTML = usernameText;
            result = false;
        } else if (username.length < 3) {
            usernameText = "Nickname should be at least 3 symbols.";
            document.getElementById("usernameDemo2").innerHTML = usernameText;
            result = false;
        } else if (username.length > 16) {
            usernameText = "Nickname should be less then 17 symbols.";
            document.getElementById("usernameDemo2").innerHTML = usernameText;
            result = false;
        } else {
            usernameText = "";
            document.getElementById("usernameDemo2").innerHTML = usernameText;
        }
        const emailPattern = /[a-zA-Z0-9_]+@[A-Za-z0-9].+/;
        if (!emailPattern.test(email)) {
            emailText = "Email should contain latin symbols, @, digits, . and _";
            document.getElementById("emailDemo2").innerHTML = emailText;
            return false;
        } else {
            usernameText = "";
            document.getElementById("emailDemo2").innerHTML = usernameText;
        }
        const passPattern = /[a-zA-Z0-9_]{6,32}/;
        if (password.length < 6) {
            passwordText = "Password should be at least 6 symbols";
            document.getElementById("passwordDemo2").innerHTML = passwordText;
            result = false;
        } else if (!passPattern.test(password)) {
            passwordText = "Password should contain only latin symbols, digits and _";
            document.getElementById("passwordDemo2").innerHTML = passwordText;
            result = false;
        } else if (password.length > 32) {
            passwordText = "Password should be less then 32 symbols";
            document.getElementById("passwordDemo2").innerHTML = passwordText;
            result = false;
        } else if (password !== password2) {
            passwordText = "Passwords should be the same";
            document.getElementById("passwordDemo2").innerHTML = passwordText;
            result = false;
        } else {
            usernameText = "";
            document.getElementById("passwordDemo2").innerHTML = usernameText;
        }
        return result;
    }
</script>
