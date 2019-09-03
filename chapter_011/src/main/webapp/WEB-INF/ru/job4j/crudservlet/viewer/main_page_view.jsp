<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.job4j.crudservlet.Pages" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>

    <script>
        <%@include file="/resources/crudservlet/js/jquery-3.4.1.min.js" %>
    </script>
    <script>
        <%@include file="/resources/crudservlet/js/bootstrap.js" %>
    </script>
    <script>
        <%@include file="/resources/crudservlet/js/bootstrap.min.js" %>
    </script>
    <style>
        <%@include file="/resources/crudservlet/css/confirm_popup.css" %>
    </style>


    <link rel="stylesheet" href="resources/crudservlet/css/bootstrap.css">
    <%--    <link rel="stylesheet" href="resources/crudservlet/css/bootstrap-theme.css">--%>
    <%--    <link rel="stylesheet" href="resources/crudservlet/css/bootstrap-theme.min.css">--%>
    <%--    <link rel="stylesheet" href="resources/crudservlet/main_page_view.css">--%>

    <title>Users information</title>


</head>
<body>

<div class="container">

    <div class="col-md-2">
    </div>
    <div class="col-md-3">
        <h4>Users information</h4>
    </div>
    <div class="col-md-2">
    </div>
    <div class="col-md-2">
        <form method="post" action="${pageContext.servletContext.contextPath}<%=Pages.LOGOUT.page%>">
            <input class="btn-sm" type="submit" value="Logout"/>
        </form>
    </div>

    <div class="col-md-3">
    </div>
</div>


<div class="container">

    <div class="col-md-12">


        <table id="mainTable" class="table table-hover table-bordered table-striped table-condensed">
            <tr class="active">
                <th>Name</th>
                <th>Login</th>
                <th>E-mail</th>
                <th>Role</th>
                <th>Country</th>
                <th>City</th>
                <th>Create date</th>
                <th>Update user</th>
                <c:if test="${role == 'administrator'}">
                    <th>Delete user</th>
                </c:if>
            </tr>
            <c:forEach items="${listOfUsers}" var="eachUser">
                <tr>
                    <td>${eachUser.name}</td>
                    <td>${eachUser.login}</td>
                    <td>${eachUser.email}</td>
                    <td>${eachUser.role}</td>
                    <td>${eachUser.country}</td>
                    <td>${eachUser.city}</td>
                    <td>${eachUser.createDate}</td>

                    <td>
                        <c:if test="${role == 'administrator' || eachUser.login == login}">
                            <form class="forms" method='post'
                                  action="${pageContext.servletContext.contextPath}<%=Pages.VIEWS.page%>">
                                <input type='hidden' name='id' value='${eachUser.id}'/>
                                <input type='hidden' name='name' value='${eachUser.name}'/>
                                <input type='hidden' name='login' value='${eachUser.login}'/>
                                <input type='hidden' name='email' value='${eachUser.email}'/>
                                <input type='hidden' name='role' value='${eachUser.role}'/>
                                <input type='hidden' name='country' value='${eachUser.country}'/>
                                <input type='hidden' name='city' value='${eachUser.city}'/>
                                <input type='hidden' name='action' value='update'/>
                                <input type='submit' class="btn-sm" value='UPDATE'>
                            </form>
                        </c:if>
                    </td>

                    <c:if test="${role == 'administrator'}">
                        <td>
                            <c:if test="${eachUser.login != 'root'}">
                                <form class="deleteform" method='post'
                                      action="${pageContext.servletContext.contextPath}<%=Pages.DELETE.page%>">
                                    <input type='hidden' name='id' value='${eachUser.id}'/>
                                    <input type='hidden' name='login' value='${eachUser.login}'/>
                                    <input type='hidden' name='action' value='delete'/>
                                </form>
                                <button class="btn-sm" style="width: 120px"
                                        onclick="validateDelete('${eachUser.login}')">DELETE
                                </button>
                            </c:if>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>


        </table>
        <c:if test="${role == 'administrator'}">
            <form method='post' action="${pageContext.servletContext.contextPath}<%=Pages.VIEWS.page%>">
                <input type='hidden' name='action' value='add'/>
                <input type='submit' value='ADD NEW USER'>
            </form>
        </c:if>
    </div>

</div>


<div class="modal-overlay" id="modal-overlay">
    <div class="modal-wrapper" id="modal-wrapper">
        <div class="modal-header">
            <div class="header-content">
                Удаление пользователя
            </div>
        </div>
        <div class="modal-body" id="modal-body">
            <div class="info-field" id="info-field">
                Вы действительно хотите удалить?
            </div>
        </div>
        <div class="modal-footer">
            <button id="ok-button" class="ok-button">Ok</button>
            <button id="cancel-button" class="cancel-button">Cancel</button>
        </div>
    </div>
</div>


<script>
    var userLogin;
    var okButton = document.getElementById("ok-button");

    okButton.addEventListener('click', function () {

        var errorWindow = document.getElementById("modal-overlay");
        var deleteForms = document.getElementsByClassName('deleteform'); //получаем массив всех форм
        for (var i = 0; i < deleteForms.length; i++) {
            var eachLoginField = deleteForms[i].elements[1]; // получаем 2-е по счету поле input (поле логина)
            if (eachLoginField.value === userLogin) {
                var form = deleteForms[i];
                form.submit();
                errorWindow.style.display = "none";
            }
        }
    });


    var cancelButton = document.getElementById("cancel-button");
    cancelButton.addEventListener('click', function () {
        var errorWindow = document.getElementById("modal-overlay");
        errorWindow.style.display = "none";
    });


    function validateDelete(login) {
        userLogin = login;
        var errorWindow = document.getElementById("modal-overlay");
        document.getElementById("info-field").innerHTML = "Вы действительно хотите удалить пользователя " + login + "?";
        errorWindow.style.display = "flex";
    }


</script>


</body>
</html>
