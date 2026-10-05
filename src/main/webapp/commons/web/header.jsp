<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<style>
header {
    display: flex;
    justify-content: center;
    gap: 50px;
    padding: 15px;
    background: #eee;
}

header a {
    margin-top: auto;
    padding: 15px;
    background: black;
    color: white;
    font-size: 22px;
    font-weight: bold;
}
</style>

<header>

    <a href="${pageContext.request.contextPath}/home">
        Trang Chủ
    </a>

    <a href="${pageContext.request.contextPath}/user/home">
        Sản phẩm
    </a>

    <c:if test="${empty sessionScope.account}">
        <a href="${pageContext.request.contextPath}/login">
            Đăng nhập
        </a>
    </c:if>

    <c:if test="${not empty sessionScope.account}">
        <a href="${pageContext.request.contextPath}/logout">
            Đăng xuất
        </a>
    </c:if>

    <c:if test="${sessionScope.account.admin}">
        <a href="${pageContext.request.contextPath}/admin/home">
            Trang quản trị
        </a>
    </c:if>

</header>