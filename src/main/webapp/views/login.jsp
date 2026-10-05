<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div style="max-width: 420px; margin: 40px auto;">
    <div class="card" style="padding: 32px;">
        <div style="text-align: center; margin-bottom: 24px;">
            <h2 style="font-size: 24px; color: #0f172a;">ĐĂNG NHẬP</h2>
            <p style="color: #64748b; font-size: 14px; margin-top: 4px;">Chào mừng bạn quay trở lại</p>
        </div>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label class="form-label">Tên đăng nhập / Username</label>
                <input type="text" name="username" class="form-control" placeholder="Nhập username" required autofocus>
            </div>

            <div class="form-group">
                <label class="form-label">Mật khẩu</label>
                <input type="password" name="password" class="form-control" placeholder="Nhập mật khẩu" required>
            </div>

            <div style="margin-top: 24px;">
                <button type="submit" class="btn btn-primary" style="width: 100%; padding: 10px;">
                    Đăng nhập
                </button>
            </div>
        </form>

        <div style="text-align: center; margin-top: 20px; font-size: 14px; color: #64748b;">
            Chưa có tài khoản?
            <a href="${pageContext.request.contextPath}/register" style="color: var(--primary); font-weight: 600; text-decoration: none;">
                Đăng ký ngay
            </a>
        </div>
    </div>
</div>