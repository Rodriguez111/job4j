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
            <th>Create date</th>
            <th>Update user</th>
            <th>Delete user</th>
        </tr>
        <c:forEach items="${listOfUsers}" var="eachUser">
            <tr class='row'>
                <td class='cell'>${eachUser.name}</td>
                <td class='cell'>${eachUser.login}</td>
                <td class='cell'>${eachUser.email}</td>
                <td class='cell'>${eachUser.createDate}</td>
                <td class='cell'>
                    <form class="forms" method='get' action="${pageContext.servletContext.contextPath}<%=Pages.UPDATE.page%>">
                        <input type='hidden' name='id' value='${eachUser.id}'/>
                        <input type='hidden' name='name' value='${eachUser.name}'/>
                        <input type='hidden' name='login' value='${eachUser.login}'/>
                        <input type='hidden' name='email' value='${eachUser.email}'/>
                        <input type='submit' value='UPDATE'>
                    </form>
                </td>
                <td class='cell'>
                    <form class="forms" method='get' action="${pageContext.servletContext.contextPath}<%=Pages.DELETE.page%>">
                        <input type='hidden' name='id' value='${eachUser.id}'/>
                        <input type='hidden' name='name' value='${eachUser.name}'/>
                        <input type='hidden' name='login' value='${eachUser.login}'/>
                        <input type='hidden' name='email' value='${eachUser.email}'/>
                        <input type='hidden' name='createDate' value='${eachUser.createDate}'/>
                        <input type='submit' value='DELETE'>
                    </form>

                </td>
            </tr>
        </c:forEach>


        <%--<table>--%>
        <%--    <c:forEach items="${listOfUsers}" var="eachUser">--%>
        <%--    <tr>--%>
        <%--        <td>--%>
        <%--            <div class='block1'>--%>
        <%--                <table>--%>
        <%--                    <tr><b>User info:</b></tr>--%>
        <%--                    <tr>--%>
        <%--                        <td><label>Name: </label></td>--%>
        <%--                        <td><label>${eachUser.name}--%>
        <%--                        </label></td>--%>
        <%--                    </tr>--%>
        <%--                    <tr>--%>
        <%--                        <td><label>Login: </label></td>--%>
        <%--                        <td><label>${eachUser.login}--%>
        <%--                        </label></td>--%>
        <%--                    </tr>--%>
        <%--                    <tr>--%>
        <%--                        <td><label>E-mail: </label></td>--%>
        <%--                        <td><label>${eachUser.email}--%>
        <%--                        </label></td>--%>
        <%--                    </tr>--%>
        <%--                    <tr>--%>
        <%--                        <td><label>Create date: </label></td>--%>
        <%--                        <td><label>${eachUser.createDate}--%>
        <%--                        </label></td>--%>
        <%--                    </tr>--%>
        <%--                </table>--%>
        <%--                <table>--%>
        <%--                    <tr>--%>
        <%--                        <td>--%>
        <%--                            <form method='get' action="${pageContext.servletContext.contextPath}<%=Pages.UPDATE.page%>">--%>
        <%--                                <input type='hidden' name='id' value='${eachUser.id}' />--%>
        <%--                                <input type='hidden' name='name' value='${eachUser.name}' />--%>
        <%--                                <input type='hidden' name='login' value='${eachUser.login}' />--%>
        <%--                                <input type='hidden' name='email' value='${eachUser.email}' />--%>
        <%--                                <input type='submit' value='UPDATE'>--%>
        <%--                            </form>--%>
        <%--                        </td>--%>
        <%--                        <td>--%>
        <%--                                    <form method='get' action="${pageContext.servletContext.contextPath}<%=Pages.DELETE.page%>">--%>
        <%--                                        <input type='hidden' name='id' value='${eachUser.id}' />--%>
        <%--                                        <input type='hidden' name='name' value='${eachUser.name}' />--%>
        <%--                                        <input type='hidden' name='login' value='${eachUser.login}' />--%>
        <%--                                        <input type='hidden' name='email' value='${eachUser.email}' />--%>
        <%--                                        <input type='hidden' name='createDate' value='${eachUser.createDate}' />--%>
        <%--                                        <input type='submit' value='DELETE'>--%>
        <%--                                    </form>--%>
        <%--                        </td>--%>
        <%--                    </tr>--%>
        <%--                </table>--%>
        <%--            </div>--%>
        <%--        </td>--%>
        <%--    </tr>--%>
        <%--    </c:forEach>--%>


        <tr>
            <td class="table_footer">
                <form method='get' action="${pageContext.servletContext.contextPath}<%=Pages.ADD.page%>">
                    <input type='submit' value='ADD NEW USER'>
                </form>
            </td>
        </tr>
    </table>
</div>


</body>
</html>
