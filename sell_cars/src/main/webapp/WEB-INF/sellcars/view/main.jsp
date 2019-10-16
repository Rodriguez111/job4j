<%--
Это должно быть web приложение со следующими страницами.
1. основная страница. таблица со всеми объявлениям машин на продажу.
2. на странице должна быть кнопка. добавить новое объявление.
3. переходить на страницу добавления.
4. должны быть категории машины. марка. тип кузова и тд. посмотри как на авито сделано.
5. важно!. можно добавлять фото. использовать библиотеку apache fileuppload
6. объявление имеет статус продано. или нет.
7. должны существовать пользователи. кто подал заявление. только он может менять статус.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file="/resources/css/main.css" %>
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>Главная</title>
</head>
<body>
<div class="user_block">
    <div class="info_block" <c:if test="${errorMessage != null}">style="color: #DB1013; font-size: 13px" </c:if>>
        <c:if test="${errorMessage != null}">${errorMessage}</c:if>
        <c:if test="${sessionScope.userLogin != null}">Вы вошли как ${sessionScope.userLogin} </c:if>
    </div>
    <c:if test="${sessionScope.userName == null}">
        <div class="login_block">
            <form class="login_form" action="${pageContext.servletContext.contextPath}/login" method="post">
                <label for="login_field" class="login_label">Login: </label>
                <input type='text' name='login' id="login_field">
                <label for="password_field" class="password_label">Password: </label>
                <input type='password' name='password' id="password_field">
                <input type='submit' value="Войти" id="enter_button">
            </form>
            <div class="register_block">
                Не зарегестрированы?
                <form class="register_form" action="${pageContext.servletContext.contextPath}/forward" method="post">
                    <input type='hidden' name='action' value='register'/>
                    <input type='submit' value="Зарегистрироваться" id="register_button"/>
                </form>
            </div>

        </div>
    </c:if>

    <c:if test="${sessionScope.userName != null}">
        <form id="issue_advert_form" action="${pageContext.servletContext.contextPath}/forward" method="post">
            <input id="issue" type="hidden" name='action' value="issue">
            <button class="issue_advert_button" onclick="submitIssueForm()">Подать объявление</button>
        </form>

        <form class="logout_form" action="${pageContext.servletContext.contextPath}/logout" method="post">
            <input type='submit' value="Выйти" id="logout_button"/>
        </form>
    </c:if>


</div>

<hr>


<div class="main_table_container">
    <table class="main_table">
        <thead>
        <tr>
            <td class="first_column">Фото</td>
            <td class="second_column">Описание</td>
            <td class="third_column">Цена</td>
            <td class="fourth_column">Опубликован</td>
            <td class="fifth_column">Продано</td>
        </tr>
        </thead>
        <tbody id="table_body">
        </tbody>
    </table>
</div>


<script>
    function submitIssueForm() {
        var form = document.getElementById("issue_advert_form");
        form.submit();
    }


    sendAjaxRequest("getAllAdverts", loadAllAdverts);

    function loadAllAdverts(data) {
        console.log(data);
        var pathToDefaultImg = "${pageContext.servletContext.contextPath}/picture?folder=&fileName=NoPhoto.jpg";
        var allAdverts = data.allAdverts;
        var tableBody = document.getElementById("table_body");
        var lines = "";
        for (var i = 0; i < allAdverts.length; i++) {
            var sold = allAdverts[i].sold ? "Да" : "Нет";
            var photoName = allAdverts[i].photos.length > 0 ? allAdverts[i].photos[0].fileName : "";
            lines += "<tr onclick='link(" + allAdverts[i].id + ")'><td><img src=${pageContext.servletContext.contextPath}/picture?folder="
                + allAdverts[i].car.id + "&fileName=" + photoName + " height=90 onerror=\"this.src='" + pathToDefaultImg + "'\">" + "</td>"
                + "<td>" + allAdverts[i].car.carBrand.carBrand + " "
                + allAdverts[i].car.carModel + "<br>"
                + allAdverts[i].car.bodyType.bodyType + "<br>"
                + allAdverts[i].car.color + "<br>"
                + allAdverts[i].car.engine.engineType + ", " + allAdverts[i].car.engineVolume + "<br>"
                + allAdverts[i].car.transmission.transmissionType + "<br>"
                + allAdverts[i].car.year + "<br>"
                + "</td>"
                + "<td>" + allAdverts[i].price + "</td>"
                + "<td>" + allAdverts[i].date + "</td>"
                + "<td>" + sold + "</td>"
                + "</tr>"


        }
        tableBody.innerHTML = lines;
    }


    function sendAjaxRequest(dataToSend, callback) {
        $.ajax('./json', {
            method: 'post',
            data: dataToSend,
            contentType: 'text/json; charset=utf-8',
            dataType: 'json',
            success: function (data) {
                callback(data);
            }
        })
    }


    function link(id) {
        var virtual_form = document.createElement("form");
        virtual_form.action = "${pageContext.servletContext.contextPath}/forward";
        virtual_form.method = "post";
        var action_input = document.createElement("input");
        action_input.name = "action";
        action_input.value = "show_advert";
        var id_input = document.createElement("input");
        id_input.name = "id";
        id_input.value = id;
        virtual_form.appendChild(action_input);
        virtual_form.appendChild(id_input);
        document.body.appendChild(virtual_form);
        virtual_form.submit();
    }


</script>


</body>
</html>
