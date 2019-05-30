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
    <%@include file='../../view/css/window.css' %>
    <%@include file='../../view/css/table.css' %>
</style>
<body>
<div class="main">
    <div class="wrap">
        <%@include file="element/header.jspf" %>
        <div class="content">
            <div class="content_resize">
                <div class="mainbar">
                    <h1><fmt:message key="order.title"/></h1>
                    <div class="order" style="margin-left: 80px">
                        <form action="pharmacy" method="post">
                            <table>
                                <tr>
                                    <th>№</th>
                                    <th><fmt:message key="medicine.name"/></th>
                                    <th><fmt:message key="medicine.price"/></th>
                                    <th><fmt:message key="order.date"/></th>
                                    <th><fmt:message key="request.status"/></th>
                                    <th><fmt:message key="order.select"/></th>
                                </tr>
                                <c:forEach var="order" items="${orders}" varStatus="status">
                                    <tr>
                                        <td><c:out value="${order.getId()}"/></td>
                                        <c:set var="medicine" value="${medicines.get(status.index)}"/>
                                        <td><c:out value="${medicine.getName()}"/>
                                            <c:out value="${medicine.getDosage()}"/></td>
                                        <td><c:out value="${medicine.getPrice()}"/></td>
                                        <td><c:out value="${order.getDate()}"/></td>
                                        <c:if test="${order.isPaid()==false}">
                                            <td><fmt:message key="select.false"/></td>
                                            <td><input type="checkbox" name="select" value="${order.getId()}"></td>
                                        </c:if>
                                        <c:if test="${order.isPaid()==true}">
                                            <td><fmt:message key="select.true"/></td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </table>
                            <br/>
                            <input type="hidden" name="command" value="payForOrders"/>
                            <div class="submit_button">
                                <a id="button" href="#payment"><fmt:message key="button.pay"/></a>
                            </div>
                            <div id="payment">
                                <div class="window">
                                    <h2><fmt:message key="order.payment.title"/></h2>
                                    <p><fmt:message key="order.payment.label"/></p>
                                    <br/>
                                    <input class="win_button" type="submit" value="<fmt:message key="button.buy"/>">
                                    <a href="#" class="win_button"><fmt:message key="button.cancel"/></a>
                                </div>
                            </div>
                        </form>
                        <c:if test="${requestScope.containsValue(result)}">
                            <c:if test="${requestScope.result=='success'}">
                                <c:redirect url="pharmacy?command=viewUserOrders#success"/>
                            </c:if>
                            <c:if test="${requestScope.result=='failure'}">
                                <c:redirect url="pharmacy?command=viewUserOrders#failure"/>
                            </c:if>

                            <c:if test="${result=='emptyChoice'}">
                                <c:redirect url="pharmacy?command=viewUserOrders#empty_choice"/>
                            </c:if>
                        </c:if>
                        <div id="success">
                            <div class="window">
                                <br/>
                                <h2><fmt:message key="order.payment.success.title"/></h2>
                                <br/>
                                <a href="#" class="win_button">OK</a>
                            </div>
                        </div>
                        <div id="failure">
                            <div class="window">
                                <br/>
                                <h2><fmt:message key="order.payment.failure.title"/></h2>
                                <a href="pharmacy?command=rechargeBalance" class="win_button">
                                    <fmt:message key="button.balance"/>
                                </a>
                                <a href="#" class="win_button"><fmt:message key="button.cancel"/></a>
                                <br/>
                            </div>
                        </div>
                        <div id="empty_choice">
                            <div class="window">
                                <br/>
                                <h2><fmt:message key="order.empty.title"/></h2>
                                <br/> <br/>
                                <a href="#" class="win_button"><fmt:message key="button.close"/></a>
                                <br/>
                            </div>
                        </div>
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