<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Online-Pharmacy</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<style>
    img{
        display: block;
        float: right;
        width: 200px;
        height: 200px;
        text-align: center;
    }
    <%@include file='../css/style.css' %>
</style>
<body>
<div class="main">
    <div class="wrap">
        <jsp:include page="element/header.jsp"/>
        <div class="content">
            <div class="content_resize">
                <h1>ERROR</h1>
                <h2 style="text-align: center">Page not found</h2>
                <img src="view/images/error.png">
            </div>
        </div>
    </div>
    <jsp:include page="element/footer.jsp"/>
</div>
</body>
</html>