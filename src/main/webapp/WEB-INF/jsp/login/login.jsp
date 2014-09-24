<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value='/style/main.css' /> "/>
</head>
<body>
<div class="message">
    <c:out value="${message}"/>
</div>
<form action='<c:url value="/index.do" />' method="post">

    <section>
        <div>
            <label for="email">E-mail</label>
            <input type="email" name="email" id="email"/>
        </div>
    </section>
    <section>
        <div>
            <label for="password">Senha</label>
            <input type="password" name="password" id="password"/>
        </div>
    </section>
    <br/>
    <button type="submit">Acessar</button>
    <section>
        <div class="full">
            <a href='<c:url value="/reemitir.do" />'>Esqueci minha senha!</a>
        </div>
    </section>
</form>
</body>
</html>
