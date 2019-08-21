<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.job4j.crudservlet.Pages" %>
<%@ page import="ru.job4j.crudservlet.Roles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/resources/css/style.css"%></style>

<html>
<head>
    <title>Updating use</title>
    <table>
        <tr>
            <td><h2>Updating user</h2></td>
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
    <form method='post' action="${pageContext.servletContext.contextPath}<%=Pages.UPDATE.page%>" id="updateform">
       <table>
        <tr><td><label>Existing name: </label></td><td><label> ${param.name}</label></td></tr>
        <tr><td><label>New name: </label></td><td><label><input type='text' name='name'/></label></td></tr>
        <tr><td><label>Existing login: </label></td><td><label> ${param.login} </label></td></tr>
           <tr><td><label>New login: </label></td><td><label><input type='text' name='login' <c:if test="${param.login == 'root'}">disabled</c:if>/></label></td></tr>
        <tr><td><label>New password: </label></td><td><label><input type='password' name='password'/></label></td></tr>
        <tr><td><label>Existing e-mail: </label></td><td><label> ${param.email} </label></td></tr>
        <tr><td><label>New e-mail: </label></td><td><label><input type='text' name='email'/></label></td></tr>

           <tr><td><label>Select role: </label></td><td><label>
               <select
                  <c:if test="${role != 'administrator' || param.login=='root'}"> disabled </c:if>
                       name="role" form="updateform">
                   <option selected disabled>${param.role}</option>
                   <option value="<%=Roles.ADMINISTRATOR.getRole()%>">administrator</option>
                   <option value="<%=Roles.USER.getRole()%>">user</option>
               </select>
           </label></td></tr>
        <tr><td></td><td><input type='submit' value='UPDATE'></td></tr>
        </table>
        <input type='hidden' name='id' value='${param.id}' />
        </form>

    <form  action="${pageContext.servletContext.contextPath}<%=Pages.MAIN.page%>">
        <button>BACK</button>
    </form>
</div>

</body>
</html>