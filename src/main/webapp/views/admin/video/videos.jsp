<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="jakarta.tags.core"%>

<h2>QUẢN LÝ VIDEO</h2>

<a href="${pageContext.request.contextPath}/admin/videos?action=add">
    Thêm Video
</a>

<br>
<br>

<table border="1">

    <tr>
        <th>Mã Video</th>
        <th>Tiêu đề</th>
        <th>Poster</th>
        <th>Views</th>
        <th>Category</th>
        <th>Thao tác</th>
    </tr>

    <c:forEach var="video"
               items="${videos}">

        <tr>

            <td>
                ${video.videoId}
            </td>

            <td>
                ${video.title}
            </td>

            <td>
                <img
                    src="${pageContext.request.contextPath}/uploads/${video.poster}"
                    width="150"
                    height="100">
            </td>

            <td>
                ${video.views}
            </td>

            <td>
                ${video.category.categoryname}
            </td>

            <td>

                <a href="${pageContext.request.contextPath}/admin/videos?action=edit&id=${video.videoId}">
                    Sửa
                </a>

                |

                <a href="${pageContext.request.contextPath}/admin/videos?action=delete&id=${video.videoId}"
                   onclick="return confirm('Bạn có chắc muốn xóa video này?');">
                    Xóa
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

                <a href="${pageContext.request.contextPath}/admin/videos?page=${i}">
                    ${i}
                </a>

            </c:otherwise>

        </c:choose>

    </c:forEach>

</c:if>