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
    <%@include file='../../view/css/table.css' %>
    <%@include file='../../view/css/window.css' %>
</style>
<body>
<div class="main">
    <div class="wrap">
        <jsp:include page="element/header.jsp"/>
        <div class="content">
            <div class="content_resize">
                <div class="mainbar">
                    <div class="article">
                        <div class="profile">
                            <h1>My Profile</h1>
                            <hr/>
                            <div class="info">
                                <p>Name: <c:out value="${sessionScope.name}"/></p>
                                <p>Surname: <c:out value="${sessionScope.surname}"/></p>
                                <p>Login: <c:out value="${sessionScope.login}"/></p>
                                <p>Role:
                                    <c:choose>
                                    <c:when test="${sessionScope.user_role_id==1}">
                                    Pharmacist
                                    </c:when>
                                    <c:when test="${sessionScope.user_role_id==2}">
                                    Doctor
                                    </c:when>
                                    <c:when test="${sessionScope.user_role_id==3}">
                                    Client<br/>
                                <p>Balance: <c:out value="${sessionScope.balance}"/></p>
                                </c:when>
                                </c:choose>
                                </p>
                            </div>
                            <div class="buttons">
                                <a href="pharmacy?command=editProfile">Edit profile</a> <br/>
                                <c:if test="${sessionScope.user_role_id==2}">
                                    <a href="pharmacy?command=createPrescription">New prescription</a><br/>
                                    <a href="pharmacy?command=viewProfile&requests=true">Show renew requests</a><br/>
                                </c:if>
                                <c:if test="${sessionScope.user_role_id==3}">
                                    <a href="pharmacy?command=rechargeBalance">Recharge the balance</a><br/>
                                    <a href="pharmacy?command=userOrders">Open orders</a><br/>
                                    <a href="pharmacy?command=viewProfile&prescriptions=true">Show prescriptions</a><br/>
                                </c:if>
                            </div>
                            <c:if test="${requestScope.containsValue(prescriptions)}">
                                <br/><br/>
                                <table style="margin-left: 50px">
                                    <tr>
                                        <th>№</th>
                                        <th>Medicine</th>
                                        <th>Validity</th>
                                        <th colspan="2">Doctor</th>
                                    </tr>
                                    <c:forEach var="prescription" items="${prescriptions}" varStatus="status">
                                        <tr>
                                            <td><c:out value="${prescription.getId()}"/></td>
                                            <c:set var="medicine" value="${medicines.get(status.index)}"/>
                                            <td><c:out value="${medicine.getName()}"/>
                                                <c:out value="${medicine.getDosage()}"/></td>
                                            <td><c:out value="${prescription.getValidity()}"/></td>
                                            <c:set var="doctor" value="${doctors.get(status.index)}"/>
                                            <td><c:out value="${doctor.getName()}"/></td>
                                            <td><c:out value="${doctor.getSurname()}"/></td>
                                            <td>
                                                <a href="pharmacy?command=viewProfile&prescriptions=true&prescription_id=${prescription.getId()}#renewRequest">
                                                    Renew request</a>
                                            </td>
                                        </tr>

                                        <div id="renewRequest">
                                            <div class="window">
                                                <br/>
                                                <form>
                                                    <input type="hidden" name="command" value="renewRequest"/>
                                                    <c:set var="prescription_id" value="${prescription_id}"/>
                                                    <h2>Do you want to send renew request for prescription
                                                        №${prescription_id}? </h2>
                                                    <input type="hidden" name="prescription_id"
                                                           value="${prescription_id}"/>
                                                    <input class="win_button" type="submit" value="Send request"/>
                                                    <a href="#" class="win_button">Close</a>
                                                </form>
                                            </div>
                                        </div>
                                        <c:set var="success" value="true"/>
                                        <c:if test="${requestScope.result==success}">
                                            <c:redirect url="pharmacy?command=viewProfile&prescriptions=true#success"/>
                                        </c:if>
                                        <div id="success">
                                            <div class="window">
                                                <br/>
                                                <h2>Request was send successfully.</h2>
                                                <br/>
                                                <a href="#" class="win_button">OK</a>
                                            </div>
                                        </div>
                                        <c:set var="failure" value="false"/>
                                        <c:if test="${requestScope.result==failure}">
                                            <c:redirect url="pharmacy?command=viewProfile&prescriptions=true#failure"/>
                                        </c:if>
                                        <div id="failure">
                                            <div class="window">
                                                <br/>
                                                <h2>Operation failed</h2>
                                                <br/>
                                                <a href="#" class="win_button">OK</a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </table>
                            </c:if>

                            <c:if test="${requestScope.containsValue(requests)}">
                                <br/><br/>
                                <table style="margin-left: 50px">
                                    <tr>
                                        <th>№</th>
                                        <th colspan="2">Client</th>
                                        <th>Medicine</th>
                                        <th>Validity</th>
                                    </tr>
                                    <c:forEach var="request" items="${requests}" varStatus="status">
                                        <tr>
                                            <td><c:out value="${request.getId()}"/></td>
                                            <c:set var="client" value="${clients.get(status.index)}"/>
                                            <td><c:out value="${client.getName()}"/></td>
                                            <td><c:out value="${client.getSurname()}"/></td>
                                            <c:set var="medicine" value="${medicines.get(status.index)}"/>
                                            <td><c:out value="${medicine.getName()}"/>
                                                <c:out value="${medicine.getDosage()}"/></td>
                                            <c:set var="prescription" value="${doctorPrescriptions.get(status.index)}"/>
                                            <td><c:out value="${prescription.getValidity()}"/></td>
                                            <td>
                                                <span style="display: flex">
                                                    <a href="pharmacy?command=viewProfile&requests=true&request_id=${request.getId()}&decision=Accept#confirmation" style="margin-right: 5px">Accept</a>
                                                    <a href="pharmacy?command=viewProfile&requests=true&request_id=${request.getId()}&decision=Reject#confirmation">Reject</a>
                                                </span>
                                            </td>
                                        </tr>

                                        <div id="confirmation">
                                            <div class="window">
                                                <form method="POST" action="pharmacy">
                                                    <h2>Please, confirm your actions</h2>
                                                    <c:set var="request_id" value="${request_id}"/>
                                                    <c:set var="decision" value="${decision}"/>
                                                    <p>${decision} renew request № ${request_id}?</p>
                                                    <input type="hidden" name="decision" value="${decision}">
                                                    <input type="hidden" name="request_id" value="${request_id}">
                                                    <input type="hidden" name="command" value="processRequest"/>
                                                    <input class="win_button" type="submit" value="Yes"/>
                                                    <a href="#" class="win_button">No</a>
                                                </form>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </table>
                            </c:if>
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
