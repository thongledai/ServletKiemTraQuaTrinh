<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>ĐĂNG KÝ</h2>

<c:if test="${not empty message}">
    <p style="color:red">${message}</p>
</c:if>

<form action="${pageContext.request.contextPath}/register"
      method="post">

    <p>Username:</p>
    <input type="text" name="username" required>

    <p>Password:</p>
    <input type="password" name="password" required>

    <p>Họ tên:</p>
    <input type="text" name="fullname">

    <p>Số điện thoại:</p>
    <input type="text" name="phone">

    <p>Email:</p>
    <input type="email" name="email" required>

    <br><br>

    <button type="submit">Đăng ký</button>

</form>

<p>
    <a href="${pageContext.request.contextPath}/login">
        Quay lại đăng nhập
    </a>
</p>