<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="/resources/css/ticket.css" %>
        <%@include file="/resources/css/bootstrap.css" %>
    </style>


    <title>Оформление билета</title>
</head>
<body>
<div class="container">

    <div class="col-md-4">
    </div>

    <div class="col-md-4">
        <div class="info-margin"></div>
        <div class="info">Ряд: ${param.row},  Место:  ${param.place}</div>
        <div class="info"> Цена: ${param.cost}</div>
        <br>
        <form method='post' action="${pageContext.servletContext.contextPath}\create_ticket" id="createForm">
            <div class="form-group">
                <label for="name">Имя:</label><input type='text' class="form-control" title="name" name="name"
                                                      id="name" placeholder="1-20 символов" >
            </div>
            <div class="form-group">
                <label for="surname">Фамилия:</label><input type='text' class="form-control" title="surname" name="surname"
                                                        id="surname" placeholder="1-20 символов" >
            </div>
            <div class="form-group">
                <label for="phone">Телефон:</label><input type='text' class="form-control" title="phone" name="phone"
                                                        id="phone" placeholder="+XXXXXXXXXXXX">
            </div>
            <input type='hidden'  name="row" value=${param.row}>
            <input type='hidden'  name="place" value=${param.place}>
            <input type='hidden'  name="cost" value=${param.cost}>

            <div class="form-group">

            </div>
        </form>


        <div class="container">

            <div class="col-sm-1">

                <div class="form-group">
                    <button class="btn-sm" style="width: 120px" onclick=validate()>Оформить</button>
                </div>
            </div>
            <div class="col-sm-1"></div>
            <div class="col-sm-1">
                <form action="${pageContext.servletContext.contextPath}">
                    <div class="form-group">
                        <button class="btn-sm">Назад</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-4">
        </div>
    </div>
</div>

<div class="container">
    <div class="col-md-4">
    </div>
    <div id="infoBlock" class="col-md-4">
        <c:if test="${serverAnswer != ''}">${serverAnswer}</c:if>
    </div>
    <div class="col-md-4">
    </div>
</div>

<script>
   function validate() {
       var form = document.getElementById("createForm");
       var name = form.name.value;
       var surname = form.surname.value;
       var phone = form.phone.value;
       var result = true;

       if (name == '') {
           result = false;
           document.getElementById("infoBlock").innerHTML = 'Поле Имя не может быть пустым';
       } else if(!checkLength (name, 20)) {
           result = false;
           document.getElementById("infoBlock").innerHTML = 'Длина поля Имя не может быть больше 20 символов';
       } else if (surname == '') {
           result = false;
           document.getElementById("infoBlock").innerHTML = 'Поле Фамилия не может быть пустым';
       } else if(!checkLength (surname, 20)) {
           result = false;
           document.getElementById("infoBlock").innerHTML = 'Длина поля Фамилия не может быть больше 20 символов';
       }else if (phone == '') {
           result = false;
           document.getElementById("infoBlock").innerHTML = 'Поле Телефон не может быть пустым';
       } else if(!checkPhone(phone)) {
           result = false;
           document.getElementById("infoBlock").innerHTML = 'Введенный номер не соотвествует формату';
       }
       if (result) {
           submitForm();
       }


   }

   function checkLength(fieldValue, maxLength) {
       return fieldValue.length <= maxLength;
   }

   function checkPhone(phoneNumber) {
       var regex = new RegExp("^\\+[0-9]{12}$");
       return regex.test(phoneNumber);
   }


   function submitForm() {
       var form = document.getElementById("createForm");
       form.submit();
   }


</script>


</body>
</html>
