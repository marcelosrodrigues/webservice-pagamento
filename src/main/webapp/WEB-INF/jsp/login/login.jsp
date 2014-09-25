<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value='/style/application.css' /> "/>
    <link rel="stylesheet" href="<c:url value='/style/sdm.css' /> "/>
</head>
<body>

<div id="corpo" class="container-fluid padded">

    <div class="container">

        <div class="message">
            <c:out value="${message}"/>
        </div>

        <div class="span5 offset">

            <div class="padded">

                <div class="login box" style="margin-top: 80px;">
                    <div class="box-header">
                        <span class="title">Autentição de Beneficiário</span>
                    </div>
                    <div class="box-content padded">
                    <form action='<c:url value="/index.do" />' method="post" class="separate-sections">

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
                        <button type="submit" class="btn btn-blue btn-block"><i class="icon-off icon-white"></i>&nbsp;&nbsp;Entrar</button><br/>
                        <section>
                            <div class="full">
                                <a href='<c:url value="/reemitir.do" />'>Esqueci minha senha!</a>
                            </div>
                        </section>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
