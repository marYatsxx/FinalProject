<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Online-Pharmacy</title>
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
        <jsp:include page="element/header.jsp"/>
        <div class="content">
            <div class="content_resize">
                <div class="mainbar">
                    <h1>My Orders</h1>
                    <div class="order" style="width: fit-content;">
                        <form action="pharmacy" method="post">
                            <table>
                                <tr>
                                    <th>№</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Date</th>
                                    <th>Status</th>
                                    <th>Select</th>
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
                                            <td>not paid</td>
                                            <td><input type="checkbox" name="select" value="${order.getId()}"></td>
                                        </c:if>
                                        <c:if test="${order.isPaid()==true}">
                                            <td>paid</td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </table>
                            <br/>
                            <input type="hidden" name="command" value="userOrders"/>
                            <div class="submit_button">
                                <a id="button" href="#payment">Pay</a>
                            </div>
                            <div id="payment">
                                <div class="window">
                                    <h2>Purchase confirmation</h2>
                                    <p>Do you want to buy selected products?</p>
                                    <br/>
                                    <input class="win_button" type="submit" value="Buy">
                                    <a href="#" class="win_button">Cancel</a>
                                </div>
                            </div>
                            <c:set var="success" value="success"/>
                            <c:if test="${requestScope.result==success}">
                                <c:redirect url="pharmacy?command=userOrders#success"/>
                            </c:if>
                            <div id="success">
                                <div class="window">
                                    <br/>
                                    <h2>Payment operation has completed successfully.</h2>
                                    <br/>
                                    <a href="#" class="win_button">OK</a>
                                </div>
                            </div>
                            <c:set var="failure" value="failure"/>
                            <c:if test="${requestScope.result==failure}">
                                <c:redirect url="pharmacy?command=userOrders#failure"/>
                            </c:if>
                            <div id="failure">
                                <div class="window">
                                    <br/>
                                    <h2>Your account has insufficient funds. You can recharge it or cancel the purchase.</h2>
                                    <br/>
                                    <a href="pharmacy?command=rechargeBalance" class="win_button">Recharge balance</a>
                                    <a href="#" class="win_button">Cancel</a>
                                </div>
                            </div>
                            <c:set var="emptyChoice" value="emptyChoice"/>
                            <c:if test="${requestScope.result==emptyChoice}">
                                <c:redirect url="pharmacy?command=userOrders#empty_choice"/>
                            </c:if>
                            <div id="empty_choice">
                                <div class="window">
                                    <br/>
                                    <h2>Nothing is chosen</h2>
                                    <br/> <br/>
                                    <a href="#" class="win_button">Close</a>
                                    <br/>
                                </div>
                            </div>
                        </form>
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