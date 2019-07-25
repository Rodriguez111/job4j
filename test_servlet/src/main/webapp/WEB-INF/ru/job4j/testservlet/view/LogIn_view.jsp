<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
    <h2> Sign in </h2>
</head>
<body>

<style type='text/css'>
    .block1 {
        width: 300px;
        background: #ccc;
        padding: 5px;
        border: solid 1px grey;
        float: left;
    }
</style>


    <c:if test="${error != ''}">
        <div class='error' style="background-color: firebrick">
            <c:out value="${error}"/>
        </div>
    </c:if>


<div class='block1'>
    <form method='post' action="${pageContext.servletContext.contextPath}/signin" >
        <table>
            <tr><td><label>Login: </label></td><td><label><input type='text' name='login'></label></td></tr>
            <tr><td><label>Password: </label></td><td><label><input type='password' name='password'></label></td></tr>
            <tr><td></td><td><input type='submit' value='SIGN IN'></td></tr>
        </table>
    </form>
</div>

</body>
</html>
