<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title></title>
</head>
<body>
    <form action='<c:url value="/index.do" />' method="post">
        <c:out value="${message}" />
        <label for="email">E-mail</label>
        <input type="email" name="email" id="email" />
        <label for="password">Senha</label>
        <input type="password" name="password" id="password" />
        <button type="submit">Acessar</button>
        <a href='<c:url value="/reemitir.do" />'>Esqueci minha senha!</a>
    </form>
</body>
</html>
