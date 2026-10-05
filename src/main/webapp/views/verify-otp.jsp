<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>XÁC NHẬN OTP</h2>

<c:if test="${not empty message}">
    <p style="color:red">${message}</p>
</c:if>

<form action="${pageContext.request.contextPath}/verify-otp"
      method="post">

    <p>Nhập mã OTP:</p>

    <input type="text"
           name="otp"
           maxlength="6"
           required>

    <button type="submit">
        Xác nhận
    </button>

</form>