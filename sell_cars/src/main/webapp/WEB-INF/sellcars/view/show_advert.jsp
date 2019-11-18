<%--
  Created by IntelliJ IDEA.
  User: Ruben
  Date: 09.10.2019
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file="/resources/css/show_advert.css" %>
    <%@include file="/resources/css/sold_popup.css" %>
</style>
<html>
<head>
    <title>Title</title>
</head>
<body>

${sessionScope.get("advert").id}


<div class="main_container">

    <div id="photo_container" class="photo_container">
    </div>

    <div class="sold_container" id="sold_container">
    </div>


    <div class="buttons_container">
        <div class="left_button_container">
            <button id="back_button" onClick='location.href="${pageContext.servletContext.contextPath}"'>Назад</button>
        </div>
        <div id="right_button_container" class="right_button_container">
            <div id="checkbox_container">
                <input type="checkbox" id="is_sold_checkbox"><label for="is_sold_checkbox">Присвоить статуc
                "Продано" </label>
            </div>
            <button id="submit_button" type="button">Ok</button>
        </div>
    </div>


    <div id="car_info_container" class="car_info_container">

        <div id="car_brand_container" class="car_brand_container">
        </div>
        <div id="car_model_container" class="car_model_container">
        </div>
        <div id="body_type_container" class="body_type_container">
        </div>
        <br>
        <div id="engine_type_container" class="engine_type_container">
        </div>
        <div id="engine_volume_container" class="engine_volume_container">
        </div>
        <div id="transmission_container" class="transmission_container">
        </div>
        <br>
        <div id="vin_container" class="vin_container">
        </div>
        <div id="mileage_container" class="mileage_container">
        </div>
        <div id="year_container" class="year_container">
        </div>
        <br>
        <div id="color_container" class="color_container">
        </div>
        <div id="price_container" class="price_container">
        </div>
        <hr>
        <div id="seller_container" class="seller_container">
        </div>
        <div id="date_container" class="date_container">
        </div>
        <br>
        <div id="phone_container" class="phone_container">
        </div>
        <div id="email_container" class="email_container">
        </div>

    </div>


</div>

<div class="modal" id="modal">
    <div class="modal-wrapper">
        <div class="modal-header">
            <span class="closeButton">&times;</span>
            Присвоить статус "Продано"
        </div>
        <div class="modal-body" id="modal-body">

        </div>
        <div class="modal-footer">
            <button id="ok" class="ok">Да</button>
            <button id="cancel" class="cancel">Отменить</button>
        </div>
    </div>
</div>


<script>
    var advertId;
    var jsonObj = JSON.stringify({"getAdvert":${param.id}});
    sendAjaxRequest(jsonObj, loadAdvert);

    var photos = [];

    function addAllPhotos(data) {
        for (var i = 0; i < data.photos.length; i++) {
            photos[i] = data.photos[i].fileName;
        }
    }

    var checkBox = document.getElementById("is_sold_checkbox");
    var okButton = document.getElementById("ok");
    var cancelButton = document.getElementById("cancel");


    okButton.addEventListener('click', function () {
        var popupWindow = document.getElementById("modal");
        popupWindow.style.display = 'none';
        setSoldStatus();

    });

    cancelButton.addEventListener('click', function () {
        var popupWindow = document.getElementById("modal");
        popupWindow.style.display = 'none';
    });

    checkBox.addEventListener('change', function () {
        if (this.checked) {
            addSubmitButtonListener();
        } else {
            removeSubmitButtonListener();
        }
    });

    function addSubmitButtonListener() {
        var submitButton = document.getElementById("submit_button");
        submitButton.addEventListener('click', submitButtonListener);
    }

    function removeSubmitButtonListener() {
        var submitButton = document.getElementById("submit_button");
        submitButton.removeEventListener('click', submitButtonListener);
    }

    var submitButtonListener = function () {
        var popupWindow = document.getElementById("modal");
        var infoBlock = document.getElementById("modal-body");
        infoBlock.innerHTML = "Вы уверены, что хотите присвоить объявлению cтатус \"Продано\"?";
        popupWindow.style.display = 'flex';
    };


    function setSoldStatus() {
        var jsonObj = JSON.stringify({"setSoldStatus": advertId});
        sendAjaxRequest(jsonObj, hideRightButtonContainer);
    }


    function hideRightButtonContainer(data) {
        if (data.result === "OK") {
            var jsonObj = JSON.stringify({"getAdvert":${param.id}});
            sendAjaxRequest(jsonObj, showAdvertInfo);
            var rightButtonContainer = document.getElementById("right_button_container");
            rightButtonContainer.style.display = 'none';
        }

    }


    function displayPhotos(data) {
        var photoContainer = document.getElementById("photo_container");
        for (var i = 0; i < photos.length; i++) {
            var imgPath = "${pageContext.servletContext.contextPath}/picture?folder=" + data.car.id + "&fileName=" + photos[i];
            var slides_fade = document.createElement("div");
            slides_fade.setAttribute("class", "slides");
            photoContainer.appendChild(slides_fade);
            var number_text = document.createElement("div");
            number_text.setAttribute("class", "number_text");
            slides_fade.appendChild(number_text);
            number_text.innerHTML = i + 1 + "/" + photos.length;
            var img = document.createElement("IMG");
            img.setAttribute("class", "image");
            img.setAttribute("src", imgPath);
            slides_fade.appendChild(img);
        }


        if (photos.length > 1) {
            var previous = document.createElement("a");
            previous.setAttribute('class', 'previous');
            previous.setAttribute('onclick', 'plusSlide(-1)');
            previous.innerHTML = "&#10094";

            var next = document.createElement("a");
            next.setAttribute('class', 'next');
            next.setAttribute('onclick', 'plusSlide(1)');
            next.innerHTML = "&#10095";

            photoContainer.appendChild(previous);
            photoContainer.appendChild(next);

            var dotsDiv = document.createElement("div");
            dotsDiv.setAttribute("class", "dots_div");
            photoContainer.appendChild(dotsDiv);

            for (var i = 0; i < photos.length; i++) {
                var dot = document.createElement("span");
                dot.setAttribute("class", "dot");
                dot.setAttribute("onclick", "currentSlide(" + i + ")");
                dotsDiv.appendChild(dot);
            }
        }


    }

    function displayDefaultPhoto() {
        var photoContainer = document.getElementById("photo_container");
        var imgPath = "${pageContext.servletContext.contextPath}/picture?folder=&fileName=NoPhoto.jpg";
        var slides_fade = document.createElement("div");
        slides_fade.setAttribute("class", "slides");
        photoContainer.appendChild(slides_fade);
        var img = document.createElement("IMG");
        img.setAttribute("class", "image");
        img.setAttribute("src", imgPath);
        slides_fade.appendChild(img);
    }

    function loadAdvert(data) {
        showUpdateButton(data);
        addAllPhotos(data);
        if (photos.length > 0) {
            displayPhotos(data);
        } else {
            displayDefaultPhoto();
        }
        showSlides(slideIndex);
        showAdvertInfo(data);
    }

    function showUpdateButton(advert) {
        var advertLogin = advert.user.login;
        var userLogin1 = "${userLogin}";
        if (advertLogin === userLogin1 && !advert.sold) {
            var editButton = document.getElementById("right_button_container");
            editButton.style.display = "block";
        }
    }

    function showAdvertInfo(data) {
        console.log(data);
        advertId = data.id;
        document.getElementById("car_brand_container").innerHTML = "<b>Марка:</b> " + data.car.carBrand.carBrand;
        document.getElementById("car_model_container").innerHTML = "<b>Модель:</b> " + data.car.carModel;
        document.getElementById("body_type_container").innerHTML = "<b>Тип кузова:</b> " + data.car.bodyType.bodyType;
        document.getElementById("engine_type_container").innerHTML = "<b>Двигатель:</b> " + data.car.engine.engineType;
        document.getElementById("engine_volume_container").innerHTML = "<b>Объем:</b> " + data.car.engineVolume;
        document.getElementById("transmission_container").innerHTML = "<b>Тип передачи:</b> " + data.car.transmission.transmissionType;
        document.getElementById("vin_container").innerHTML = "<b>VIN:</b> " + data.car.vin;
        document.getElementById("mileage_container").innerHTML = "<b>Пробег, км:</b> " + data.car.mileage;
        document.getElementById("year_container").innerHTML = "<b>Год выпуска:</b> " + data.car.year;
        document.getElementById("color_container").innerHTML = "<b>Цвет:</b> " + data.car.color;
        document.getElementById("price_container").innerHTML = "<b>Цена: $</b> " + data.price;

        document.getElementById("seller_container").innerHTML = "<b>Продавец: </b> " + data.user.surname + " " + data.user.name;
        document.getElementById("date_container").innerHTML = "<b>Дата объявления: </b> " + data.date;
        document.getElementById("phone_container").innerHTML = "<b>№ телефона: </b> " + data.user.phone;
        document.getElementById("email_container").innerHTML = "<b>E-mail: </b> " + data.user.email;

        if (data.sold) {
            document.getElementById("sold_container").innerHTML = "Продано";
            document.getElementById("sold_container").style.color = "#DB1013"
        } else {
            document.getElementById("sold_container").innerHTML = "Продается";
            document.getElementById("sold_container").style.color = "#17bf18"
        }

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
        });
    }


    var slideIndex = 0;


    function showSlides(n) {
        var slides = document.getElementsByClassName("slides");
        var dot = document.getElementsByClassName("dot");
        if (dot.length > 0) {
            for (i = 0; i < slides.length; i++) {
                if (n === i) {
                    slides[i].style.display = "block";
                    dot[i].style.height = 13;
                    dot[i].style.width = 13;
                } else {
                    slides[i].style.display = "none";
                    dot[i].style.height = 10;
                    dot[i].style.width = 10;
                }
            }
        }

    }

    function plusSlide(n) {
        slideIndex += n;
        console.log(slideIndex);
        if (slideIndex === photos.length) {
            slideIndex = 0
        }
        if (slideIndex === -1) {
            slideIndex = photos.length - 1;
        }
        showSlides(slideIndex);
    }


</script>


</body>
</html>
