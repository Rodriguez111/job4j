<%@ page import="ru.job4j.crudservlet.Pages" %><%--
  Created by IntelliJ IDEA.
  TestUser: Ruben
  Date: 20.07.2019
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/resources/css/style.css"%></style>
<html>
<head>
    <title>Deleting user</title>
    <table>
        <tr>
            <td><h2>Deleting user</h2></td>
            <td class="logout">
                <h2><form method='post' action="${pageContext.servletContext.contextPath}<%=Pages.LOGOUT.page%>">
                    <input type='submit' value='Logout'>
                </form></h2>
            </td>

        </tr>
    </table>
</head>
<body>
<div class='block1'>
<form method='post' action="${pageContext.servletContext.contextPath}<%=Pages.DELETE.page%>" >
    <b>Are you sure you want to delete this user?</b>
    <table>
        <tr><td><label>Name: </label></td><td><label>${requestScope.name}</label></td></tr>
        <tr><td><label>Login: </label></td><td><label>${requestScope.login}</label></td></tr>
        <tr><td><label>E-mail: </label></td><td><label>${requestScope.email}</label></td></tr>
        <tr><td><label>Create date: </label></td><td><label>${requestScope.createDate}</label></td></tr>
       <tr><td></td><td><input type='submit' value='DELETE'></td></tr>
        </table>
    <input type='hidden' name='id' value='${requestScope.id}'/>
    </form>
<form  action="${pageContext.servletContext.contextPath}<%=Pages.MAIN.page%>">
    <button>BACK</button>
</form>
</div>

</body>
</html>
