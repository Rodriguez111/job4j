<%@ page import="ru.job4j.jsp.User" %>
<%@ page import="ru.job4j.jsp.UserStorage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>





<form action="<%=request.getContextPath()%>/echo" method="post">
    Login: <input type="text" name="login"><br/><br/>
    Email: <input type="text" name="email"><br/><br/>
    <input type="submit">
</form>
<br/>
<table style="border: 1px solid black" border="1" cellpadding="3">
    <tr>
      <th>Login</th>
      <th>Email</th>
    </tr>
    <% for (User eachUser : UserStorage.getINSTANCE().getUsers()) {%>
    <tr>
        <td><%=eachUser.getLogin()%></td>
        <td><%=eachUser.getEmail()%></td>
    </tr>
    <% } %>

</table>

</body>
</html>
