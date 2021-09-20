<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.siteName" var="siteName"/>
<fmt:message bundle="${locale}" key="locale.home" var="home"/>
<fmt:message bundle="${locale}" key="locale.signUp" var="signUp"/>
<fmt:message bundle="${locale}" key="locale.logIn" var="logIn"/>
<fmt:message bundle="${locale}" key="locale.logOut" var="logOut"/>
<fmt:message bundle="${locale}" key="locale.signIn" var="signIn"/>
<fmt:message bundle="${locale}" key="locale.username" var="username"/>
<fmt:message bundle="${locale}" key="locale.password" var="password"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>
<fmt:message bundle="${locale}" key="locale.email" var="email"/>
<fmt:message bundle="${locale}" key="locale.repeatPassword" var="password2"/>
<fmt:message bundle="${locale}" key="locale.register" var="register"/>
<fmt:message bundle="${locale}" key="locale.registerName" var="registerName"/>
<fmt:message bundle="${locale}" key="locale.loginName" var="loginName"/>
<fmt:message bundle="${locale}" key="locale.menu" var="menu"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">${siteName}</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li id="index-page"><a href="${pageContext.request.contextPath}/index.jsp">${home}</a></li>
                <li id="menu"><a href="FrontController?command=show-menu">${menu}</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.get('user') == null}">
                    <li class="sign-up">
                        <a data-toggle="modal" data-target="#register" href="#">
                            <span class="glyphicon glyphicon-user"></span>
                                ${signUp}</a>
                    </li>
                    <li><a data-toggle="modal" data-target="#login" href="#">
                        <span class="glyphicon glyphicon-log-in"></span>
                            ${logIn}</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.get('user') != null}">
                    <li class="sign-up">
                        <a href="FrontController?command=view-user&username=${sessionScope.get('user').username}">
                            <span class="glyphicon glyphicon-user"></span> ${sessionScope.get('user').username}</a>
                    </li>
                    <li><a href="FrontController?command=log-out">
                        <span class="glyphicon glyphicon-log-out"></span> ${logOut}</a>
                    </li>

                </c:if>
                <li><a href="FrontController?command=change-language&language=en">English</a></li>
                <li><a href="FrontController?command=change-language&language=ru">Pусский</a></li>
            </ul>
        </div>
    </div>
</nav>

<div id="login" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;  </button>
                <h4 class="modal-title">${loginName}</h4>
            </div>
            <div class="modal-body text-center">
                <form name="loginForm" class="form-horizontal" method="post" action="FrontController" onsubmit="return validateForm();">
                    <input type="hidden" name="command" value="login"/>
                    <span id="usernameDemo" class="red"></span>
                    <div class="form-group">
                        <label for="username" class="col-sm-3 control-label">${username}</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="username" placeholder="${username}"
                                   name="username" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password3" class="col-sm-3 control-label">${password}</label>
                        <span id="passwordDemo" class="red"></span>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="password3" placeholder="${password}"
                                   name="password" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-7">
                            <button type="submit" class="btn btn-primary">${signIn}</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">${cancel}</button>
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
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;  </button>
                <h4 class="modal-title" id="myModalLabel">${registerName}</h4>
            </div>
            <div class="modal-body text-center">
                <form name="registerForm" class="form-horizontal" method="post" action="FrontController"  onsubmit="return validateRegistration();">
                    <input type="hidden" name="command" value="register"/>
                    <div class="form-group">
                        <label for="inputUsername" class="col-sm-3 control-label">${username}</label>
                        <span id="usernameDemo2" class="red"></span>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputUsername" placeholder="${username}"
                                   name="username" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-3 control-label">${email}</label>
                        <span id="emailDemo2" class="red"></span>
                        <div class="col-sm-7">
                            <input type="email" class="form-control" id="email" placeholder="${email}"
                                   name="email" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-3 control-label">${password}</label>
                        <span id="passwordDemo2" class="red"></span>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="inputPassword3" placeholder="${password}"
                                   name="password" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword4" class="col-sm-3 control-label">${password2}</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="inputPassword4" placeholder="${password2}"
                                   name="password2" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-7">
                            <button type="submit" class="btn btn-primary">${register}</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">${cancel}</button>
            </div>
        </div>
    </div>
</div>
<script  language="javascript">
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
