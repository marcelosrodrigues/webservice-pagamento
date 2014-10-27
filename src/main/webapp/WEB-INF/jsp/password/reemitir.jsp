<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,800">
    <link rel="shortcut icon" href="/tools/sdm/imagens/favicon16x16.ico" type="image/x-icon">
    <link rel="apple-touch-icon" href="/tools/sdm/imagens/favicon/favicon64x64.png">
    <meta charset="utf-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <link rel="stylesheet" href="<c:url value='/style/application.css' /> "/>
    <link rel="stylesheet" href="<c:url value='/style/sdm.css' /> "/>
    <script type="text/javascript" src="<c:url value='/js/jquery.min.js' />" ></script>
    <script type="text/javascript" src="<c:url value='/js/bootstrap.min.js' />" ></script>
</head>
<body>
<div class="navbar navbar-top navbar-inverse">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="/">

            </a>

            <!-- the new toggle buttons -->
            <ul class="nav pull-right">
                <li class="toggle-primary-sidebar hidden-desktop" data-toggle="collapse" data-target=".nav-collapse-primary"><a><i class="icon-th-list"></i></a></li>
                <li class="collapsed hidden-desktop" data-toggle="collapse" data-target=".nav-collapse-top"><a><i class="icon-align-justify"></i></a></li>
            </ul>
        </div>
    </div>
</div>
<div id="corpo" class="container-fluid padded">

    <div class="container">

        <div class="span5 offset3">

            <div class="padded">

                <div class="login box" style="margin-top: 80px;">
                    <div class="box-header">
                        <span class="title">Autentição de Beneficiário</span>
                    </div>
                    <div class="box-content padded">

                        <form action="<c:url value='/index.do' />" method="post" class="separate-sections">
                            <c:if test="${not empty message}">
                                <div class="alert alert-error">
                                    <a class="close" data-dismiss="alert" href="#">×</a>
                                    <p style="margin: 0 0 0px;">
                                        <i class="icon-exclamation-sign"></i>
                                        &nbsp;&nbsp;<c:out value="${message}"/>
                                    </p>
                                </div>
                            </c:if>
                            <section>
                                <div class="control-group <c:if test='${not empty message}'>error</c:if>">
                                    <label for="cpf" class="control-label">CPF do Titular*</label>
                                    <input type="text" id="cpf" name="cpf" style="width: 250px;"/>
                                </div>
                            </section>
                            <br>
                            <button type="submit" class="btn btn-blue btn-block"><i class="icon-off icon-white"></i>&nbsp;&nbsp;Entrar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value='/js/jquery.color.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.maskMoney.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-maskinput.js' />" ></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.js' />" ></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-datepicker-pt.js' />" ></script>

<script type="text/javascript" src="<c:url value='/js/ui.js' />" ></script>
</body>

</html>
