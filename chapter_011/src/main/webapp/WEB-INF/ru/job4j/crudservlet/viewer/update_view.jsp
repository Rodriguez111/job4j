<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page import="ru.job4j.crudservlet.Pages" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Update user</title>
    <h2> Updating user </h2>
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

    <form method='post' action="${pageContext.servletContext.contextPath}<%=Pages.UPDATE.page%>">
       <table>
        <tr><td><label>Existing name: </label></td><td><label> ${param.name}</label></td></tr>
        <tr><td><label>New name: </label></td><td><label><input type='text' name='name'/></label></td></tr>
        <tr><td><label>Existing login: </label></td><td><label> ${param.login} </label></td></tr>
        <tr><td><label>New login: </label></td><td><label><input type='text' name='login'/></label></td></tr>
        <tr><td><label>Existing e-mail: </label></td><td><label> ${param.email} </label></td></tr>
        <tr><td><label>New e-mail: </label></td><td><label><input type='text' name='email'/></label></td></tr>
        <tr><td></td><td><input type='submit' value='UPDATE'></td></tr>
        </table>
        <input type='hidden' name='id' value='${param.id}' />
        </form>

    <form  action="${pageContext.servletContext.contextPath}<%=Pages.MAIN.page%>">
        <button>BACK</button>
    </form>
</div>

</body>
</html>
