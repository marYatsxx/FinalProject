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
</style>
<body>
<div class="main">
    <div class="wrap">
        <jsp:include page="element/header.jsp"/>
        <div class="content">
            <div class="content_resize">
                <div class="mainbar">
                    <div class="article">
                        <div class="balance">
                            <h1>Balance recharge</h1>
                            <hr/>
                            <form name="recharge" method="POST" action="pharmacy">
                                <div class="form-wrap">
                                    <input type="hidden" name="command" value="rechargeBalance"/>
                                    <label for="card">Card code</label>
                                    <input type="text" name="card" pattern="[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}"
                                           required/><br/>
                                    <label for="validity">Card validity</label>
                                    <input type="text" name="validity" pattern="[0-9]{2}/[0-9]{2}" required/><br/>
                                    <label for="balance">Amount to recharge</label>
                                    <input type="text" name="balance" pattern="[0-9]*.[0-9]*]{2}" required/><br/>
                                    <br/>
                                </div>
                                <input id="button" type="submit" value="Recharge"/>
                            </form>
                        </div>
                        <a href="#failure">failure</a>
                        <c:if test="${result==false}">
                            <a href="#failure">failure</a>
                        </c:if>
                        <c:if test="${result==true}">
                            <a href="#success">success</a>
                            <c:redirect url="#success"/>
                        </c:if>
                        <div id="success">
                            <div class="window">
                                <br/>
                                <h2>The operation completed successfully.</h2>
                                <br/>
                                <a href="pharmacy?command=viewProfile" class="win_button">OK</a>
                            </div>
                        </div>
                        <div id="failure">
                            <div class="window">
                                <br/>
                                <h2>The operation failed.</h2>
                                <br/>
                                <a href="" class="win_button">Try again</a>
                                <a href="pharmacy?command=viewProfile" class="win_button">My account</a>
                            </div>
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
