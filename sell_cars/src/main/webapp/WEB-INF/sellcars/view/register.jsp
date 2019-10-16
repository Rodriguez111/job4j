<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file="/resources/css/register.css" %>
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<html>
<head>
    <title>Новый пользователь</title>
</head>
<body>

<div class="main_container">

    <form action="${pageContext.servletContext.contextPath}/register" method="POST" id="add_form">
        <fieldset>
            <legend>Введите свои данные:</legend>
            <table>
                <tr>
                    <td><label for="login">Логин: </label></td>
                    <td><input id="login" type="text" name="login" placeholder="от 3 до 20 символов"></td>
                </tr>
                <tr>
                    <td><label for="password">Пароль: </label></td>
                    <td><input id="password" type="password" name="password" placeholder="от 3 до 20 символов"></td>
                </tr>
                <tr>
                    <td><label for="password">Подтверждение пароля: </label></td>
                    <td><input id="repassword" type="password" name="repassword" placeholder="подтвердите пароль"></td>
                </tr>
                <tr>
                    <td><label for="name">Имя: </label></td>
                    <td><input id="name" type="text" name="name" placeholder="от 3 до 20 символов"></td>
                </tr>
                <tr>
                    <td><label for="surname">Фамилия: </label></td>
                    <td><input id="surname" type="text" name="surname" placeholder="от 3 до 20 символов"></td>
                </tr>
                <tr>
                    <td><label for="phone">Телефон: (опционально)</label></td>
                    <td><input id="phone" type="text" name="phone" placeholder="+123456789123"></td>
                </tr>
                <tr>
                    <td><label for="email">E-mail: </label></td>
                    <td><input id="email" type="text" name="email" placeholder="от 3 до 60 символов"></td>
                </tr>

            </table>

            <hr>

            <div class="buttons_container">
                <div class="left_button_container">
                    <button id="back_button" type="button"
                            onClick='location.href="${pageContext.servletContext.contextPath}"'>Назад
                    </button>
                </div>
                <div class="right_button_container">
                    <button id="submit_button" type="button" onclick="validate()">Зарегистрировать</button>
                </div>
            </div>

            <div class="info_panel" id="info_panel">
            </div>

        </fieldset>
    </form>


</div>

<script>

    function validate() {
        var form = document.getElementById("add_form");
        var infoPanel = document.getElementById("info_panel");
        infoPanel.innerHTML = "";
        var login = form.login.value;
        var password = form.password.value;
        var repassword = form.repassword.value;
        var name = form.name.value;
        var surname = form.surname.value;
        var phone = form.phone.value;
        var email = form.email.value;
        var result = true;

        if (!checkLength(login, 20)) {
            infoPanel.innerHTML = "Поле Логин должно иметь длину от 3 до 20 символов и не содержать пробелов";
            result = false;
        } else if (!checkLength(password, 20)) {
            infoPanel.innerHTML = "Поле Пароль должно иметь длину от 3 до 20 символов и не содержать пробелов";
            result = false;
        } else if (password !== repassword) {
            infoPanel.innerHTML = "Пароли не совпадают";
            result = false;
        } else if (!checkLength(name, 20)) {
            infoPanel.innerHTML = "Имя должно иметь длину от 3 до 20 символов и не содержать пробелов";
            result = false;
        } else if (!checkLength(surname, 20)) {
            infoPanel.innerHTML = "Фамилия должна иметь длину от 3 до 20 символов и не содержать пробелов";
            result = false;
        } else if (phone != null) {
            if (!checkPhone(phone)) {
                infoPanel.innerHTML = "Номер телефона должен соответствовать формату +000111222333";
                result = false;
            }
        } else if (result && !checkEmail(email)) {
            infoPanel.innerHTML = "Неверный формат почтового адреса";
            result = false;
        }

        if (result) {
            var json = JSON.stringify({
                "login": login, "password": password, "name": name,
                "surname": surname, "phone": phone, "email": email
            });
            sendRequest(json)
        }
    }

    function checkEmail(string) {
        var regex = new RegExp("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
        return regex.test(string);
    }

    function checkPhone(string) {
        var regex = new RegExp("^\\+[0-9]{12}$");
        return regex.test(string);
    }

    function checkLength(string, maxLength) {
        return string.length >= 3 && string.length <= maxLength && !string.includes(' ');
    }

    function sendRequest(json) {
        $.ajax("./register", {
            method: 'post',
            data: json,
            contentType: 'text/json; charset=utf-8',
            dataType: 'json',
            success: function (data) {
                handleSysMessage(data)
            }
        });

    }

    function handleSysMessage(jsonMessage) {
        var infoPanel = document.getElementById("info_panel");
        if (jsonMessage.messageFromServer === "OK") {
            infoPanel.style.color = '#0d9c01';
            infoPanel.innerHTML = "Регистрация прошла успешно. Теперь вы можете войти как " + jsonMessage.login;
            setTimeout(function () {
                window.location.href = "${pageContext.servletContext.contextPath}";
            }, 2000);//wait 3 second

        } else {
            infoPanel.innerHTML = jsonMessage.messageFromServer;
        }


    }


</script>


</body>
</html>
