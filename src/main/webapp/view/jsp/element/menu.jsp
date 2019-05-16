<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="sidebar">
    <div class="gadget">
        <h2 class="star">Menu</h2>
        <ul class="sb_menu">
            <li><a href="pharmacy?command=viewHomePage">Home</a></li>
            <li><a href="pharmacy?command=viewCatalog&pageCount=1">Medication List</a></li>
            <c:if test="${not empty sessionScope.user}">
                <li><a href="#">Renew Prescription</a></li>
                <li><a href="pharmacy?command=viewProfile">User Account</a></li>
            </c:if>
        </ul>
    </div>
</div>
