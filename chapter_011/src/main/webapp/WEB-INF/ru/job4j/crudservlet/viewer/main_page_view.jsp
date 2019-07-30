<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.job4j.crudservlet.Pages" %>
<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page import="ru.job4j.crudservlet.controller.logic.ValidateService" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Users information</title>
    <h2>Users information</h2>
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


<table>
    <c:forEach items="${listOfUsers}" var="eachUser">
    <tr>
        <td>
            <div class='block1'>
                <table>
                    <tr><b>User info:</b></tr>
                    <tr>
                        <td><label>Name: </label></td>
                        <td><label>${eachUser.name}
                        </label></td>
                    </tr>
                    <tr>
                        <td><label>Login: </label></td>
                        <td><label>${eachUser.login}
                        </label></td>
                    </tr>
                    <tr>
                        <td><label>E-mail: </label></td>
                        <td><label>${eachUser.email}
                        </label></td>
                    </tr>
                    <tr>
                        <td><label>Create date: </label></td>
                        <td><label>${eachUser.createDate}
                        </label></td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>
                            <form method='get' action="${pageContext.servletContext.contextPath}<%=Pages.UPDATE.page%>">
                                <input type='hidden' name='id' value='${eachUser.id}' />
                                <input type='hidden' name='name' value='<c:set var="name" value="${eachUser.name}" scope="request"/>'/>
                                <input type='hidden' name='login' value='${eachUser.login}' />
                                <input type='hidden' name='email' value='${eachUser.email}' />
                                <input type='submit' value='UPDATE'>
                            </form>
                        </td>
                        <td>
                            <form method='get' action="${pageContext.servletContext.contextPath}<%=Pages.DELETE.page%>">
                                <input type='hidden' name='id' value='<c:set var="name" value="${eachUser.name}" scope="request"/>' />
                                <input type='hidden' name='name' value='${eachUser.name}' />
                                <input type='hidden' name='login' value='${eachUser.login}' />
                                <input type='hidden' name='email' value='${eachUser.email}' />
                                <input type='hidden' name='createDate' value='${eachUser.createDate}' />
                                <input type='submit' value='DELETE'>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </td>
    </tr>
    </c:forEach>

    <tr>
        <td>
            <form method='get' action="${pageContext.servletContext.contextPath}<%=Pages.ADD.page%>">
                <input type='submit' value='ADD NEW USER'>
            </form>
        </td>
    </tr>
</table>


</body>
</html>
