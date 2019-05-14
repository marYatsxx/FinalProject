<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Online-Pharmacy</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<style>
	<%@include file='view/css/style.css' %>
</style>
<body>
<div class="main">
	<div class="wrap">
	<jsp:include page="view/jsp/element/header.jsp"/>
	<div class="content">
		<div class="content_resize">
			<div class="mainbar">
				<div class="article">
					<c:if test="${empty sessionScope.user}">
						<h1>Welcome to our online-pharmacy!</h1>
					</c:if>
					<c:if test="${not empty sessionScope.user}">
						<h1>Welcome, <c:out value="${sessionScope.user.getName()}
						${sessionScope.user.getSurname()}"/></h1>
					</c:if>
					<hr/>
					<p>Posted by <a href="#">Owner</a>
					<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. <a href="#">Morbi tincidunt, orci ac convallis aliquam, lectus turpis varius lorem, eu posuere nunc justo tempus leo.</a> Donec mattis, purus nec placerat bibendum, dui pede condimentum odio, ac blandit ante orci ut diam. Cras fringilla magna. Phasellus suscipit, leo a pharetra condimentum, lorem tellus eleifend magna, eget fringilla velit magna id neque. Curabitur vel urna. In tristique orci porttitor ipsum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. Morbi tincidunt, orci ac convallis aliquam.</p>
					<p><a href="#">Read more</a></p>
				</div>
			</div>
			<jsp:include page="view/jsp/element/menu.jsp"/>
			<div class="clr"></div>
		</div>
	</div>
	</div>
	<jsp:include page="view/jsp/element/footer.jsp"/>
</body>
</html>