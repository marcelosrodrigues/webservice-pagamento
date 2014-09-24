<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value='/style/main.css' /> "/>
</head>
<body>

<form action="<c:url value='/reemitir.do' />" method="post">
    <c:out value="${message}" />
    <section><div>
    <label for="cpf">CPF</label>
    <input type="text" id="cpf" name="cpf" />
    </div></section>
    <br>
    <button type="submit">Reemitir</button>
</form>
</body>
</html>
