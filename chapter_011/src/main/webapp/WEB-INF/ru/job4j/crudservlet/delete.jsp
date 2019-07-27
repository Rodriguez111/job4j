<%@ page import="ru.job4j.crudservlet.Pages" %><%--
  Created by IntelliJ IDEA.
  User: Ruben
  Date: 20.07.2019
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user</title>
    <h2> Deleting user </h2>
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
<form method='post' action=<%=Pages.DELETE.page%>>
    <b>Are you sure you want to delete this user?</b>
    <table>
        <tr><td><label>Name: </label></td><td><label><%=request.getParameter("name") %></label></td></tr>
        <tr><td><label>Login: </label></td><td><label><%=request.getParameter("login") %></label></td></tr>
        <tr><td><label>E-mail: </label></td><td><label><%=request.getParameter("email") %></label></td></tr>
        <tr><td><label>Create date: </label></td><td><label><%=request.getParameter("createDate") %></label></td></tr>
       <tr><td></td><td><input type='submit' value='DELETE'></td></tr>
        </table>
    <input type='hidden' name='id' value='<%=request.getParameter("id") %>' />
    </form>
<form  action=<%=Pages.MAIN.page%>>
    <button>BACK</button>
</form>
</div>

</body>
</html>
