<%@ page import="ru.job4j.crudservlet.Pages" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/resources/css/style.css"%></style>


<html>
<head>
    <title>Sign in</title>
    <h2> Sign in </h2>
</head>
<body>

<c:if test="${errorMessage != ''}">
<div class='err_block' style="background-color: firebrick">
    <c:out value="${errorMessage}" />
</div>
</c:if>

<div class='block1'>
    <form method='post' action="${pageContext.servletContext.contextPath}<%=Pages.LOGIN.page%>" >
        <table>
            <tr><td><label>Login: </label></td><td><label><input type='text' name='login'></label></td></tr>
            <tr><td><label>Password: </label></td><td><label><input type='password' name='password'></label></td></tr>
            <tr><td></td><td><input type='submit' value='LOG IN'></td></tr>
        </table>
    </form>

</div>

</body>
</html>