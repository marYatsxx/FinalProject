<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
<title><fmt:message key="site.title"/> </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<style>
	<%@include file='view/css/style.css' %>
</style>
<body>
<div class="main">
	<div class="wrap">
		<%@include file="view/jsp/element/header.jspf"%>
	<div class="content">
		<div class="content_resize">
			<div class="mainbar">
				<div class="article">
					<c:if test="${empty sessionScope.user}">
						<h1><fmt:message key="homepage.welcome"/> </h1>
					</c:if>
					<c:if test="${not empty sessionScope.user}">
						<h1><fmt:message key="homepage.user.welcome"/> <c:out value="${sessionScope.user.getName()}
						${sessionScope.user.getSurname()}"/></h1>
					</c:if>
					<hr/>
					<p><fmt:message key="home.text"/></p>
					</div>
			</div>
			<%@include file="view/jsp/element/menu.jspf"%>
			<div class="clr"></div>
		</div>
	</div>
	</div>
	<%@include file="view/jsp/element/footer.jspf"%>
</div>
</body>
</html>