<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div style="max-width: 460px; margin: 30px auto;">
    <div class="card" style="padding: 32px;">
        <div style="text-align: center; margin-bottom: 24px;">
            <h2 style="font-size: 24px; color: #0f172a;">ĐĂNG KÝ TÀI KHOẢN</h2>
            <p style="color: #64748b; font-size: 14px; margin-top: 4px;">Tạo tài khoản để trải nghiệm dịch vụ</p>
        </div>

        <c:if test="${not empty message}">
            <div class="alert alert-danger">${message}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <label class="form-label">Tên đăng nhập / Username <span style="color:red">*</span></label>
                <input type="text" name="username" class="form-control" placeholder="Nhập username" required>
            </div>

            <div class="form-group">
                <label class="form-label">Mật khẩu <span style="color:red">*</span></label>
                <input type="password" name="password" class="form-control" placeholder="Nhập mật khẩu" required>
            </div>

            <div class="form-group">
                <label class="form-label">Họ và tên</label>
                <input type="text" name="fullname" class="form-control" placeholder="Ví dụ: Nguyễn Văn A">
            </div>

            <div class="form-group">
                <label class="form-label">Số điện thoại</label>
                <input type="text" name="phone" class="form-control" placeholder="Ví dụ: 0901234567">
            </div>

            <div class="form-group">
                <label class="form-label">Email <span style="color:red">*</span></label>
                <input type="email" name="email" class="form-control" placeholder="name@example.com" required>
            </div>

            <div style="margin-top: 24px;">
                <button type="submit" class="btn btn-primary" style="width: 100%; padding: 10px;">
                    Đăng ký tài khoản
                </button>
            </div>
        </form>

        <div style="text-align: center; margin-top: 20px; font-size: 14px; color: #64748b;">
            Đã có tài khoản?
            <a href="${pageContext.request.contextPath}/login" style="color: var(--primary); font-weight: 600; text-decoration: none;">
                Đăng nhập
            </a>
        </div>
    </div>
</div>