<%@ page import="ru.job4j.crudservlet.Pages" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>

    <style><%@include file="/resources/crudservlet/css/bootstrap.css"%></style>
    <style><%@include file="/resources/crudservlet/css/bootstrap-theme.css"%></style>
    <style><%@include file="/resources/crudservlet/css/bootstrap-theme.min.css"%></style>
    <style><%@include file="/resources/crudservlet/css/login_view.css"%></style>
    <style><%@include file="/resources/crudservlet/js/jquery-3.4.1.min.js"%></style>



    <title>Sign in</title>

</head>
<body>

<c:if test="${errorMessage != ''}">
<div class='err_block' style="background-color: firebrick">
    <c:out value="${errorMessage}" />
</div>
</c:if>
<div class="col-md-5">
</div>
<div class='col-md-2'>
    <h2> Sign in </h2>
    <form method='post' action="${pageContext.servletContext.contextPath}<%=Pages.LOGIN.page%>" >

            <div class="form-group">
            <label for="login">Login:</label><input type='text' class="form-control" title="login" name="login" id="login">
            </div>
            <div class="form-group">
            <label for="password">Password: </label><input type='password' class="form-control" name='password' id="password">
            </div>
            <div class="form-group">
            <input type='submit' class="btn btn-default" value='LOG IN'>
            </div>

    </form>


</div>
<div class="col-md-5">
</div>

</body>
</html>
