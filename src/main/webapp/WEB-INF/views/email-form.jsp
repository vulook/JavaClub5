<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Library BRM</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <%@ page isELIgnored="false" %>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>

<body>
<div class="container">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center">Send email</h2>
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Send Mail</div>
            </div>
            <div class="panel-body">
                <form:form action="send" cssClass="form-horizontal"
                           method="get">


                    <input type="text" name="emails" hidden="true" value="${emails}">

                    <div class="form-group">
                        <label for="subject" class="col-md-3 control-label"></label><br>
                        <div class="text-center">
                            <input placeholder="Subject" style="direction: inherit;min-width: 500px;" id="subject" type="text" name="subject">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="message" class="col-md-3 control-label"></label><br>
                        <div class="text-center">
                            <input aria-multiline="true" role="textbox" contenteditable="true" placeholder="Write here your message" style="cursor: auto; direction: inherit;min-width: 500px; min-height: 242px;" id="message" type="text" name="message">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <button cssClass="btn btn-primary">Send</button>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>