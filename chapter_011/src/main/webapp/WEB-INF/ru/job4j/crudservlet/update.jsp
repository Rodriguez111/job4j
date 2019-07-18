<%@ page import="ru.job4j.crudservlet.Pages" %><%--
  Created by IntelliJ IDEA.
  User: Ruben
  Date: 20.07.2019
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
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

    <form method='post' action=<%=Pages.UPDATE.page%>>
       <table>
        <tr><td><label>Existing name: </label></td><td><label>  <%=request.getParameter("name") %> </label></td></tr>
        <tr><td><label>New name: </label></td><td><label><input type='text' name='name'/></label></td></tr>
        <tr><td><label>Existing login: </label></td><td><label> <%=request.getParameter("login") %> </label></td></tr>
        <tr><td><label>New login: </label></td><td><label><input type='text' name='login'/></label></td></tr>
        <tr><td><label>Existing e-mail: </label></td><td><label> <%=request.getParameter("email") %> </label></td></tr>
        <tr><td><label>New e-mail: </label></td><td><label><input type='text' name='email'/></label></td></tr>
        <tr><td></td><td><input type='submit' value='UPDATE'></td></tr>
        </table>
        <input type='hidden' name='id' value='<%=request.getParameter("id") %>' />
        </form>


    <form  action=<%=Pages.MAIN.page%>>
        <button>BACK</button>
    </form>

</div>

</body>
</html>
