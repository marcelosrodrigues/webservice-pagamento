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
                        <span class="title">Reemitir sua senha</span>
                    </div>
                    <div class="box-content padded">

                        <form action="<c:url value='/reemitir.do' />" method="post" class="separate-sections">
                            <c:out value="${message}"/>
                            <section>
                                <div>
                                    <label for="cpf">CPF do Titular*</label>
                                    <input type="text" id="cpf" name="cpf"/>
                                </div>
                            </section>
                            <br>
                            <button type="submit" class="btn btn-blue btn-block"><i class="icon-off icon-white"></i>&nbsp;&nbsp;Reemitir
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
