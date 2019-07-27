<%@ page import="ru.job4j.crudservlet.Pages" %>
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
        <tr><td><label>Name: </label></td><td><label>${param.name}</label></td></tr>
        <tr><td><label>Login: </label></td><td><label>${param.login}</label></td></tr>
        <tr><td><label>E-mail: </label></td><td><label>${param.email}</label></td></tr>
        <tr><td><label>Create date: </label></td><td><label>${param.createDate}</label></td></tr>
       <tr><td></td><td><input type='submit' value='DELETE'></td></tr>
        </table>
    <input type='hidden' name='id' value='${param.id}'/>
    </form>
<form  action="${pageContext.servletContext.contextPath}<%=Pages.MAIN.page%>">
    <button>BACK</button>
</form>
</div>

</body>
</html>
