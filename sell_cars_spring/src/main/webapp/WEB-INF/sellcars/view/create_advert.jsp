<%--
  Created by IntelliJ IDEA.
  User: Ruben
  Date: 01.10.2019
  Time: 4:56
  To change this template use File | Settings | File Templates.
--%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file="/resources/css/create_advert.css" %>
</style>


<html>
<head>

    <title>Создание нового объявления:</title>
</head>

<body>

<div class="main_container">

    <form method="post" action="${pageContext.servletContext.contextPath}/issue_advert" enctype="multipart/form-data"
          id="issue_form">
        <fieldset>
            <legend>Создание нового объявления:</legend>

            <div class="first_line">

                <div class="car_brand_container">
                    <label for="car_brand">Марка: </label>
                    <select class="selects" id="car_brand" name="car_brand">
                    </select>
                </div>

                <div class="car_model_container">
                    <label for="car_model">Модель: </label>
                    <input type="text" name="car_model" id="car_model" placeholder="Model-1">
                </div>
            </div>
            <hr>
            <div class="second_line">
                <div class="body_type_container">
                    <label for="body_type">Тип кузова: </label>
                    <select class="selects" id="body_type" name="body_type">
                    </select>
                </div>
                <div class="transmission_container">
                    <label for="transmission">Трансмиссия: </label>
                    <select class="selects" id="transmission" name="transmission">
                    </select>
                </div>
                <div class="engine_container">
                    <div class="engine_type_container">
                        <label for="engine_type">Тип двигателя: </label>
                        <select class="selects" id="engine_type" name="engine_type">
                        </select>
                    </div>
                    <div class="engine_volume_container">
                        <label for="engine_volume">Объем двигателя: </label>
                        <input type="text" id="engine_volume" name="engine_volume" placeholder="0.0">
                    </div>
                </div>
            </div>
            <hr>
            <div class="third_line">
                <div class="subline_one">
                    <div class="vin_container">
                        <div class="vin_description">
                            (17 символов, только латинские заглавные буквы и цифры)
                        </div>
                        <label for="vin">VIN: </label>
                        <input type="text" name="vin" id="vin" placeholder="WAUZZZ8AZNA123456">
                    </div>
                    <div class="color_container">
                        <label for="color">Цвет кузова: </label>
                        <input type="text" name="color" id="color" placeholder="синий">
                    </div>
                </div>
                <div class="subline_two">
                    <div class="mileage_container">
                        <label for="mileage">Пробег, км.: </label>
                        <input type="text" name="mileage" id="mileage" placeholder="120000">
                    </div>
                    <div class="year_container">
                        <label for="year">Год выпуска: </label>
                        <input type="text" name="year" id="year" placeholder="1901">
                    </div>
                </div>
            </div>
            <hr>
            <div class="fourth_line">
                <div class="photo_container">
                    <label>Загрузите фото (до 5 штук): </label>
                    <div class="file_input_container">
                        <input id="input_photo1" class="input_photo" type="file" name="files"
                               onchange="addOneMoreInput(this)"/>
                    </div>
                </div>
            </div>

            <hr>
            <div class="fifth_line">
                <div class="price_container">
                    <label for="price">Укажите цену, USD: </label>
                    <input type="text" name="price" id="price" placeholder="25000">
                </div>
            </div>
            <hr>
            <div class="info_panel" id="info_panel">
                <c:if test="${messageFromServer != null}">${messageFromServer}</c:if>
            </div>

        </fieldset>

    </form>


    <div class="buttons_container">
        <div class="left_button_container">
            <button id="back_button" type="submit" onClick='location.href="${pageContext.servletContext.contextPath}"'>
                Назад
            </button>
        </div>
        <div class="right_button_container">
            <button id="submit_button" onClick="issue()">Опубликовать объявление</button>

        </div>
    </div>


</div>


<script>
    $(document).ready(function () {
        sendAjaxRequest("getListOfCarBrands", getCarBrandList);
        sendAjaxRequest("getListOfCarBodies", getCarBodyList);
        sendAjaxRequest("getListOfTransmissions", getTransmissionList);
        sendAjaxRequest("getListOfEngines", getEngineList);
    });

    var carBrandSelector = $("#car_brand");
    var carBodySelector = $("#body_type");
    var transmissionSelector = $("#transmission");
    var engineSelector = $("#engine_type");
    var fileSizeIsValidated = true;


    function issue() {

        var form = document.getElementById("issue_form");
        if (validateForm() && fileSizeIsValidated) {
            form.submit();
        }
    }

    function validateForm() {
        var result = checkAllFields();
        var info_panel = document.getElementById("info_panel");
        var form = document.getElementById("issue_form");
        var carModel = form.car_model.value;
        var engineVolume = form.engine_volume.value;
        var vin = form.vin.value;
        var color = form.color.value;
        var mileage = form.mileage.value;
        var year = form.year.value;
        var price = form.price.value;

        if (result) {
            result = testModel(carModel);
        }
        if (result) {
            result = testNumericForEngineVolume(engineVolume);
        }
        if (result) {
            result = testVin(vin);
        }
        if (result) {
            result = testColor(color);
        }
        if (result) {
            result = testNumericForMileage(mileage);
        }
        if (result) {
            result = testNumericForYear(year);
        }
        if (result) {
            result = testNumericForPrice(price);
        }
        return result;
    }

    function checkAllFields() {
        var result = true;
        var info_panel = document.getElementById("info_panel");
        var allSelects = document.getElementsByTagName("select");
        var allTextInputs = document.getElementsByTagName("input");

        for (var i = 0; i < allSelects.length; i++) {
            allSelects[i].style.borderColor = "";
            allSelects[i].style.boxShadow = "";
            info_panel.innerHTML = "";
        }
        for (var i = 0; i < allTextInputs.length; i++) {
            allTextInputs[i].style.borderColor = "";
            allTextInputs[i].style.boxShadow = "";
            info_panel.innerHTML = "";
        }

        for (var i = 0; i < allSelects.length; i++) {
            if (allSelects[i].value === '') {
                allSelects[i].style.borderColor = "#c2181a";
                info_panel.innerHTML = "Заполните все выделенные поля";
                allSelects[i].style.boxShadow = "0 0 3px 3px #ff191c";
                result = false;
            }
        }

        for (var i = 0; i < allTextInputs.length; i++) {
            if (allTextInputs[i].value === '' && allTextInputs[i].type === "text") {
                allTextInputs[i].style.borderColor = "#c2181a";
                info_panel.innerHTML = "Заполните все выделенные поля";
                allTextInputs[i].style.boxShadow = "0 0 3px 3px #ff191c";
                result = false;
            }
        }
        return result;
    }


    function addOneMoreInput(inputElement) {
        var fileInputs = document.getElementsByClassName("input_photo");
        var filesAmount = fileInputs.length;
        var thisId = inputElement.id;
        var lastInputElementId = fileInputs[filesAmount - 1].id;
        if (checkFileSize(inputElement) && filesAmount < 5 && thisId === lastInputElementId) {
            var photoContainer = document.getElementsByClassName('photo_container')[0];
            var newDiv = document.createElement("div");
            newDiv.setAttribute("class", "file_input_container");
            newDiv.appendChild(createFileInputElement("input_photo" + (filesAmount + 1)));
            photoContainer.appendChild(newDiv)
        }
    }

    function checkFileSize(inputElement) {
        var info_panel = document.getElementById("info_panel");
        info_panel.innerHTML = "";
        var result = true;
        fileSizeIsValidated = true;
        if (inputElement.files.length > 0) {
            var fileSize = inputElement.files[0].size;
            if (fileSize > 307_200) {
                result = false;
                fileSizeIsValidated = false;
                info_panel.innerHTML = "Размер файла превышает 300 КБ!";
                var id = inputElement.id;
                var parentElement = inputElement.parentNode;
                inputElement.parentNode.removeChild(inputElement);
                parentElement.appendChild(createFileInputElement(id));
            }
        }

        fileSizeIsValidated = true;
        return result;
    }

    function createFileInputElement(id) {
        var newInput = document.createElement("input");
        newInput.setAttribute("class", "input_photo");
        newInput.setAttribute("id", id);
        newInput.setAttribute("form", "issue_form");
        newInput.setAttribute("type", "file");
        newInput.setAttribute("name", "files");
        newInput.setAttribute("onchange", "addOneMoreInput(this)");
        return newInput;
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

    function getCarBrandList(listOfCarBrands) {
        var options = '<option selected disabled value="">Выберите бренд</option>';
        for (var i = 0; i < listOfCarBrands.length; i++) {
            options += "<option value='" + listOfCarBrands[i].carBrand + "'>"
                + listOfCarBrands[i].carBrand + "</option>\r\n";
        }
        carBrandSelector.html(options);
    }

    function getCarBodyList(listOfCarBodies) {

        var options = '<option selected disabled value="">Выберите тип кузова</option>';
        for (var i = 0; i < listOfCarBodies.length; i++) {
            options += "<option value='" + listOfCarBodies[i].bodyType + "'>"
                + listOfCarBodies[i].bodyType + "</option>\r\n";
        }
        carBodySelector.html(options);
    }

    function getTransmissionList(listOfTransmissions) {

        var options = '<option selected disabled value="">Выберите тип передачи</option>';
        for (var i = 0; i < listOfTransmissions.length; i++) {
            options += "<option value='" + listOfTransmissions[i].transmissionType + "'>"
                + listOfTransmissions[i].transmissionType + "</option>\r\n";
        }
        transmissionSelector.html(options);
    }

    function getEngineList(listOfEngines) {

        var options = '<option selected disabled value="">Выберите тип двигателя</option>';
        for (var i = 0; i < listOfEngines.length; i++) {
            options += "<option value='" + listOfEngines[i].engineType + "'>"
                + listOfEngines[i].engineType + "</option>\r\n";
        }
        engineSelector.html(options);
    }


    var priceInput = document.getElementById("price");
    priceInput.addEventListener('input', function () {
        priceInput.style.backgroundColor = '';
        var value = priceInput.value;
        var result = testNumericForPrice(value);
        if (!result) {
            priceInput.style.backgroundColor = '#f38c84';
        }
    });

    var engineVolumeInput = document.getElementById("engine_volume");
    engineVolumeInput.addEventListener('input', function () {
        engineVolumeInput.style.backgroundColor = '';
        var value = engineVolumeInput.value;
        var result = testNumericForEngineVolume(value);
        if (!result) {
            engineVolumeInput.style.backgroundColor = '#f38c84';
        }
    });

    var yearInput = document.getElementById("year");
    yearInput.addEventListener('input', function () {
        yearInput.style.backgroundColor = '';
        var value = yearInput.value;
        var result = testNumericForYear(value);
        if (!result) {
            yearInput.style.backgroundColor = '#f38c84';
        }
    });

    var vinInput = document.getElementById("vin");
    vinInput.addEventListener('input', function () {
        vinInput.style.backgroundColor = '';
        var value = vinInput.value;
        var result = testVin(value);
        if (!result) {
            vinInput.style.backgroundColor = '#f38c84';
        }
    });

    var colorInput = document.getElementById("color");
    colorInput.addEventListener('input', function () {
        colorInput.style.backgroundColor = '';
        var value = colorInput.value;
        var result = testColor(value);
        if (!result) {
            colorInput.style.backgroundColor = '#f38c84';
        }
    });

    var mileageInput = document.getElementById("mileage");
    mileageInput.addEventListener('input', function () {
        mileageInput.style.backgroundColor = '';
        var value = mileageInput.value;
        var result = testNumericForMileage(value);
        if (!result) {
            mileageInput.style.backgroundColor = '#f38c84';
        }
    });

    function testNumericForPrice(line) {
        var regex = new RegExp("^[0-9]{1,}[\.]{0,1}[0-9]{0,2}$");
        return regex.test(line);
    }

    function testNumericForEngineVolume(line) {
        var regex = new RegExp("^[0-9]{1,}[\.]{0,1}[0-9]{0,1}$");
        return regex.test(line);
    }

    function testNumericForYear(line) {
        var regex = new RegExp("^[0-9]{4}$");
        return regex.test(line);
    }

    function testVin(line) {
        var regex = new RegExp("^[0-9,A-Z]{17}$");
        return regex.test(line);
    }

    function testModel(line) {
        var result = true;
        var info_panel = document.getElementById("info_panel");
        if (line.length > 60) {
            info_panel.innerHTML = "Значение \"Модель\" превышает 60 символов";
            result = false;
        }
        return result;
    }

    function testColor(line) {
        var result = true;
        var info_panel = document.getElementById("info_panel");
        if (line.length > 60) {
            info_panel.innerHTML = "Значение \"Цвет\" превышает 60 символов";
            result = false;
        }
        return result;
    }

    function testNumericForMileage(line) {
        var regex = new RegExp("^[0-9]{1,}$");
        return regex.test(line);
    }


</script>


</body>
</html>
