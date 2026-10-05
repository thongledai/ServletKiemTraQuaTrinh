<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<header class="main-header">
    <div class="header-container">
        <a href="${pageContext.request.contextPath}/home" class="brand-logo">
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <polygon points="5 3 19 12 5 21 5 3"></polygon>
            </svg>
            <span>VIDEO STORE</span>
        </a>

        <nav class="nav-links">
            <a href="${pageContext.request.contextPath}/home" class="nav-link">Trang chủ</a>
            <a href="${pageContext.request.contextPath}/user/home" class="nav-link">Sản phẩm</a>

            <c:if test="${empty sessionScope.account}">
                <a href="${pageContext.request.contextPath}/login" class="nav-link btn-primary">Đăng nhập</a>
                <a href="${pageContext.request.contextPath}/register" class="nav-link">Đăng ký</a>
            </c:if>

            <c:if test="${not empty sessionScope.account}">
                <a href="${pageContext.request.contextPath}/user/cart" class="nav-link">
                    🛒 Giỏ hàng
                </a>
                <a href="${pageContext.request.contextPath}/user/orders" class="nav-link">
                    📦 Đơn hàng
                </a>
                <span class="nav-user-info">
                    👤 ${sessionScope.account.fullname != null ? sessionScope.account.fullname : sessionScope.account.username}
                </span>

                <c:if test="${sessionScope.account.admin}">
                    <a href="${pageContext.request.contextPath}/admin/videos" class="nav-link" style="color: #7c3aed; font-weight: 600;">
                        ⚙️ Quản trị
                    </a>
                </c:if>

                <a href="${pageContext.request.contextPath}/logout" class="nav-link" style="color: #ef4444;">Đăng xuất</a>
            </c:if>
        </nav>
    </div>
</header>