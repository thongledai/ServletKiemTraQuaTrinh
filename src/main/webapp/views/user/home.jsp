<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="jakarta.tags.core"%>

<h2>TRANG CHỦ USER</h2>

<h3>Danh mục</h3>

<c:forEach var="c"
           items="${categories}">

    <a href="${pageContext.request.contextPath}/user/home?categoryId=${c.categoryId}">
        ${c.categoryname}
    </a>

    (${c.videoCount})

    &nbsp;&nbsp;

</c:forEach>

<hr>

<h2>
    Category:
    ${category.categoryname}
</h2>

<table border="1"
       cellpadding="10"
       cellspacing="0">

    <tr>

        <th>Poster</th>
        <th>Tiêu đề</th>
        <th>Mã video</th>
        <th>Category</th>
        <th>View</th>
        <th>Chi tiết</th>

    </tr>

    <c:forEach var="video"
               items="${videos}">

        <tr>

            <td>

                <img
                    src="${pageContext.request.contextPath}/uploads/${video.poster}"
                    width="150"
                    height="100">

            </td>

            <td>
                ${video.title}
            </td>

            <td>
                ${video.videoId}
            </td>

            <td>
                ${video.category.categoryname}
            </td>

            <td>
                ${video.views}
            </td>

            <td>

                <a href="${pageContext.request.contextPath}/video/detail?id=${video.videoId}">
                    Xem
                </a>

            </td>

        </tr>

    </c:forEach>

</table>

<br>

<c:if test="${totalPage > 1}">

    <c:forEach var="i"
               begin="1"
               end="${totalPage}">

        <c:choose>

            <c:when test="${i == page}">

                <b>[${i}]</b>

            </c:when>

            <c:otherwise>

                <a href="${pageContext.request.contextPath}/user/home?categoryId=${categoryId}&page=${i}">
                    ${i}
                </a>

            </c:otherwise>

        </c:choose>

    </c:forEach>

</c:if>