<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.job4j.crudservlet.Pages" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    <%@include file="/resources/css/style.css" %>
</style>

<html>
<head>
    <title>Users information</title>
    <table>
        <tr>
            <td><h2>Users information</h2></td>
            <td class="logout">
                <h2>
                    <form method='post' action="${pageContext.servletContext.contextPath}<%=Pages.LOGOUT.page%>">
                        <input type='submit' value='Logout'>
                    </form>
                </h2>
            </td>

        </tr>
    </table>
</head>
<body>

<div class="main_block">
    <table class="main_table">
        <tr class="table_header">
            <th>Name</th>
            <th>Login</th>
            <th>E-mail</th>
            <th>Role</th>
            <th>Create date</th>
            <th>Update user</th>
            <c:if test="${role == 'administrator'}">
                <th>Delete user</th>
            </c:if>
        </tr>
        <c:forEach items="${listOfUsers}" var="eachUser">
            <tr class='row'>
                <td class='cell'>${eachUser.name}</td>
                <td class='cell'>${eachUser.login}</td>
                <td class='cell'>${eachUser.email}</td>
                <td class='cell'>${eachUser.role}</td>
                <td class='cell'>${eachUser.createDate}</td>

                <td class='cell'>
                    <c:if test="${role == 'administrator' || eachUser.login == login}">
                        <form class="forms" method='post'
                              action="${pageContext.servletContext.contextPath}<%=Pages.VIEWS.page%>">
                            <input type='hidden' name='id' value='${eachUser.id}'/>
                            <input type='hidden' name='name' value='${eachUser.name}'/>
                            <input type='hidden' name='login' value='${eachUser.login}'/>
                            <input type='hidden' name='email' value='${eachUser.email}'/>
                            <input type='hidden' name='role' value='${eachUser.role}'/>
                            <input type='hidden' name='action' value='update'/>
                            <input type='submit' value='UPDATE'>
                        </form>
                    </c:if>
                </td>

                <c:if test="${role == 'administrator'}">
                    <td class='cell'>
                        <c:if test="${eachUser.login != 'root'}">
                            <form class="forms" method='post'
                                  action="${pageContext.servletContext.contextPath}<%=Pages.VIEWS.page%>">
                                <input type='hidden' name='id' value='${eachUser.id}'/>
                                <input type='hidden' name='name' value='${eachUser.name}'/>
                                <input type='hidden' name='login' value='${eachUser.login}'/>
                                <input type='hidden' name='email' value='${eachUser.email}'/>
                                <input type='hidden' name='createDate' value='${eachUser.createDate}'/>
                                <input type='hidden' name='action' value='delete'/>
                                <input type='submit' value='DELETE'>
                            </form>
                        </c:if>
                    </td>

                </c:if>

            </tr>
        </c:forEach>


        <tr>
            <td class="table_footer">
                <c:if test="${role == 'administrator'}">
                    <form method='post' action="${pageContext.servletContext.contextPath}<%=Pages.VIEWS.page%>">
                        <input type='hidden' name='action' value='add'/>
                        <input type='submit' value='ADD NEW USER'>
                    </form>
                </c:if>
            </td>
        </tr>

    </table>
</div>


</body>
</html>
