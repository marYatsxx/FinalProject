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
                    <h1><fmt:message key="medicine"/></h1>
                    <div class="medicine_list" style="margin-left: 80px">
                        <table>
                            <tr>
                                <th>№</th>
                                <th><fmt:message key="medicine.name"/></th>
                                <th><fmt:message key="medicine.dosage"/></th>
                                <th><fmt:message key="medicine.price"/></th>
                                <th><fmt:message key="prescription"/></th>
                            </tr>
                            <c:forEach var="medicine" items="${medicine_list}" varStatus="status">
                                <tr>
                                    <td><c:out value="${medicine.id}"/></td>
                                    <td><c:out value="${medicine.name}"/></td>
                                    <td><c:out value="${medicine.dosage}"/></td>
                                    <td><c:out value="${medicine.price}"/></td>
                                    <c:if test="${medicine.isNeedsPrescription()==true}">
                                        <td><fmt:message key="button.yes"/></td>
                                    </c:if>
                                    <c:if test="${medicine.isNeedsPrescription()==false}">
                                        <td><fmt:message key="button.no"/></td>
                                    </c:if>
                                    <td>
                                        <a href="pharmacy?command=viewMedicine&medicine_id=${medicine.id}#edit">
                                            <fmt:message key="button.edit"/></a>
                                        <a href="pharmacy?command=viewMedicine&medicine_id=${medicine.id}#delete">
                                            <fmt:message key="button.delete"/></a>
                                    </td>
                                </tr>
                                <div id="edit">
                                    <div class="window" style="height: 250px;">
                                        <h2><fmt:message key="edit.medicine"/></h2>
                                        <form name="edit_medicine" method="post" action="pharmacy">
                                            <input type="hidden" name="command" value="editMedicine"/>
                                            <c:set var="changedMedicine" value="${changedMedicine}"/>
                                            <input type="hidden" name="medicine_id" value="${changedMedicine.id}">
                                            <div class="form-wrap" style="margin-left: 100px">
                                                <div class="field">
                                                    <label for="name"><fmt:message key="medicine.name"/></label>
                                                    <input type="text" name="name"
                                                           value="<c:out value="${changedMedicine.name}"/>">
                                                </div>
                                                <div class="field">
                                                    <label for="dosage"><fmt:message key="medicine.dosage"/></label>
                                                    <input type="text" name="dosage"
                                                           value="<c:out value="${changedMedicine.dosage}"/>">
                                                </div>
                                                <div class="field">
                                                    <label for="price"><fmt:message key="medicine.price"/></label>
                                                    <input type="text" name="price"
                                                           value="<c:out value="${changedMedicine.price}"/>">
                                                </div>
                                                <div class="field">
                                                    <label for="needs_prescription"><fmt:message key="prescription"/></label>
                                                    <select name="needs_prescription" required>
                                                        <option value="yes"><fmt:message key="button.yes"/></option>
                                                        <option value="no"><fmt:message key="button.no"/></option>
                                                    </select>
                                                </div>
                                                <br/>
                                                <input class="win_button" type="submit" name="save"
                                                           value="<fmt:message key="button.save"/>">
                                                <a class="win_button" href="#">
                                                    <fmt:message key="button.close"/></a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div id="delete">
                                    <div class="window">
                                        <h2><fmt:message key="medicine.delete"/></h2>
                                        <form name="edit_medicine" method="post" action="pharmacy">
                                            <input type="hidden" name="delete" value="delete"/>
                                            <input type="hidden" name="command" value="editMedicine"/>
                                            <c:set var="changedMedicine" value="${changedMedicine}"/>
                                            <input type="hidden" name="medicine_id" value="${changedMedicine.id}">
                                            <div class="form-wrap" style="margin-left: 100px">
                                                <input class="win_button" type="submit" name="delete"
                                                       value="<fmt:message key="button.delete"/>">
                                                <a class="win_button" href="#">
                                                    <fmt:message key="button.close"/></a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </c:forEach>
                        </table>
                        <div class="submit_button">
                            <a id="button" href="#add"><fmt:message key="button.new.medicine"/></a>
                        </div>
                        <div id="add">
                            <div class="window" style="height: 250px;">
                                <h2><fmt:message key="edit.medicine"/></h2>
                                <form name="edit_medicine" method="post" action="pharmacy">
                                    <input type="hidden" name="command" value="editMedicine"/>
                                    <div class="form-wrap" style="margin-left: 100px">
                                        <div class="field">
                                            <label for="name"><fmt:message key="medicine.name"/></label>
                                            <input type="text" name="name" required>
                                        </div>
                                        <div class="field">
                                            <label for="dosage"><fmt:message key="medicine.dosage"/></label>
                                            <input type="text" name="dosage" required>
                                        </div>
                                        <div class="field">
                                            <label for="price"><fmt:message key="medicine.price"/></label>
                                            <input type="text" name="price" required>
                                        </div>
                                        <div class="field">
                                            <label for="needs_prescription"><fmt:message key="prescription"/></label>
                                            <select name="needs_prescription" required>
                                                <option value="yes"><fmt:message key="button.yes"/></option>
                                                <option value="no"><fmt:message key="button.no"/></option>
                                            </select>
                                        </div>
                                        <br/>
                                        <input class="win_button" type="submit" name="save"
                                               value="<fmt:message key="button.save"/>">
                                        <a class="win_button" href="#">
                                            <fmt:message key="button.close"/></a>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <br/>
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