<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h2>CHI TIẾT VIDEO</h2>

<p>

    <img
        src="${pageContext.request.contextPath}/uploads/${video.poster}"
        width="300">

</p>

<p>
    Tiêu đề:
    ${video.title}
</p>

<p>
    Mã video:
    ${video.videoId}
</p>

<p>
    Category:
    ${video.category.categoryname}
</p>

<p>
    View:
    ${video.views}
</p>

<p>
    Share: 10
</p>

<p>
    Like: 10
</p>

<p>
    Description:
</p>

<p>
    ${video.description}
</p>

<br>

<a href="${pageContext.request.contextPath}/user/home">
    Quay lại
</a>