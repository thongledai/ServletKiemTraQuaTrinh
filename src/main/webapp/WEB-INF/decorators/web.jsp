<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><sitemesh:write property="title" default="Web Video Portal" /></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/commons/style.css">
    <sitemesh:write property="head" />
</head>
<body>

    <%@ include file="/commons/web/header.jsp" %>

    <main class="main-content">
        <sitemesh:write property="body" />
    </main>

    <%@ include file="/commons/web/footer.jsp" %>

</body>
</html>