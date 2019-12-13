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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

    <div class="info_block" <c:if test="${param.error == true}">style="color: #DB1013; font-size: 13px" </c:if>>

<c:if test="${param.error == true}">
            <%=((Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage()%>
</c:if>



        <sec:authorize access="isAuthenticated()">Вы вошли как  <sec:authentication property="principal.login" /> </sec:authorize>
    </div>

    <sec:authorize access="!isAuthenticated()">
        <div class="login_block">
            <form class="login_form" action="${pageContext.servletContext.contextPath}/login/process" method="post">
                <label for="login_field" class="login_label">Login: </label>
                <input type='text' name='login' id="login_field">
                <label for="password_field" class="password_label">Password: </label>
                <input type='password' name='password' id="password_field">
                <input type='submit' value="Войти" id="enter_button">
            </form>
            <div class="register_block">
                Не зарегестрированы?
                <form class="register_form" action="${pageContext.servletContext.contextPath}/show_register" method="get">
                    <input type='submit' value="Зарегистрироваться" id="register_button"/>
                </form>
            </div>

        </div>

    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <form id="issue_advert_form" action="${pageContext.servletContext.contextPath}/create" method="post">
            <input id="issue" type="hidden" name='action' value="issue">
            <button class="issue_advert_button" onclick="submitIssueForm()">Подать объявление</button>
        </form>

        <form class="logout_form" action="${pageContext.servletContext.contextPath}/logout" method="post">
            <input type='submit' value="Выйти" id="logout_button"/>
        </form>

    </sec:authorize>

</div>

<hr>


<div class="left_margin">
    <div class="filters_container">
        <div class="filters_title">
            Фильтры:
        </div>
        <div class="last_day_container">
            <div class="for_last_day_title">За последний день</div>
            <input type="checkbox" id="for_last_day_input">
        </div>
        <div class="only_with_photo_container">
            <div class="only_with_photo_title">Только с фото</div>
            <input type="checkbox" id="only_with_photo_input">
        </div>
        <div class="brand_selector_container">
            <div class="brand_selector_title">По марке</div>
            <select id="brand_selector"></select>
        </div>
        <div class="price_container">
            <div class="price_title_from">Цена от</div>
            <input id="price_from"/>
            <div class="price_title_to">до</div>
            <input id="price_to"/>
        </div>
        <button id="apply_filter_button">Применить</button>
        <button id="reset_filter_button">Сбросить фильтры</button>


    </div>

</div>

<div class="central_container">
    <table class="main_table">
        <thead>
        <tr class="header_row">
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


<div class="right_margin">

</div>


<script>

    window.onload = function () {
        loadAll();
        loadCarBrandList();
        addListenerForPriceFields();
        addApplyFilterButtonListener();
        addResetFilterButtonListener();
    };

    var lastDayCheckBox = document.getElementById("for_last_day_input");
    var onlyWithPhotoCheckBox = document.getElementById("only_with_photo_input");
    var brandSelector = document.getElementById("brand_selector");
    var priceFrom = document.getElementById("price_from");
    var priceTo = document.getElementById("price_to");
    var applyFilterButton = document.getElementById("apply_filter_button");
    var resetFilterButton = document.getElementById("reset_filter_button");


    function loadCarBrandList() {
        sendAjaxRequest("getListOfCarBrands", getCarBrandList);

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

    function getCarBrandList(listOfBrands) {
        var options = '<option selected value="none">Нет</option>';
        for (var i = 0; i < listOfBrands.length; i++) {
            options += "<option value='" + listOfBrands[i].carBrand + "'>"
                + listOfBrands[i].carBrand + "</option>\r\n";
        }
        var brandSelector = document.getElementById("brand_selector");
        brandSelector.innerHTML = options;
    }

    function addApplyFilterButtonListener() {
        applyFilterButton.addEventListener('click', function () {
            if (filterControlsNotChecked()) {
                loadAll();
            } else {
                sendFilterParametersToServer();
            }
        })

    }

    function sendFilterParametersToServer() {
        var dataToSend = composeJsonForQuery();
        sendAjaxRequest(dataToSend, loadAllAdverts)
    }


    function addResetFilterButtonListener() {
        resetFilterButton.addEventListener('click', function () {
            lastDayCheckBox.checked = false;
            onlyWithPhotoCheckBox.checked = false;
            brandSelector.value = 'none';
            priceFrom.value = '';
            priceTo.value = '';
        })
    }

    function filterControlsNotChecked() {
        return !lastDayCheckBox.checked && !onlyWithPhotoCheckBox.checked
            && brandSelector.value === 'none' && priceFrom.value === "" && priceTo.value === "";
    }


    function composeJsonForQuery() {
        var dataToSend = {};
        dataToSend["filterSelect"] = {};
        if (lastDayCheckBox.checked) {
            dataToSend["filterSelect"]["lastDay"] = "true";
        }
        if (onlyWithPhotoCheckBox.checked) {
            dataToSend["filterSelect"]["photos"] = "true";
        }
        if (brandSelector.value !== 'none') {
            dataToSend["filterSelect"]["carBrand"] = brandSelector.value;
        }
        if(priceFrom.value !== "") {
            dataToSend["filterSelect"]["priceFrom"] = priceFrom.value;
        }
        if(priceTo.value !== "") {
            dataToSend["filterSelect"]["priceTo"] = priceTo.value;
        }
        return JSON.stringify(dataToSend);
    }

    function addListenerForPriceFields() { //запрет ввода нецифровых значений
        priceFrom.addEventListener('input', function () {
            var value = priceFrom.value;

            if (!onlyDigitsCheck(value)) {
                value = removeAllNonDigits(value);
                priceFrom.value = value;
            }
        });
        priceTo.addEventListener('input', function () {
            var value = priceTo.value;
            if (!onlyDigitsCheck(value)) {
                value = removeAllNonDigits(value);
                priceTo.value = value;
            }
        });

    }

    function onlyDigitsCheck(string) {
        var regex = new RegExp("^[0-9]*$");
        return regex.test(string);
    }

    function removeAllNonDigits(string) {
        return string.replace(/[^0-9]/gim, '');
    }


</script>


<script>


    function submitIssueForm() {
        var form = document.getElementById("issue_advert_form");
        form.submit();
    }

    function loadAll() {
        sendAjaxRequest("getAllAdverts", loadAllAdverts);
    }


    function loadAllAdverts(allAdverts) {
        var pathToDefaultImg = "${pageContext.servletContext.contextPath}/picture?folder=&fileName=NoPhoto.jpg";
        var tableBody = document.getElementById("table_body");
        var lines = "";
        for (var i = 0; i < allAdverts.length; i++) {
            var sold = allAdverts[i].sold ? "Да" : "Нет";
            var photoName = allAdverts[i].photos.length > 0 ? allAdverts[i].photos[0].fileName : "";
            lines += "<tr onclick='link(" + allAdverts[i].id + ")'><td><img src=${pageContext.servletContext.contextPath}/picture?folder="
                + allAdverts[i].car.id + "&fileName=" + photoName + " height=90 onerror=\"this.src='" + pathToDefaultImg + "'\">" + "</td>"
                + "<td class='inner_cell'>" + allAdverts[i].car.carBrand.carBrand + " "
                + allAdverts[i].car.carModel + "<br>"
                + allAdverts[i].car.bodyType.bodyType + "<br>"
                + allAdverts[i].car.color + "<br>"
                + allAdverts[i].car.engine.engineType + ", " + allAdverts[i].car.engineVolume + "<br>"
                + allAdverts[i].car.transmission.transmissionType + "<br>"
                + allAdverts[i].car.year + "<br>"
                + "</td>"
                + "<td class='inner_cell'>" + allAdverts[i].price + "</td>"
                + "<td class='inner_cell'>" + allAdverts[i].date + "</td>"
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
        virtual_form.action = "${pageContext.servletContext.contextPath}/show_advert";
        virtual_form.method = "post";
      //  var action_input = document.createElement("input");
        //action_input.type = 'hidden';
       // action_input.name = "action";
       // action_input.value = "show_advert";
         var id_input = document.createElement("input");
         id_input.type = 'hidden';
         id_input.name = "id";
         id_input.value = id;
        //virtual_form.appendChild(action_input);
        virtual_form.appendChild(id_input);
        document.body.appendChild(virtual_form);
        virtual_form.submit();
    }
</script>


</body>
</html>
