<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="header">
    <div class="header_resize">
        <div class="logo"><h1><a href="pharmacy?command=viewHomePage">Online-Pharmacy</a></h1></div>
        <div class="menu_nav">
            <ul>
                <c:if test="${empty sessionScope.user}">
                    <li><a href="pharmacy?command=login"><span>LOG IN</span></a></li>
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                    <li><a href="pharmacy?command=logout"><span>LOG OUT</span></a></li>
                </c:if>
                <li><a href="#"><span>EN</span></a></li>
                <li><a href="#"><span>RU</span></a></li>
            </ul>
        </div>
        <div class="clr"></div>
    </div>
</div>