<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>ĐĂNG NHẬP</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<c:if test="${not empty message}">
    <p style="color:green">${message}</p>
</c:if>

<form action="${pageContext.request.contextPath}/login"
      method="post">

    <p>Username:</p><input type="text" name="username" required>

    <p>Password:</p><input type="password" name="password" required>

    <br><br>

    <button type="submit">
        Đăng nhập
    </button>

</form>

<p>
    Chưa có tài khoản?
    <a href="${pageContext.request.contextPath}/register">
        Đăng ký
    </a>
</p>