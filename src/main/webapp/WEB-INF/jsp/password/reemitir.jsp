<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title></title>
</head>
<body>

<form action="<c:url value='/reemitir.do' />" method="post">
    <c:out value="${message}" />
    <label for="cpf">CPF:</label>
    <input type="text" id="cpf" name="cpf" />
    <button type="submit">Reemitir</button>
</form>
</body>
</html>
