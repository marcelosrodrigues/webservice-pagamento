<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value='/style/application.css' /> "/>
    <link rel="stylesheet" href="<c:url value='/style/sdm.css' /> "/>
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
