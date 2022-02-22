<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<meta charset="utf-8">

<title>Register New User Form</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
    .error {
        color: red
    }
</style>

<body>

<div>

    <div id="loginbox" style="margin-top: 50px; position: center;"
         class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-primary">

            <div class="panel-heading">
                <div class="panel-title">Register New User</div>
            </div>

            <div style="padding-top: 30px" class="panel-body">

                <form:form action="processRegistrationForm"
                           modelAttribute="validationForm"
                           class="form-horizontal">

                    <!-- Place for messages: error, alert etc ... -->
                    <div class="form-group">
                        <div class="col-xs-15">
                            <div>

                            </div>
                        </div>
                    </div>

                    <!-- Email -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:errors path="email" cssClass="error"/>
                        <form:input path="email" minlength="3" required="required"
                                    pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="email (*)"
                                    class="form-control"/>
                    </div>

                    <!-- Password -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <form:errors path="password" cssClass="error"/>
                        <form:password path="password" required="required" id="password" name="password"
                                       pattern="^\S{3,}$"
                                       onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 6 characters' : ''); if(this.checkValidity()) form.password_two.pattern = this.value;"
                                       placeholder="Password" class="form-control"/>
                    </div>

                    <!-- Confirm Password -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <form:errors path="matchingPassword" cssClass="error"/>
                        <form:password path="matchingPassword" required="required" id="password_two" name="password_two"
                                       pattern="^\S{6,}$"
                                       onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above' : '');"
                                       placeholder="Verify Password" class="form-control"/>
                    </div>

                    <!-- First name -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:errors path="firstName" cssClass="error"/>
                        <form:input path="firstName" required="required" minlength="3" placeholder="first name (*)"
                                    class="form-control"/>
                    </div>

                    <!-- Last name -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:errors path="lastName" cssClass="error"/>
                        <form:input path="lastName" required="required" placeholder="last name (*)" minlength="3"
                                    class="form-control"/>
                    </div>

                    <!-- Age -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:errors path="age" cssClass="error"/>
                        <form:input path="age" required="required" placeholder="age (*)"
                                    class="form-control"/>
                    </div>

                    <!-- Phone -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:errors path="phone" cssClass="error"/>
                        <form:input path="phone" required="required" placeholder="phone (*)" minlength="3"
                                    class="form-control"/>
                    </div>

                    <!-- Register Button -->
                    <div style="margin-top: 10px" class="form-group">
                        <div class="col-sm-6 controls">
                            <button type="submit" class="btn btn-primary">Register</button>
                        </div>
                    </div>

                </form:form>

            </div>

        </div>

    </div>

</div>

</body>
</html>