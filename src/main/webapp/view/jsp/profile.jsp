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
    <%@include file='../../view/css/table.css' %>
    <%@include file='../../view/css/window.css' %>
</style>
<body>
<div class="main">
    <div class="wrap">
        <%@include file="element/header.jspf"%>
        <div class="content">
            <div class="content_resize">
                <div class="mainbar">
                    <div class="article">
                        <div class="profile">
                            <h1><fmt:message key="menu.profile"/></h1>
                            <hr/>
                            <div class="info">
                                <p><fmt:message key="user.name"/>: <c:out value="${sessionScope.name}"/></p>
                                <p><fmt:message key="user.surname"/>: <c:out value="${sessionScope.surname}"/></p>
                                <p><fmt:message key="user.login"/>: <c:out value="${sessionScope.login}"/></p>
                                <p><fmt:message key="user.role"/>:
                                <c:choose>
                                <c:when test="${sessionScope.user_role_id==1}">
                                    <fmt:message key="user.role.admin"/>
                                </c:when>
                                <c:when test="${sessionScope.user_role_id==2}">
                                    <fmt:message key="user.role.doctor"/>
                                </c:when>
                                <c:when test="${sessionScope.user_role_id==3}">
                                    <fmt:message key="user.role.client"/>
                                    <br/>
                                    <p><fmt:message key="client.balance"/>: <c:out value="${sessionScope.balance} BYN"/></p>
                                </c:when>
                                <c:when test="${sessionScope.user_role_id==4}">
                                    <fmt:message key="user.role.pharmacist"/>
                                </c:when>
                                </c:choose>
                                </p>
                            </div>
                            <div class="buttons">
                                <a href="pharmacy?command=viewEditProfile"><fmt:message key="button.edit.profile"/></a>
                                <br/>
                                <c:if test="${sessionScope.user_role_id==1}">
                                    <a href="pharmacy?command=viewRegister">
                                        <fmt:message key="button.add.employee"/>
                                    </a><br/>
                                    <a href="pharmacy?command=viewEmployees">
                                        <fmt:message key="button.edit.employees"/>
                                    </a><br/>
                                    <a href="pharmacy?command=viewClients">
                                        <fmt:message key="button.edit.clients"/>
                                    </a><br/>
                                </c:if>
                                <c:if test="${sessionScope.user_role_id==2}">
                                    <a href="pharmacy?command=viewPrescription">
                                        <fmt:message key="button.prescription"/>
                                    </a><br/>
                                    <a href="pharmacy?command=viewProfile&requests=true">
                                        <fmt:message key="button.show.requests"/>
                                    </a><br/>
                                </c:if>
                                <c:if test="${sessionScope.user_role_id==3}">
                                    <a href="pharmacy?command=viewRechargePage">
                                        <fmt:message key="button.balance"/>
                                    </a><br/>
                                    <a href="pharmacy?command=viewUserOrders">
                                        <fmt:message key="button.show.user.orders"/>
                                    </a><br/>
                                    <a href="pharmacy?command=viewProfile&prescriptions=true">
                                        <fmt:message key="button.show.user.prescriptions"/>
                                    </a><br/>
                                </c:if>
                                <c:if test="${sessionScope.user_role_id==4}">
                                    <a href="pharmacy?command=viewMedicine">
                                        <fmt:message key="button.show.medicine"/>
                                    </a><br/>
                                    <a href="pharmacy?command=viewEmployees">
                                        <fmt:message key="button.edit.employees"/>
                                    </a><br/>
                                </c:if>
                            </div>
                            <c:if test="${requestScope.found==false}">
                                <div>
                                    <p style="text-align: center; clear:both"><fmt:message key="client.prescriptions.empty"/></p>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.containsValue(prescriptions)}">
                                <br/><br/>
                                <table style="margin-left: 50px">
                                    <tr>
                                        <th>№</th>
                                        <th><fmt:message key="medicine"/></th>
                                        <th><fmt:message key="prescription.validity"/></th>
                                        <th colspan="2"><fmt:message key="user.role.doctor"/></th>
                                    </tr>
                                    <c:forEach var="prescription" items="${prescriptions}" varStatus="status">
                                        <tr>
                                            <td><c:out value="${prescription.getId()}"/></td>
                                            <c:set var="medicine" value="${medicine_list.get(status.index)}"/>
                                            <td><c:out value="${medicine.name}"/>
                                                <c:out value="${medicine.dosage}"/></td>
                                            <td><c:out value="${prescription.getValidity()}"/></td>
                                            <c:set var="doctor" value="${doctors.get(status.index)}"/>
                                            <td><c:out value="${doctor.name}"/></td>
                                            <td><c:out value="${doctor.surname}"/></td>
                                            <td>
                                                <a href="pharmacy?command=viewProfile&prescriptions=true&prescription_id=${prescription.getId()}#renewRequest">
                                                    <fmt:message key="request"/></a>
                                            </td>
                                        </tr>

                                        <div id="renewRequest">
                                            <div class="window">
                                                <br/>
                                                <form>
                                                    <input type="hidden" name="command" value="renewRequest"/>
                                                    <c:set var="prescription_id" value="${prescription_id}"/>
                                                    <h2><fmt:message key="profile.request.title"/>
                                                        №${prescription_id}? </h2>
                                                    <input type="hidden" name="prescription_id"
                                                           value="${prescription_id}"/>
                                                    <input class="win_button" type="submit" value="<fmt:message key="button.send"/>"/>
                                                    <a href="#" class="win_button"><fmt:message key="button.close"/></a>
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
                                                <h2><fmt:message key="profile.success.title"/></h2>
                                                <br/>
                                                <a href="#" class="win_button"><fmt:message key="button.close"/></a>
                                            </div>
                                        </div>
                                        <c:set var="failure" value="false"/>
                                        <c:if test="${requestScope.result==failure}">
                                            <c:redirect url="pharmacy?command=viewProfile&prescriptions=true#failure"/>
                                        </c:if>
                                        <div id="failure">
                                            <div class="window">
                                                <br/>
                                                <h2><fmt:message key="failure.title"/></h2>
                                                <br/>
                                                <a href="#" class="win_button"><fmt:message key="button.close"/></a>
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
                                        <th colspan="2"><fmt:message key="user.role.client"/></th>
                                        <th><fmt:message key="medicine"/></th>
                                        <th><fmt:message key="prescription.validity"/></th>
                                    </tr>
                                    <c:forEach var="request" items="${requests}" varStatus="status">
                                        <tr>
                                            <td><c:out value="${request.getId()}"/></td>
                                            <c:set var="client" value="${clients.get(status.index)}"/>
                                            <td><c:out value="${client.name}"/></td>
                                            <td><c:out value="${client.surname}"/></td>
                                            <c:set var="medicine" value="${medicine_list.get(status.index)}"/>
                                            <td><c:out value="${medicine.name}"/>
                                                <c:out value="${medicine.dosage}"/></td>
                                            <c:set var="prescription" value="${doctorPrescriptions.get(status.index)}"/>
                                            <td><c:out value="${prescription.getValidity()}"/></td>
                                            <td>
                                                <span style="display: flex">
                                                    <a href="pharmacy?command=viewProfile&requests=true&request_id=${request.getId()}&decision=Accept#confirmation"
                                                       style="margin-right: 5px"><fmt:message key="button.Accept"/></a>
                                                    <a href="pharmacy?command=viewProfile&requests=true&request_id=${request.getId()}&decision=Reject#confirmation">
                                                        <fmt:message key="button.Reject"/></a>
                                                </span>
                                            </td>
                                        </tr>

                                        <div id="confirmation">
                                            <div class="window">
                                                <form method="POST" action="pharmacy">
                                                    <h2><fmt:message key="confirmation.title"/></h2>
                                                    <c:set var="request_id" value="${request_id}"/>
                                                    <c:set var="decision" value="${decision}"/>
                                                    <p><fmt:message key="button.${decision}"/> <fmt:message key="profile.confirmation.text"/> ${request_id}?</p>
                                                    <input type="hidden" name="decision" value="${decision}">
                                                    <input type="hidden" name="request_id" value="${request_id}">
                                                    <input type="hidden" name="command" value="processRequest"/>
                                                    <input class="win_button" type="submit" value="<fmt:message key="button.yes"/>"/>
                                                    <a href="#" class="win_button"><fmt:message key="button.no"/></a>
                                                </form>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </table>
                            </c:if>
                            <div id="ok">
                                <div class="window">
                                    <br/>
                                    <h2><fmt:message key="profile.new.employee.ok"/></h2>
                                    <br/>
                                    <a href="#" class="win_button"><fmt:message key="button.close"/></a>
                                </div>
                            </div>
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
