<%@ page import="ru.job4j.crudservlet.Pages" %>
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

<div class='block1'>
    <form method='post' action="${pageContext.servletContext.contextPath}<%=Pages.SIGN_IN.page%>" >
        <table>
            <tr><td><label>Login: </label></td><td><label><input type='text' name='login'></label></td></tr>
            <tr><td><label>Password: </label></td><td><label><input type='password' name='password'></label></td></tr>
            <tr><td></td><td><input type='submit' value='SIGN IN'></td></tr>
        </table>
    </form>
    <form  action="${pageContext.servletContext.contextPath}<%=Pages.MAIN.page%>">
        <button>BACK</button>
    </form>
</div>

</body>
</html>
