<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Online-Pharmacy</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<style>
    <%@include file='../../view/css/style.css' %>
    <%@include file='../../view/css/catalog.css' %>
    <%@include file='../../view/css/window.css' %>
</style>
<body>
<div class="main">
    <div class="wrap">
        <jsp:include page="element/header.jsp"/>
        <div class="content">
            <div class="content_resize">
                <div class="mainbar">
                    <h1>Medication List</h1>
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
                                    <span class="price"><c:out value="${med.getPrice()}"/></span>
                                    <c:if test="${user.getUserRoleId()==3}">
                                        <c:if test="${med.needsPrescription()==true}">
                                            <a href="pharmacy?command=viewCatalog&pageCount=${pageCount}&medicine_id=${med.getId()}#prescriptionCheck"
                                               class="button">Buy</a>
                                        </c:if>
                                        <c:if test="${med.needsPrescription()==false}">
                                            <a href="pharmacy?command=viewCatalog&pageCount=${pageCount}&medicine_id=${med.getId()}#confirmation"
                                               class="button">Buy</a>
                                        </c:if>
                                    </c:if>
                                </div>
                            </div>

                            <div id="prescriptionCheck">
                                <div class="window">
                                    <h2>This medicine needs prescription.</h2>
                                    <p>Click the button to check prescription availability</p>
                                    <form>
                                        <input type="hidden" name="command" value="checkPrescription" />
                                        <c:set var="medicine_id" value="${requestScope.medicine_id}"/>
                                        <input type="hidden" name="medicine_id" value="${medicine_id}"/>
                                        <br/>
                                        <input class="win_button" type="submit" value="Check"/>
                                        <a href="#" class="win_button">Close</a>
                                    </form>
                                </div>
                            </div>
                            <div id="confirmation">
                                <div class="window">
                                    <br/>
                                    <h2>Do you want to buy this medicine?</h2>
                                    <br/>
                                    <form>
                                        <input type="hidden" name="command" value="addToOrder"/>
                                        <c:set var="medicine_id" value="${medicine_id}"/>
                                        <input type="hidden" name="medicine_id" value="${medicine_id}"/>
                                        <input class="win_button" type="submit" value="Buy"/>
                                        <a href="#" class="win_button">Close</a>
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
                                    <c:redirect url="pharmacy?command=viewCatalog&pageCount=${pageCount}&medicine_id=${medicine_id}#confirmation"/>
                                </c:if>
                            </c:if>
                            <div id="ok">
                                <div class="window">
                                    <br/>
                                    <h2>Item is added to order. You can pay for the order in your account.</h2>
                                    <br/>
                                    <a href="#" class="win_button">OK</a>
                                </div>
                            </div>
                            <div id="notFound">
                                <div class="window">
                                    <br/>
                                    <h2>Prescription not found.</h2>
                                    <br/>
                                    <a href="#" class="win_button">OK</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <br/>
                    <div class="arrow">
                        <a href="pharmacy?command=viewCatalog&pageCount=${pageCount=pageCount-1}"><img src="view/images/arrow.png"></a>
                        <a href="pharmacy?command=viewCatalog&pageCount=${pageCount=pageCount+2}"><img src="view/images/right_arrow.png"></a>
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