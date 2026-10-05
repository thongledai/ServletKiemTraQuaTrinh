<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
header {
    display: flex;
    justify-content: center;
    gap: 50px;
    padding: 15px;
    background: #3333;
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

    <a href="${pageContext.request.contextPath}/admin/videos">
        Trang quản trị
    </a>

    <a href="${pageContext.request.contextPath}/logout">
        Đăng xuất
    </a>
    


</header>