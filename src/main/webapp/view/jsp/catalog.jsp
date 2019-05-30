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
    <%@include file='../../view/css/style.css' %>
    <%@include file='../../view/css/catalog.css' %>
    <%@include file='../../view/css/window.css' %>
</style>
<body>
<div class="main">
    <div class="wrap">
        <%@include file="element/header.jspf" %>
        <div class="content">
            <div class="content_resize">
                <div class="mainbar">
                    <h1><fmt:message key="menu.medicine"/></h1>
                    <div class="catalog">
                        <c:if test="${pageCount>pageAmount}">
                            ${pageCount=pageAmount}
                        </c:if>
                        <c:if test="${pageCount<1}">
                            ${pageCount=1}
                        </c:if>
                        <c:forEach var="med" items="${medicine_list}" begin="${pageCount*9-9}" end="${pageCount*9-1}">
                            <div class="product-item">
                                <img src="view/images/medicine.jpg">
                                <div class="product-list">
                                    <h3><c:out value="${med.getName()}"/> <c:out value="${med.getDosage()}"/></h3>
                                    <span class="price"><c:out value="${med.getPrice()} BYN"/></span>
                                    <c:if test="${user.getUserRoleId()==3}">
                                        <c:if test="${med.isNeedsPrescription()==true}">
                                            <a href="pharmacy?command=viewCatalog&pageCount=${pageCount}&medicine_id=${med.getId()}#prescriptionCheck"
                                               class="button"><fmt:message key="button.buy"/></a>
                                        </c:if>
                                        <c:if test="${med.isNeedsPrescription()==false}">
                                            <a href="pharmacy?command=viewCatalog&pageCount=${pageCount}&medicine_id=${med.getId()}#confirmation"
                                               class="button"><fmt:message key="button.buy"/></a>
                                        </c:if>
                                    </c:if>
                                </div>
                            </div>

                            <div id="prescriptionCheck">
                                <div class="window">
                                    <h2><fmt:message key="catalog.prescriptioncheck.title"/></h2>
                                    <p><fmt:message key="catalog.prescriptioncheck.label"/></p>
                                    <form>
                                        <input type="hidden" name="command" value="checkPrescription"/>
                                        <c:set var="medicine_id" value="${requestScope.medicine_id}"/>
                                        <input type="hidden" name="medicine_id" value="${medicine_id}"/>
                                        <br/>
                                        <input class="win_button" type="submit"
                                               value="<fmt:message key="button.check"/>"/>
                                        <a href="#" class="win_button"><fmt:message key="button.close"/></a>
                                    </form>
                                </div>
                            </div>
                            <div id="confirmation">
                                <div class="window">
                                    <br/>
                                    <h2><fmt:message key="catalog.confirmation.title"/></h2>
                                    <br/>
                                    <form>
                                        <input type="hidden" name="command" value="addToOrder"/>
                                        <c:set var="medicine_id" value="${medicine_id}"/>
                                        <input type="hidden" name="medicine_id" value="${medicine_id}"/>
                                        <input class="win_button" type="submit"
                                               value="<fmt:message key="button.buy"/>"/>
                                        <a href="#" class="win_button"><fmt:message key="button.close"/></a>
                                    </form>
                                </div>
                            </div>
                            <c:if test="${requestScope.containsValue(result)}">
                                <c:redirect url="/pharmacy?command=viewCatalog&pageCount=${pageCount}#ok"/>
                            </c:if>
                            <c:if test="${requestScope.containsValue(hasPrescription)}">
                                <c:if test="${hasPrescription==false}">
                                    <c:redirect url="/pharmacy?command=viewCatalog&pageCount=${pageCount}#notFound"/>
                                </c:if>
                                <c:if test="${hasPrescription==true}">
                                    <c:redirect
                                            url="pharmacy?command=viewCatalog&pageCount=${pageCount}&medicine_id=${medicine_id}#confirmation"/>
                                </c:if>
                            </c:if>
                            <div id="ok">
                                <div class="window">
                                    <br/>
                                    <h2><fmt:message key="catalog.ok.title"/></h2>
                                    <br/>
                                    <a href="#" class="win_button"><fmt:message key="button.close"/></a>
                                </div>
                            </div>
                            <div id="notFound">
                                <div class="window">
                                    <br/>
                                    <h2><fmt:message key="catalog.notfound.title"/></h2>
                                    <br/>
                                    <a href="#" class="win_button"><fmt:message key="button.close"/></a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <br/>
                    <div class="arrow">
                        <a href="pharmacy?command=viewCatalog&pageCount=${pageCount=pageCount-1}"><img
                                src="view/images/arrow.png"></a>
                        <a href="pharmacy?command=viewCatalog&pageCount=${pageCount=pageCount+2}"><img
                                src="view/images/right_arrow.png"></a>
                    </div>
                </div>
                <%@include file="element/menu.jspf" %>
                <div class="clr"></div>
            </div>
        </div>
    </div>
    <%@include file="element/footer.jspf" %>
</div>
</body>
</html>