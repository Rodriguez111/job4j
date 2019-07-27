<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>



<table>
    <tr>
        <td>
<div>
<form action="${pageContext.servletContext.contextPath}/users" method="post">
    Login: <input type="text" name="login"> <br/><br/>
    Email: <input type="text" name="email"><br/><br/>
    <input type="submit">
</form>
</div>
        </td>
        <td>

<div>
    <form action="${pageContext.servletContext.contextPath}/logout" method="post">
        <input type="submit" value="Log out">
    </form>
</div>
        </td>
    </tr>
</table>

<table style="border: 1px solid black" border="1" cellpadding="3">
    <tr>
      <th>Login</th>
      <th>Email</th>
    </tr>
    <c:forEach items="${users}" var="user">

    <tr>
        <td><c:out value="${user.login}" /></td>
        <td><c:out value="${user.email}" /></td>
    </tr>
    </c:forEach>

</table>

</body>
</html>
