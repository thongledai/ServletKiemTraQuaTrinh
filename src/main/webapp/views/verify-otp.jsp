<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div style="max-width: 420px; margin: 50px auto;">
    <div class="card" style="padding: 32px; text-align: center;">
        <h2 style="font-size: 22px; color: #0f172a; margin-bottom: 8px;">XÁC NHẬN MÃ OTP</h2>
        <p style="color: #64748b; font-size: 14px; margin-bottom: 20px;">
            Vui lòng nhập mã OTP (6 chữ số) đã được gửi đến email của bạn để hoàn tất đăng ký.
        </p>

        <c:if test="${not empty message}">
            <div class="alert alert-danger">${message}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/verify-otp" method="post">
            <div class="form-group">
                <input type="text" name="otp" maxlength="6" class="form-control"
                       placeholder="Nhập 6 số OTP"
                       style="text-align: center; font-size: 20px; letter-spacing: 6px; font-weight: bold; width: 220px; margin: 0 auto;"
                       required autofocus>
            </div>

            <div style="margin-top: 24px;">
                <button type="submit" class="btn btn-primary" style="width: 100%; padding: 10px;">
                    Xác nhận mã
                </button>
            </div>
        </form>
    </div>
</div>