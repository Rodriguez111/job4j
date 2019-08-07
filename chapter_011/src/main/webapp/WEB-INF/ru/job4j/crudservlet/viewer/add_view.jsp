<%@ page import="ru.job4j.crudservlet.Pages" %>
<%@ page import="ru.job4j.crudservlet.Roles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/resources/css/style.css"%></style>
<html>
<head>
    <title>Adding user</title>
    <table>
        <tr>
            <td><h2>Adding user</h2></td>
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
    <form method='post' action="${pageContext.servletContext.contextPath}<%=Pages.ADD.page%>" id="addform" >
        <table>
            <tr><td><label>Name: </label></td><td><label><input type='text' name='name' required></label></td></tr>
            <tr><td><label>Login: </label></td><td><label><input type='text' name='login' required></label></td></tr>
            <tr><td><label>Password: </label></td><td><label><input type='password' name='password' required></label></td></tr>
            <tr><td><label>E-mail: </label></td><td><label><input type='text' name='email' required></label></td></tr>
            <tr><td><label>Select role: </label></td><td><label>
                <select name="role" form="addform" required>
                    <option selected disabled value="">Select role</option>
                    <option value="<%=Roles.ADMINISTRATOR.getRole()%>">Administrator</option>
                    <option value="<%=Roles.USER.getRole()%>">User</option>
                </select>
            </label></td></tr>
            <tr><td></td><td><input type='submit' value='Создать'></td></tr>
        </table>
    </form>
    <form  action="${pageContext.servletContext.contextPath}<%=Pages.MAIN.page%>">
        <button>BACK</button>
        </form>
</div>

</body>
</html>
