<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="jakarta.tags.core"%>

<h2>SỬA VIDEO</h2>

<c:if test="${not empty message}">
    <p style="color:red;">
        ${message}
    </p>
</c:if>

<form action="${pageContext.request.contextPath}/admin/videos"
      method="post"
      enctype="multipart/form-data">

    <input type="hidden"
           name="action"
           value="update">

    <p>
        Mã Video:

        <input type="text"
               name="videoId"
               value="${video.videoId}"
               readonly>
    </p>

    <p>
        Tiêu đề:

        <input type="text"
               name="title"
               value="${video.title}"
               required>
    </p>

    <p>
        Poster hiện tại:

        <br>

        <img
            src="${pageContext.request.contextPath}/uploads/${video.poster}"
            width="150"
            height="100">

        <br>
        <br>

        Chọn poster mới:

        <input type="file"
               name="poster"
               accept="image/*">
    </p>

    <p>
        Views:

        <input type="number"
               name="views"
               value="${video.views}"
               min="0">
    </p>

    <p>
        Description:

        <br>

        <textarea name="description"
                  rows="4"
                  cols="50">${video.description}</textarea>
    </p>

    <p>
        Category:

        <select name="categoryId">

            <c:forEach var="category"
                       items="${categories}">

                <option
                    value="${category.categoryId}"
                    ${category.categoryId == video.category.categoryId ? 'selected' : ''}>

                    ${category.categoryname}

                </option>

            </c:forEach>

        </select>
    </p>

    <button type="submit">
        Cập nhật
    </button>

    <a href="${pageContext.request.contextPath}/admin/videos">
        Quay lại
    </a>

</form>