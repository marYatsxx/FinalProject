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
                            <h1>Edit profile</h1>
                            <hr/>
                            <form name="edit_profile" method="post" action="pharmacy">
                                <input type="hidden" name="command" value="editProfile">



                                <div>
                                    <label for="name">Name</label>

                                </div>
                                <div>
                                    <label for="surname">Surname</label>

                                </div>
                                <div>
                                    <label for="user_role_id">Role</label>
                                    <select name="user_role_id" required>
                                        <option >Choose user role</option>
                                        <option value="doctor">Doctor</option>
                                        <option value="client">Client</option>
                                    </select>
                                </div>
                                <br/>
                                <div class="register"><input type="submit" value="Register"></div>
                            </form>
                        </div>


                            <div class="info">
                                <p>Name: <input type="text" name="name" value="<c:out value="${sessionScope.surname}"/>"></p>
                                <p>Surname: <input type="text" name="surname" value="<c:out value="${sessionScope.surname}"/>"></p>
                                <p>Login: <input type="text" name="login" value="<c:out value="${sessionScope.login}"/>"></p>
                                <div class="error">
                                    <c:if test="${not empty sessionScope.registration_error}">
                                        <c:out value="${sessionScope.registration_error}"/>
                                    </c:if>
                                </div>
                                <p>Password: <input type="password" name="password"
                                                    value="<c:out value="${sessionScope.user.getPassword}"/>" required></p>
                                <p>Repeat password: <input type="password" name="repeat_password" required></p>
                                <div class="error">
                                    <c:if test="${not empty sessionScope.password_error}">
                                        <c:out value="${sessionScope.password_error}"/>
                                    </c:if>
                                </div>
                            </div>
                            <div class="buttons">
                                <input type="button" name="save" value="Save changes"><br/><br/>
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