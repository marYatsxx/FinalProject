<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Online-Pharmacy</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<style>
    option{
        width: 250px;
    }
    <%@include file='../../view/css/style.css' %>
    <%@include file='../../view/css/window.css' %>
</style>
<body>
<div class="main">
    <div class="wrap">
        <jsp:include page="element/header.jsp"/>
        <div class="content">
            <div class="content_resize">
                <div class="mainbar">
                    <h1>Prescription creation</h1><br/>
                    <form method="post" action="pharmacy">
                        <div class="form-wrap">
                            <input type="hidden" name="command" value="createPrescription">
                            <div class="field">
                                <label for="client">Client</label>
                                <select name="client" required>
                                    <option>Choose client</option>
                                    <c:forEach var="client" items="${clients}">
                                        <option value="${client.getId()}">
                                            <c:out value="${client.getName()}"/>
                                            <c:out value="${client.getSurname()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div><br/>
                            <div class="field">
                                <label for="medicine">Medicine</label>
                                <select name="medicine" required>
                                    <option>Choose medicine</option>
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
                        <div class="submit_button"><input id="button" type="submit" value="Create"></div>
                    </form>

                    <c:set var="success" value="success"/>
                    <c:if test="${requestScope.result==success}">
                        <h1>RESULT SUCCESS</h1>
                        <c:redirect url="pharmacy?command=createPrescription#success"/>
                    </c:if>
                    <div id="success">
                        <div class="window">
                            <br/>
                            <h2>Prescription has been created successfully.</h2>
                            <br/>
                            <a href="#" class="win_button">Create new</a>
                            <a href="pharmacy?command=viewProfile" class="win_button">My account</a>
                        </div>
                    </div>
                    <c:set var="failure" value="failure"/>
                    <c:if test="${requestScope.result==failure}">
                        <c:redirect url="pharmacy?command=createPrescription#failure"/>
                    </c:if>
                    <div id="failure">
                        <div class="window">
                            <br/>
                            <h2>Operation failed</h2>
                            <br/>
                            <a href="#" class="win_button">OK</a>
                        </div>
                    </div>
                </div>
                <jsp:include page="element/menu.jsp"/>
                <div class="clr"></div>
            </div>
        </div>
    </div>
    <jsp:include page="element/footer.jsp"/>
</div>
</body>
</html>