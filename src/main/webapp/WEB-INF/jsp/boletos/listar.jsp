<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title></title>

    <style>
        * {
            font-family: 'Lucida Grande', 'Lucida Sans Unicode', Helvetica, Arial, Verdana, sans-serif;
            font-size: 12px;
            color: #333333;
        }
        table {
            display: table;
            border-collapse: separate;
            border-spacing: 2px;
            border-color: gray;
            text-align: center;
            width: 90%;
        }
        table thead {
            background: #e5e5e5;
        }
        A:visited {
            COLOR: #666666;
            TEXT-DECORATION: none;
        }
        A:link {
            COLOR: #666666;
            TEXT-DECORATION: none;
        }
        a:webkit-any-link {
            color: -webkit-link;
            text-decoration: underline;
            cursor: auto;
        }
    </style>
</head>
<body>

<div class="message">
    <c:out value="${message}" />
</div>
<fmt:setLocale value="pt-BR" />
<table>
    <thead>
        <tr>
            <td>Documento</td>
            <td>Vencimento</td>
            <td>Valor</td>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${boletos}" var="boleto">
            <tr>
                <td><a href='<c:url value="/boleto/reemitir.do" />?id=${boleto.id}'>${boleto.numeroDoDocumento}</a></td>
                <td><a href='<c:url value="/boleto/reemitir.do" />?id=${boleto.id}'><fmt:formatDate value="${boleto.dataVencimento}" pattern="dd-MM-yyyy" /></a></td>
                <td><a href='<c:url value="/boleto/reemitir.do" />?id=${boleto.id}'><fmt:formatNumber value="${boleto.valorBoleto}" type="currency" minFractionDigits="2" /></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
