<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="site.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<style>
    select{
        width: 250px;
    }
    .form-wrap{
        margin-left: 220px;
    }
    <%@include file='../../view/css/style.css' %>
    <%@include file='../../view/css/window.css' %>
</style>
<body>
<div class="main">
    <div class="wrap">
        <%@include file="element/header.jspf"%>
        <div class="content">
            <div class="content_resize">
                <div class="mainbar">
                    <h1><fmt:message key="button.prescription"/></h1><br/>
                    <form method="post" action="pharmacy">
                        <div class="form-wrap">
                            <input type="hidden" name="command" value="createPrescription">
                            <div class="field">
                                <label for="client"><fmt:message key="user.role.client"/></label>
                                <select name="client" required>
                                    <option><fmt:message key="client.option"/></option>
                                    <c:forEach var="client" items="${clients}">
                                        <option value="${client.getId()}">
                                            <c:out value="${client.getName()}"/>
                                            <c:out value="${client.getSurname()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div><br/>
                            <div class="field">
                                <label for="medicine"><fmt:message key="medicine"/></label>
                                <select name="medicine" required>
                                    <option><fmt:message key="medicine.option"/></option>
                                    <c:forEach var="medicine" items="${medicines}">
                                        <option value="${medicine.getId()}">
                                            <c:out value="${medicine.getName()}"/>
                                            <c:out value="${medicine.getDosage()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div><br/>
                        </div>
                        <br/>
                        <div class="submit_button">
                            <input id="button" type="submit" value="<fmt:message key="button.create"/>">
                        </div>
                    </form>

                    <c:set var="success" value="success"/>
                    <c:if test="${requestScope.result==success}">
                        <c:redirect url="pharmacy?command=viewPrescription#success"/>
                    </c:if>
                    <div id="success">
                        <div class="window">
                            <br/>
                            <h2><fmt:message key="prescription.success.title"/></h2>
                            <br/>
                            <a href="#" class="win_button"><fmt:message key="button.new"/></a>
                            <a href="pharmacy?command=viewProfile" class="win_button"><fmt:message key="menu.profile"/></a>
                        </div>
                    </div>
                    <c:set var="failure" value="failure"/>
                    <c:if test="${requestScope.result==failure}">
                        <c:redirect url="pharmacy?command=viewPrescription#failure"/>
                    </c:if>
                    <div id="failure">
                        <div class="window">
                            <br/>
                            <h2><fmt:message key="failure.title"/></h2>
                            <br/>
                            <a href="#" class="win_button"><fmt:message key="button.close"/></a>
                        </div>
                    </div>
                </div>
                <%@include file="element/menu.jspf"%>
                <div class="clr"></div>
            </div>
        </div>
    </div>
    <%@include file="element/footer.jspf"%>
</div>
</body>
</html>