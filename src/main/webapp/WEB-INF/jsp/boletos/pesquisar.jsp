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
    <link rel="stylesheet" href="<c:url value='/style/jquery-ui-1.10.4.custom.min.css' /> "/>
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
                <form action="<c:url value='/boletos/pesquisar.do' />" method="post">
                    <div style="margin-top: 20px; margin-bottom: 20px;">
                        <div style="float: left;margin-right: 10px;">
                            <p style="margin: 0 0 0 0px;">&nbsp;</p>
                            <p style="margin: 0 0 0 0px;">Banco</p>
                            <select name="banco" style="width: 100px;">
                                <option></option>
                                <c:forEach items="${bancos}" var="banco">
                                    <option value="${banco}">${banco}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div style="float: left;margin-right: 10px;">
                            <p style="margin: 0 0 0 0px;">Data de Vencimento</p>
                            <p style="margin: 0 0 0 0px;">De:</p>
                            <input type="text" name="inicial" id="inicial" />
                        </div>
                        <div style="float: left;margin-right: 10px;">
                            <p style="margin: 0 0 0 0px;">&nbsp;</p>
                            <p style="margin: 0 0 0 0px;">Até:</p>
                            <input type="text" name="fim" id="fim" />
                        </div>
                        <div style="float: left;margin-right: 10px;">
                            <p style="margin: 0 0 0 0px;">&nbsp;</p>
                            <p style="margin: 0 0 0 0px;">&nbsp;</p>
                            <button type="submit" name="pesquisar" class="btn btn-blue btn-block" style="width: 100px;" value="imprimir"><i class="icon-off icon-white"></i>&nbsp;&nbsp;Imprimir</button>
                        </div>
                        <div style="float: left;margin-right: 10px;">
                            <p style="margin: 0 0 0 0px;">&nbsp;</p>
                            <p style="margin: 0 0 0 0px;">&nbsp;</p>
                            <button type="submit" name="pesquisar" class="btn btn-blue btn-block" style="width: 100px;" value="pesquisar"><i class="icon-off icon-white"></i>&nbsp;&nbsp;Pesquisar</button>
                        </div>
                    </div>
                    <table>
                        <thead>
                        <tr>
                            <td><input type="checkbox" id="todos" /></td>
                            <td>Cliente</td>
                            <td>CPF</td>
                            <td>Documento</td>
                            <td>Vencimento</td>
                            <td>Valor</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${boletos}" var="boleto">
                            <tr>
                                <td><input type="checkbox" id="boleto" name="id" value="${boleto.id}" /></td>
                                <td>
                                   ${boleto.pagador.nome}
                                </td>
                                <td>
                                    ${boleto.pagador.cpf}
                                </td>
                                <td>
                                   ${boleto.numeroDoDocumento}
                                </td>
                                <td><fmt:formatDate
                                        value="${boleto.dataVencimento}" pattern="dd-MM-yyyy"/></td>
                                <td><fmt:formatNumber
                                        value="${boleto.valorBoleto}" type="currency" minFractionDigits="2"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <br />
                    <button type="submit" class="btn btn-blue btn-block" style="width: 100px;"><i class="icon-off icon-white"></i>&nbsp;&nbsp;Imprimir</button>
                </form>
            </div>
        </div>
    </div>

</body>
<script type="text/javascript" src="<c:url value='/js/jquery.color.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.maskMoney.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-maskinput.js' />" ></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.js' />" ></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-datepicker-pt.js' />" ></script>

<script type="text/javascript" src="<c:url value='/js/ui.js' />" ></script>
</html>
