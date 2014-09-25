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
    <link rel="stylesheet" href="<c:url value='/style/main.css' /> "/>
</head>
<body>
<div class="navbar navbar-top navbar-inverse">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="/">

            </a>

            <!-- the new toggle buttons -->
            <ul class="nav pull-right">
                <li class="toggle-primary-sidebar hidden-desktop" data-toggle="collapse"
                    data-target=".nav-collapse-primary"><a><i class="icon-th-list"></i></a></li>
                <li class="collapsed hidden-desktop" data-toggle="collapse" data-target=".nav-collapse-top"><a><i
                        class="icon-align-justify"></i></a></li>
            </ul>
        </div>
    </div>
</div>
<div id="corpo" class="container-fluid padded">

    <div class="container">

        <div class="offset">

            <div class="padded">
                <fmt:setLocale value="pt-BR"/>

                <c:if test="${not empty message}">
                    <div class="alert alert-success">
                        <a class="close" data-dismiss="alert" href="#">×</a>
                        <p style="margin: 0 0 0px;">
                            <i class="icon-exclamation-sign"></i>
                            &nbsp;&nbsp;<c:out value="${message}"/>
                        </p>
                    </div>
                </c:if>

                <table>
                    <thead>
                    <tr>
                        <td>Documento</td>
                        <td>Vencimento</td>
                        <td>Valor</td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${boletos}" var="boleto">
                        <tr>
                            <td>
                                <a href='<c:url value="/boleto/reemitir.do" />?id=${boleto.id}'>${boleto.numeroDoDocumento}</a>
                            </td>
                            <td><a href='<c:url value="/boleto/reemitir.do" />?id=${boleto.id}'><fmt:formatDate
                                    value="${boleto.dataVencimento}" pattern="dd-MM-yyyy"/></a></td>
                            <td><a href='<c:url value="/boleto/reemitir.do" />?id=${boleto.id}'><fmt:formatNumber
                                    value="${boleto.valorBoleto}" type="currency" minFractionDigits="2"/></a></td>
                            <td><a href='<c:url value="/boleto/imprimir.do" />?id=${boleto.id}' target="_blank">Imprimir</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
