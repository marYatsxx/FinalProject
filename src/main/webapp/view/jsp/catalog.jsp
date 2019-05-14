<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Online-Pharmacy</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<style>
    <%@include file='../../view/css/style.css' %>
    <%@include file='../../view/css/catalog.css' %>
</style>
<body>
<div class="main">
    <jsp:include page="element/header.jsp"/>
    <div class="content">
        <div class="content_resize">
            <div class="mainbar">
                <div class="product-item">
                    <img src="view/images/medicine.jpg">
                    <div class="product-list">
                        <h3>Amoxicillin 500mg</h3>
                        <span class="price">10.00</span>
                        <a href="" class="button">Buy</a>
                    </div>
                </div>
                <div class="product-item">
                    <img src="view/images/medicine.jpg">
                    <div class="product-list">
                        <h3>Calcium 500mg</h3>
                        <span class="price">10.00</span>
                        <a href="" class="button">Buy</a>
                    </div>
                </div>
                <div class="product-item">
                    <img src="view/images/medicine.jpg">
                    <div class="product-list">
                        <h3>Flukonazol 150mg</h3>
                        <span class="price">10.00</span>
                        <a href="" class="button">Buy</a>
                    </div>
                </div>
                <div class="product-item">
                    <img src="view/images/medicine.jpg">
                    <div class="product-list">
                        <h3>Flukonazol 50mg</h3>
                        <span class="price">10.00</span>
                        <a href="" class="button">Buy</a>
                    </div>
                </div>
                <div class="product-item">
                    <img src="view/images/medicine.jpg">
                    <div class="product-list">
                        <h3>Amoxiclav 250mg</h3>
                        <span class="price">10.00</span>
                        <a href="" class="button">Buy</a>
                    </div>
                </div>
                <div class="product-item">
                    <img src="view/images/medicine.jpg">
                    <div class="product-list">
                        <h3>Amoxiclav 750mg</h3>
                        <span class="price">10.00</span>
                        <a href="" class="button">Buy</a>
                    </div>
                </div>
                <div class="product-item">
                    <img src="view/images/medicine.jpg">
                    <div class="product-list">
                        <h3>Mildronat 250mg</h3>
                        <span class="price">10.00</span>
                        <a href="" class="button">Buy</a>
                    </div>
                </div>
                <div class="product-item">
                    <img src="view/images/medicine.jpg">
                    <div class="product-list">
                        <h3>Mildronat 500mg</h3>
                        <span class="price">10.00</span>
                        <a href="" class="button">Buy</a>
                    </div>
                </div>
                <div class="product-item">
                    <img src="view/images/medicine.jpg">
                    <div class="product-list">
                        <h3>L-Thyroxinum 25mg</h3>
                        <span class="price">10.00</span>
                        <a href="" class="button">Buy</a>
                    </div>
                </div>
            </div>
            <jsp:include page="element/menu.jsp"/>
            <div class="clr"></div>
        </div>
    </div>
    <jsp:include page="element/footer.jsp"/>
</body>
</html>