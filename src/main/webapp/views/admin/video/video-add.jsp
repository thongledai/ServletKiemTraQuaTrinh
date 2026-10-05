<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="jakarta.tags.core"%>

<h2>THÊM VIDEO</h2>

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
           value="insert">

    <p>
        Mã Video:
        <input type="text"
               name="videoId"
               required>
    </p>

    <p>
        Tiêu đề:
        <input type="text"
               name="title"
               required>
    </p>

    <p>
        Poster:
        <input type="file"
               name="poster"
               accept="image/*"
               required>
    </p>

    <p>
        Views:
        <input type="number"
               name="views"
               value="0"
               min="0">
    </p>

    <p>
        Description:
        <br>

        <textarea name="description"
                  rows="4"
                  cols="50"></textarea>
    </p>

    <p>
        Category:

        <select name="categoryId">

            <c:forEach var="category"
                       items="${categories}">

                <option value="${category.categoryId}">
                    ${category.categoryname}
                </option>

            </c:forEach>

        </select>
    </p>

    <button type="submit">
        Thêm
    </button>

    <a href="${pageContext.request.contextPath}/admin/videos">
        Quay lại
    </a>

</form>