<%@ page import="ru.job4j.crudservlet.Pages" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <h2> Creating new user </h2>
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
    <form method='post' action="${pageContext.servletContext.contextPath}<%=Pages.ADD.page%>" >
        <table>
            <tr><td><label>Name: </label></td><td><label><input type='text' name='name'></label></td></tr>
            <tr><td><label>Login: </label></td><td><label><input type='text' name='login'></label></td></tr>
            <tr><td><label>E-mail: </label></td><td><label><input type='text' name='email'></label></td></tr>
            <tr><td></td><td><input type='submit' value='ADD USER'></td></tr>
        </table>
    </form>
    <form  action="${pageContext.servletContext.contextPath}<%=Pages.MAIN.page%>">
        <button>BACK</button>
        </form>
</div>

</body>
</html>
