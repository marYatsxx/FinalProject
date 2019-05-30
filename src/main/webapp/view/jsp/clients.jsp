<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Clients</title>
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
                    <h1><fmt:message key="clients.title"/></h1>
                    <div class="employee">
                        <table>
                            <tr>
                                <th>№</th>
                                <th><fmt:message key="user.login"/></th>
                                <th><fmt:message key="user.password"/></th>
                                <th><fmt:message key="user.name"/></th>
                                <th><fmt:message key="user.surname"/></th>
                                <th><fmt:message key="user.status"/></th>
                            </tr>
                            <c:set var="count" value="0"/>
                            <c:forEach var="client" items="${clients}" varStatus="status">
                                <tr>
                                    <td><c:out value="${client.id}"/></td>
                                    <td><c:out value="${client.login}"/></td>
                                    <td><c:out value="${client.password}"/></td>
                                    <td><c:out value="${client.name}"/></td>
                                    <td><c:out value="${client.surname}"/></td>
                                    <c:if test="${client.blocked==false}">
                                        <td><fmt:message key="user.active"/></td>
                                    </c:if>
                                    <c:if test="${client.blocked==true}">
                                        <td><fmt:message key="user.blocked"/></td>
                                    </c:if>
                                    <td>
                                        <span style="display: flex">
                                            <c:if test="${client.blocked==false}">
                                                <a href="pharmacy?command=viewClients&clientId=${client.id}&decision=block#confirmation"
                                                   style="margin-right: 5px"><fmt:message key="button.block"/></a>
                                            </c:if>
                                            <c:if test="${client.blocked==true}">
                                                <a href="pharmacy?command=viewClients&clientId=${client.id}&decision=unblock#confirmation"
                                                   style="margin-right: 5px"><fmt:message key="button.unblock"/></a>
                                            </c:if>
                                            <a href="pharmacy?command=viewClients&clientId=${client.id}&decision=delete#confirmation">
                                                <fmt:message key="button.delete"/></a>
                                        </span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <br/>
                        <div id="confirmation">
                            <div class="window">
                                <form method="POST" action="pharmacy">
                                    <h2><fmt:message key="confirmation.title"/></h2>
                                    <c:set var="clientId" value="${clientId}"/>
                                    <input type="hidden" name="decision" value="${requestScope.decision}">
                                    <c:set var="decision" value="${decision}"/>
                                    <c:choose>
                                        <c:when test="${decision=='block'}">
                                            <p><fmt:message key="button.block"/>
                                                <fmt:message key="edit.user.text"/> ${clientId}?</p>
                                        </c:when>
                                        <c:when test="${decision=='unblock'}">
                                            <p><fmt:message key="button.unblock"/>
                                                <fmt:message key="edit.user.text"/> ${clientId}?</p>

                                        </c:when>
                                        <c:when test="${decision=='delete'}">
                                            <p><fmt:message key="button.delete"/>
                                                <fmt:message key="edit.user.text"/> ${clientId}?</p>
                                        </c:when>
                                    </c:choose>
                                    <input type="hidden" name="userId" value="${clientId}">
                                    <input type="hidden" name="command" value="editUsers"/>
                                    <input class="win_button" type="submit" value="<fmt:message key="button.yes"/>"/>
                                    <a href="#" class="win_button"><fmt:message key="button.no"/></a>
                                </form>
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