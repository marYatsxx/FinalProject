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
</style>
<body>
<div class="main">
    <div class="wrap">
        <%@include file="element/header.jspf"%>
        <div class="content">
            <div class="content_resize">
                <div class="mainbar">
                    <div class="article">
                        <div class="balance">
                            <h1><fmt:message key="button.balance"/></h1>
                            <hr/>
                            <form name="recharge" method="POST" action="pharmacy">
                                <div class="form-wrap">
                                    <input type="hidden" name="command" value="rechargeBalance"/>
                                    <br/>
                                    <div class="field">
                                        <label for="card"><fmt:message key="balance.card"/></label>
                                        <input type="text" name="card" pattern="[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}"
                                               required/>
                                        <p><fmt:message key="balance.template.code"/></p>
                                    </div>
                                    <div class="field">
                                        <label for="validity"><fmt:message key="balance.validity"/></label>
                                        <input type="text" name="validity" pattern="[0-9]{2}/[0-9]{2}" required/>
                                        <p><fmt:message key="balance.template.validity"/></p>
                                    </div>
                                    <div class="field">
                                        <label for="balance"><fmt:message key="balance.amount"/></label>
                                        <input type="text" name="balance" pattern="[0-9]*.[0-9]*]{2}" required/><br/>
                                        <p><fmt:message key="balance.amount.limit"/></p>
                                        <br/>
                                    </div>
                                </div>
                                <div class="submit_button"><input id="button" type="submit"
                                                                  value="<fmt:message key="button.recharge"/>"/></div>
                            </form>
                        </div>
                        <c:if test="${requestScope.result==false}">
                            <c:redirect url="pharmacy?command=viewRechargePage#failure"/>
                        </c:if>
                        <c:if test="${requestScope.result==true}">
                            <c:redirect url="pharmacy?command=viewRechargePage#success"/>
                        </c:if>
                        <div id="success">
                            <div class="window">
                                <br/>
                                <h2><fmt:message key="balance.success.label"/> </h2>
                                <br/>
                                <a href="pharmacy?command=viewProfile" class="win_button">OK</a>
                            </div>
                        </div>
                        <div id="failure">
                            <div class="window">
                                <br/>
                                <h2><fmt:message key="failure.title"/></h2>
                                <p><fmt:message key="${balance_error}"/> </p>
                                <a href="#" class="win_button"><fmt:message key="button.tryagain"/></a>
                                <a href="pharmacy?command=viewProfile" class="win_button">
                                    <fmt:message key="button.myprofile"/>
                                </a>
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
