<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.job4j.crudservlet.Pages" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/resources/css/style.css"%></style>

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
    <style><%@include file="/resources/crudservlet/css/error_popup.css" %></style>
    <style><%@include file="/resources/crudservlet/css/update_view.css" %></style>

    <link rel="stylesheet" href="resources/crudservlet/css/bootstrap.css">



    <title>Update user</title>


</head>
<body>


<div class="container">

    <div class="col-md-2">
    </div>
    <div class="col-md-3">
        <h4>Update user</h4>
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

    <div class="col-md-4">
    </div>

    <div class="col-md-4">
        <form method='post' action="${pageContext.servletContext.contextPath}<%=Pages.UPDATE.page%>" id="updateform">

            <div class="form-group">
                <label for="name">Name:</label><input type='text' class="form-control" title="name" name="name"
                                                      id="name" placeholder=${param.name}>
            </div>
            <div class="form-group">
                <label for="login">Login:</label><input type='text' class="form-control" title="login" name="login"
                                                        id="login" placeholder=${param.login}>
            </div>
            <div class="form-group">
                <label for="password">Password:</label><input type='password' class="form-control" title="password"
                                                              name="password" id="password">
            </div>
            <div class="form-group">
                <label for="repassword">Password:</label><input type='password' class="form-control" title="repassword"
                                                                name="repassword" id="repassword">
            </div>
            <div class="form-group">
                <label for="email">Email:</label><input type='text' class="form-control" title="email" name="email"
                                                           id="email" placeholder=${param.email}>
            </div>
            <div class="container location">
                <div class="col-sm-2 location">
                    <div class="form-group">
                        <label for="country">Country:</label>
                        <select name="country" form="updateform" required class="form-control" id="country">
                        </select>
                    </div>
                </div>
                <div class="col-sm-2 location">
                    <div class="form-group">
                        <label for="city">City:</label>
                        <select name="city" form="updateform" required class="form-control" id="city">
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="role">Role:</label>
                <select name="role" form="updateform" required class="form-control" id="role">
                </select>
            </div>
            <input type='hidden' name='id' value='${param.id}' />

        </form>


        <div class="container">

            <div class="col-sm-1">

                <div class="form-group">
                    <button class="btn-sm" style="width: 120px" onclick=validate()>UPDATE USER</button>
                </div>
            </div>
            <div class="col-sm-1"></div>
            <div class="col-sm-1">
                <form action="${pageContext.servletContext.contextPath}<%=Pages.MAIN.page%>">
                    <div class="form-group">
                        <button class="btn-sm">BACK</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-4">
        </div>
    </div>
</div>




<div class="modal-overlay" id="modal-overlay">
    <div class="modal-wrapper" id="modal-wrapper">
        <div class="modal-header">
            <div class="header-content">
                Ошибка!
            </div>
        </div>
        <div class="modal-body" id="modal-body">
            <div class="info-field" id="info-field">
            </div>
        </div>
        <div class="modal-footer">
            <button id="ok-error" class="ok-error">Ok</button>
        </div>
    </div>
</div>



<script>
    var countrySelector = $("#country");
    var citySelector = $("#city");
    var errorOkButton = document.getElementById("ok-error");
    errorOkButton.addEventListener('click', function() {
        var errorWindow = document.getElementById("modal-overlay");
        errorWindow.style.display = "none";
    });


    function validate() {
        var result = "OK";
        var name = document.getElementById("name");
        var login = document.getElementById("login");
        var email = document.getElementById("email");
        var password = document.getElementById("password");
        var repassword = document.getElementById("repassword");
        var city = document.getElementById("city");
        var role = document.getElementById("role");
        result =  validateInput("Имя", name.value, 20);
        if(result === "OK") {
            result =  validateInput("Логин", login.value, 20);
        }
        if (result === "OK") {
            result =  validateInput("Пароль", password.value, 20);
            if (result === "OK" && password.length !== 0) {
                if (password.value !== repassword.value) {
                    result = "Пароли должны совпадать.";
                }
            }
        }
        if (result === "OK") {
            result =  validateInput("Email", email.value, 30);
        }
        if (result === "OK") {
            result =  validateEmpty("Город", city.value);
        }
        if (result === "OK") {
            var form = document.getElementById("updateform");
            form.submit();
        } else {
            var errorWindow = document.getElementById("modal-overlay");
            document.getElementById("info-field").innerHTML = result;
            errorWindow.style.display = "flex";
        }
    }

    function validateLength(fieldName, fieldValue, maxLength) {
        var result = "OK";
        if(fieldValue.length < 3 || fieldValue.length > maxLength) {
            result =  "Длина поля " + fieldName + " должна быть от 3 до " +  maxLength +  " символов."
        }
        return result;
    }

    function validateEmpty(fieldName, fieldValue) {
        var result = "OK";
        if (fieldValue.length === 0) {
            result = "Поле " + fieldName + " не может быть пустым";
        }
        return result;
    }


    function validateSpaces(fieldName, fieldValue) {
        var result = "OK";
        if(typeof fieldValue == "string" && fieldValue.indexOf(' ') > -1){
            result =  "Пробелы в поле " + fieldName + " недопустимы"
        }
        return result;
    }

    function validateInput(fieldName, fieldValue, maxLength) {
        var result = "OK";
        if (fieldValue.length > 0) {
            result = validateLength(fieldName, fieldValue, maxLength);
            if(result === "OK") {
                result =  validateSpaces(fieldName, fieldValue);
            }
        }

        return result;
    }

    function sendAjaxRequest(url, dataToSend, callback) {
        $.ajax(url, {
            method: 'post',
            data: dataToSend,
            contentType: 'text/json; charset=utf-8',
            dataType: 'json',
            success: function(data){
                callback(data);
            }
        })
    }



    function getRolesList(listOfRoles) {
        var lst = listOfRoles.listOfRoles;
       var selectedRole = "${param.role}";
       var options = "<option selected disabled value='" + selectedRole + "'>" + selectedRole + "</option>";
        for (var i = 0; i < lst.length; i++) {
            options += "<option value='" + lst[i] + "'>"
                + lst[i] + "</option>\r\n";
        }
        $("#role").html(options);
    }

    function getCountriesList(listOfCountries) {
        var lst = listOfCountries.listOfCountries;
        var selectedCountry = "${param.country}";
        var options = "<option selected disabled value='" + selectedCountry + "'>" + selectedCountry + "</option>";
        for (var i = 0; i < lst.length; i++) {
            options += "<option value='" + lst[i] + "'>"
                + lst[i] + "</option>\r\n";
        }
        countrySelector.html(options);
    }

    function getCitiesList(listOfCities) {
        var currentCity = "${param.city}";
        var lst = listOfCities.listOfCities;
        var options = "<option selected disabled value='" + currentCity + "'>" + currentCity + "</option>";
        for (var i = 0; i < lst.length; i++) {
            options += "<option value='" + lst[i] + "'>"
                + lst[i] + "</option>\r\n";
        }
        citySelector.html(options);
    }

    function getDefaultCity(listOfCities) {
        var selectedCity = "${param.city}";

        var lst = listOfCities.listOfCities;
        var options = "<option selected disabled value='" + selectedCity + "'>" + selectedCity + "</option>";
        for (var i = 0; i < lst.length; i++) {
            options += "<option value='" + lst[i] + "'>"
                + lst[i] + "</option>\r\n";
        }
        citySelector.html(options);
    }



    function addLists() {
        sendAjaxRequest('./json', "getListOfCountries", getCountriesList);
        sendAjaxRequest('./json', "getListOfRoles", getRolesList);


        var selectedCountry = "${param.country}";
        var jsonString = JSON.stringify({"getListOfCities":selectedCountry});
        sendAjaxRequest('./json', jsonString, getDefaultCity);
    }

    function sendAjaxRequest(url, dataToSend, callback) {
        $.ajax(url, {
            method: 'post',
            data: dataToSend,
            contentType: 'text/json; charset=utf-8',
            dataType: 'json',
            success: function(data){
                callback(data);
            }
        })
    }

    countrySelector.change(function() {
        var countrySelector = document.getElementById("country");
        var currentCountry = countrySelector.options[countrySelector.selectedIndex].value;
        var jsonString = JSON.stringify({"getListOfCities":currentCountry});
        sendAjaxRequest('./json', jsonString, getCitiesList);
    });

    $(document).ready(addLists);



</script>


</body>



</html>
