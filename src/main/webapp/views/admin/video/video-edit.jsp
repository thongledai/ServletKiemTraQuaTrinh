<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="page-header">
    <h1 class="page-title">✏️ Chỉnh Sửa Video</h1>
</div>

<c:if test="${not empty message}">
    <div class="alert alert-danger">
        ${message}
    </div>
</c:if>

<div class="card" style="max-width: 650px; margin: 0 auto; padding: 28px;">
    <form action="${pageContext.request.contextPath}/admin/videos"
          method="post"
          enctype="multipart/form-data">

        <input type="hidden" name="action" value="update">

        <div class="form-group">
            <label class="form-label">Mã Video</label>
            <input type="text" name="videoId" class="form-control" value="${video.videoId}" readonly style="background-color: #f1f5f9;">
        </div>

        <div class="form-group">
            <label class="form-label">Tiêu đề Video <span style="color:red">*</span></label>
            <input type="text" name="title" class="form-control" value="${video.title}" required>
        </div>

        <div class="form-group">
            <label class="form-label">Thể loại / Danh mục <span style="color:red">*</span></label>
            <select name="categoryId" class="form-control" required>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.categoryId}" ${category.categoryId == video.category.categoryId ? 'selected' : ''}>
                        ${category.categoryname}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label class="form-label">Poster hiện tại</label>
            <div style="margin-bottom: 10px;">
                <img src="${pageContext.request.contextPath}/uploads/${video.poster}"
                     alt="${video.title}"
                     style="width: 150px; height: 90px; object-fit: cover; border-radius: var(--radius-sm); border: 1px solid var(--border-color);"
                     onerror="this.onerror=null;this.src='https://placehold.co/150x90?text=No+Image';">
            </div>
            <label class="form-label" style="font-size: 13px; color: var(--text-muted);">Chọn ảnh mới (nếu muốn thay đổi):</label>
            <input type="file" name="poster" class="form-control" accept="image/*">
        </div>

        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 16px;">
            <div class="form-group">
                <label class="form-label">Lượt xem</label>
                <input type="number" name="views" class="form-control" value="${video.views}" min="0">
            </div>

            <div class="form-group">
                <label class="form-label">Giá bán (VNĐ)</label>
                <input type="number" name="price" class="form-control" value="${video.price}" min="0" step="1000">
            </div>
        </div>

        <div class="form-group">
            <label class="form-label">Mô tả nội dung</label>
            <textarea name="description" rows="4" class="form-control">${video.description}</textarea>
        </div>

        <div style="display: flex; gap: 12px; margin-top: 24px;">
            <button type="submit" class="btn btn-primary" style="background: #7c3aed; border-color: #7c3aed; padding: 10px 24px;">
                ✓ Cập nhật Video
            </button>
            <a href="${pageContext.request.contextPath}/admin/videos" class="btn btn-secondary">
                Quay lại danh sách
            </a>
        </div>
    </form>
</div>